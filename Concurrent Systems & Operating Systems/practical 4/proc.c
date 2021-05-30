// +++++++ ONLY MODIFY BELOW LINE 39 BELOW
#include "types.h"
#include "defs.h"
#include <stdio.h>
#include "proc.h"

#define NCPU 1

struct cpu cpus[NCPU];
int ncpu;

void printstate(enum procstate pstate){ // DO NOT MODIFY
  switch(pstate) {
    case UNUSED   : printf("UNUSED");   break;
    case EMBRYO   : printf("EMBRYO");   break;
    case SLEEPING : printf("SLEEPING"); break;
    case RUNNABLE : printf("RUNNABLE"); break;
    case RUNNING  : printf("RUNNING");  break;
    case ZOMBIE   : printf("ZOMBIE");   break;
    default       : printf("????????");
  }
}

// Per-CPU process scheduler.
// Each CPU calls scheduler() after setting itself up.
// Scheduler never returns.  It loops, doing:
//  - choose a process to run
//  - swtch to start running that process
//  - eventually that process transfers control
//      via swtch back to the scheduler.

// local to scheduler in xv6, but here we need them to persist outside,
// because while xv6 runs scheduler as a "coroutine" via swtch,
// here swtch is just a regular procedure call.
struct proc *p;
struct cpu *c = cpus;

//  Add in any global variables and functions you need below.
// +++++++ ONLY MODIFY BELOW THIS LINE ++++++++++++++++++++++
int numberOfRuns[NPROC];
int isInitialised=0;
void
scheduler(void)
{ int runnableFound; // DO NOT MODIFY/DELETE

  c->proc = 0;

//initialise array that will keep track of the number of runs of each process;
  if(isInitialised==0){
    for(int i=0; i<NPROC; i++){
      numberOfRuns[i]=0;
    }
    isInitialised=1;
  }

  runnableFound = 1 ; // force one pass over ptable

  while(runnableFound){ // DO NOT MODIFY
    // Enable interrupts on this processor.
    // sti();
    runnableFound = 0; // DO NOT MODIFY
    // Loop over process table looking for process to run.
    // acquire(&ptable.lock);
//    for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){

    int leastRuns=2147483647;     //max integer
    int leastRunIndex=0;
    for(int i=0;i<NPROC;i++){
      if(ptable.proc[i].state==RUNNABLE && numberOfRuns[i]<leastRuns){
        leastRunIndex=i;
        leastRuns=numberOfRuns[i];
        runnableFound = 1; // DO NOT MODIFY/DELETE/BYPASS
      }
    }       
    
    numberOfRuns[leastRunIndex]=numberOfRuns[leastRunIndex]+1;  //load-balancing
    p= &ptable.proc[leastRunIndex];
    
    if(p->state!=RUNNABLE)  
      continue;         
    // Switch to chosen process.  It is the process's job
    // to release ptable.lock and then reacquire it
    // before jumping back to us.
    c->proc = p;
    //switchuvm(p);
    p->state = RUNNING;


    swtch(p);
    // p->state should not be running on return here.
    //switchkvm();
    // Process is done running for now.
    // It should have changed its p->state before coming back.
    c->proc = 0;
    
    // release(&ptable.lock);

  }
  printf("No RUNNABLE process!\n");
}
