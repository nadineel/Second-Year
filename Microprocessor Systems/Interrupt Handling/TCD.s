;Interrupt Handling Sample
; (c) Mike Brady, 2021

	area tcd,code,readonly
	export __main
__main

; Definitions  -- references to 'UM' are to the User Manual.

; Timer Stuff -- UM, Table 173

T0	equ	0xE0004000		; Timer 0 Base Address
T1	equ	0xE0008000

IR	equ	0			; Add this to a timer's base address to get actual register address
TCR	equ	4
MCR	equ	0x14
MR0	equ	0x18

TimerCommandReset	equ	2
TimerCommandRun	equ	1
TimerModeResetAndInterrupt	equ	3
TimerResetTimer0Interrupt	equ	1
TimerResetAllInterrupts	equ	0xFF

; VIC Stuff -- UM, Table 41
VIC	equ	0xFFFFF000		; VIC Base Address
IntEnable	equ	0x10
VectAddr	equ	0x30
VectAddr0	equ	0x100
VectCtrl0	equ	0x200

Timer0ChannelNumber	equ	4	; UM, Table 63
Timer0Mask	equ	1<<Timer0ChannelNumber	; UM, Table 63
IRQslot_en	equ	5		; UM, Table 58

IO1DIR	EQU	0xE0028018
;IO1SET	EQU	0xE0028014
;IO1CLR	EQU	0xE002801C
;IO1PIN	EQU	0xE0028010

; initialisation code
	
	mov r0,#0		;initialise counter
	ldr r1,=counter
	str r0,[r1]
			
	ldr r1,=minuteCounter	;initialise minuteCounter
	str r0,[r1]
	
	ldr r1,=hourCounter	;initialise hourCounter
	str r0,[r1]

; Initialise the VIC
	ldr	r0,=VIC			; looking at you, VIC!

	ldr	r1,=irqhan
	str	r1,[r0,#VectAddr0] 	; associate our interrupt handler with Vectored Interrupt 0

	mov	r1,#Timer0ChannelNumber+(1<<IRQslot_en)
	str	r1,[r0,#VectCtrl0] 	; make Timer 0 interrupts the source of Vectored Interrupt 0

	mov	r1,#Timer0Mask
	str	r1,[r0,#IntEnable]	; enable Timer 0 interrupts to be recognised by the VIC

	mov	r1,#0
	str	r1,[r0,#VectAddr]   	; remove any pending interrupt (may not be needed)

; Initialise Timer 0
	ldr	r0,=T0			; looking at you, Timer 0!

	mov	r1,#TimerCommandReset
	str	r1,[r0,#TCR]

	mov	r1,#TimerResetAllInterrupts
	str	r1,[r0,#IR]

	ldr	r1,=14745600 	 ; 1 second//
		;14700 fast for testing
	str	r1,[r0,#MR0]

	mov	r1,#TimerModeResetAndInterrupt
	str	r1,[r0,#MCR]

	mov	r1,#TimerCommandRun
	str	r1,[r0,#TCR]

;from here, initialisation is finished, so it should be the main body of the main program
;;here elapsed time using gpio!!!!!!!!!
	ldr r1,=IO1DIR
	str r2,[r1]
	ldr r3,=0x00F00F00		;for the : :
	mov r4,#0
	
loop	ldr r0,=counter	
	bl  toBCD
	orr r4,r2,r3			
	
	ldr r0,=minuteCounter
	bl toBCD
	lsl r2,r2,#12			;minute starts on the bit12 from the left
	
	orr r4,r4,r2
	
	ldr r0,=hourCounter
	bl toBCD
	lsl r2,r2,#24			;hour starts on the bit24 from the left
		
	orr r4,r4,r2
	str r4,[r1]			;store to gpio port1
		
	b  loop				;loop indefinitely
	
;fin b fin

;function toBCD
;converts value from counter to its BCD 
;parameters: r0:counter, r2: divider for each section : :
;return: r2 -BCD value
toBCD
	stmfd 	sp!,{r1,r3,r4}
	ldr r1,[r0]
	mov r4,r2
	mov r2, r1
	mov r3,#0	;tens
whlBCD	cmp r2,#9
	ble notConvert
	sub r2,r2,#10
	add r3,r3,#1
	b whlBCD	
notConvert
	lsl r3,r3,#4
	add r2,r2,r3
	
	
	ldmfd 	sp!,{r1,r3,r4}
	bx 	lr

	

;main program execution will never drop below the statement above.

	AREA	InterruptStuff, CODE, READONLY
irqhan	sub	lr,lr,#4
	stmfd	sp!,{r0-r1,lr}	; the lr will be restored to the pc

;this is the body of the interrupt handler

;here you'd put the unique part of your interrupt handler
;all the other stuff is "housekeeping" to save registers and acknowledge interrupts


;this is where we stop the timer from making the interrupt request to the VIC
;i.e. we 'acknowledge' the interrupt
	ldr	r0,=T0
	mov	r1,#TimerResetTimer0Interrupt
	str	r1,[r0,#IR]	   	; remove MR0 interrupt request from timer

;here we stop the VIC from making the interrupt request to the CPU:
	ldr	r0,=VIC
	mov	r1,#0
	str	r1,[r0,#VectAddr]	; reset VIC

;;will increment counter every second
	stmfd 	sp!,{r2-r5}
	ldr r0,=counter
	ldr r2,=minuteCounter
	ldr r4,=hourCounter
	ldr r5,[r4]
	ldr r3,[r2]
	ldr r1,[r0]
	
	cmp r1,#59
	bge restartSec
	add r1,r1,#1
	b continue
restartSec				;will restart every 60 sec
	mov r1,#0
	
	cmp r3,#59
	bge restartMin
	add r3,r3,#1
	b continueMin
restartMin				;will restart every 60 min
	mov r3,#0

	cmp r5,#23
	bge restartClock
	add r5,r5,#1
	b continueHour
restartClock				;will restart after 24 hours
	mov r5,#0
continueHour
	str r5,[r4]	
continueMin	
	str r3,[r2]
continue
	str r1,[r0]
	
	ldmfd 	sp!,{r2-r5}

	ldmfd	sp!,{r0-r1,pc}^	; return from interrupt, restoring pc from lr
				; and also restoring the CPSR
	
	AREA InterruptData, DATA, READWRITE
counter	space 4
minuteCounter space 4
hourCounter space 4

	END