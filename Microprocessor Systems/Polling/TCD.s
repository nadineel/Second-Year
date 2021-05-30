; Sample program makes the 4 LEDs P1.16, P1.17, P1.18, P1.19 go on and off in sequence
; (c) Mike Brady, 2020.

	area	tcd,code,readonly
	export	__main
__main

IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C
IO1PIN	EQU	0xE0028010
	
;;;;;;;;;;;;;;;;;;
;the program is implemented in a way that the button must be manually released after pressing to use other buttons(or pins)
;ie one button is used at a time


;set pins 23-16 as an output
	ldr r0,=IO1DIR
	mov r1,#0x0FF0000;mask
	str r1,[r0]	;set output
	ldr r4,=IO1PIN
	mov r1,#0x0000000	;initialise 0 in the pin D=0
	str r1,[r4]		

whlrepeat
	;mov r2,#0xFF000000
	ldr r2,[r4]	
	and r2,r2,#0xFF000000
	
	;keep testing	
whPoll				;   do {
	ldr	r3,[r4]		;     currentState = currentState & 0xFF000000
	and	r3,r3,#0xFF000000	;
	cmp	r2,r3		;
	beq	whPoll		;   } while (currentState == lastState)
	
	; pin state has changed
	bl functions

	b whlrepeat
	

;functions subroutine
;performs functions specified and stores it to IO1PIN
;parameters	r3:currentState
;		
;returns the result made by the function:r1
functions
;
	stmfd 	sp!,{r3,r5}
	
	ldr r5,=0xFE000000
	cmp r3,r5
	beq addi
	
	ldr r5,=0xFD000000
	cmp r3,r5
	beq subtr
	
	ldr r5,=0xFB000000
	cmp r3,r5
	beq shlft
	
	ldr r5,=0xF7000000
	cmp r3,r5
	beq shrgt
	
	b donee
	
addi
	;mov r5,#(0x1 << 16)
	cmp r1,#0x000000FF
	beq neg1		
	add r1,r1,#1
	b endaddi
neg1
	mov r1,#0
endaddi
	b done_cmpr
	
subtr
	;mov r5,#(0x1 << 16)
	cmp r1,#0x00000000
	beq zro
	cmp r1,#0x000000FF
	beq endsubtr
	sub r1,r1,#1
	b endsubtr
zro
	mov r1,#0x000000FF
endsubtr	
	b done_cmpr
	
shlft
	mov r1,r1,lsl#1
	b done_cmpr
	
shrgt
	mov r1,r1,lsr#1
	b done_cmpr
	
done_cmpr
	mov r3,r1,LSL#16
	str r3,[r4]		;set output
donee	
	ldmfd 	sp!,{r3,r5}
	bx 	lr
	
	
	end


