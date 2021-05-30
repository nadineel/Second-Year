; Sample program makes the 4 LEDs P1.16, P1.17, P1.18, P1.19 go on and off in sequence
; (c) Mike Brady, 2020.

N	EQU	4
	
	area	tcd,code,readonly
	export	__main
__main
;main program
	ldr r2,=NUMS
	ldr r3,=RES
	ldr r4,=N
	mov r5,#0	
whl
	cmp r4,#1
	blt endwhl
	sub r4,r4,#1	
	ldr r0,[r2]
	
	bl fact
	
	add r2,r2,#4		;fetch next number from the array
	str r0,[r3,r5,lsl#2]	;store r0 as msb 
	add r5,r5,#1
	str r1,[r3,r5,lsl#2]	;store r1 as lsb
	add r5,r5,#1
	
	b whl
	
endwhl	
	
fin	b	fin

;fact subroutine
;calculates the factorial
;parameters	r0:n
;		
;return		r0r1:n! 64bit

fact 
	stmfd 	sp!,{r7,r8}
	stmfd 	sp!,{r3,lr}
	
	mov 	r6,#1		;will later hold multiplier
	
	cmp 	r0,#0 		; if(n==0){ 
	moveq 	r0,#1		;  result=1}	
	moveq 	pc,lr
	mov 	r3,r0 		;r3=n 
	sub 	r0,r0,#1 	;n=n-1
	add 	sp,sp,#-4 	;decrement the stack pointer by 4 byte,to      
				;allocate space to save a word per number. 
	
	bl 	fact		; else{n*fact(n-1)}

	ldmfd 	sp!,{r3,lr}
		         
	cmp 	r3,#0x40000000	;check if the stack points to the RES address
	beq 	done
	cmp 	r3,#14
	bne 	notovrflow
	mov 	r6,r0		;multiplier
	mov 	r0,#1
notovrflow
	cmp 	r3,#2
	bne 	notshift
	mov 	r3,r3,lsr#1
notshift
	umull 	r7,r8,r3,r0
	cmp 	r8,#0		;check for overflow
	ble 	ignore
	b 	errorhere
ignore	
	mul 	r0,r3,r0 	; multiply returned value by n
	b 	donee

errorhere
	mov 	r0,#0
	mov 	r1,#0	
	b 	donee

done
	umulls 	r1,r0,r6,r0
	lsls	R1,R1,#1
	lsl	R0,R0,#1
	adc	R0,R0,#0
	
	cmp 	r1,#0
	beq 	carryset
	msr 	CPSR_f,#0x00000000
	b 	donee
carryset
	msr 	CPSR_f,#0x20000000;carry set
	
donee	 
	add 	sp,sp,#4    	;after restoring the values, deallocate memory space on the stack

	ldmfd 	sp!,{r7,r8}
	bx 	lr

	AREA	TCDROD,DATA,READONLY
NUMS	DCD	5
	DCD	14
	DCD	20
	DCD	30


	
	AREA	TCDRAM,DATA,READWRITE	;bc its access memory
RES	SPACE	N*8	;8 bytes each
	

	end
