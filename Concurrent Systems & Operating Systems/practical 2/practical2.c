#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include "cond.c"

pthread_mutex_t  mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cv = PTHREAD_COND_INITIALIZER;
int myCond=1;

int pnum;  // number updated when producer runs.
int csum;  // sum computed using pnum when consumer runs.

int (*pred)(int); // predicate indicating if pnum is to be consumed

int produceT() {
  scanf("%d",&pnum); // read a number from stdin
  return pnum;
}

void *Produce(void *a) {
  int p;

  
  p=1;
  while (p) {
    
    pthread_mutex_lock(&mutex);
    printf("mutex in Prod lock \n");

    printf("@P-READY\n");
    p = produceT();
    printf("@PRODUCED %d\n",p);

    pthread_cond_signal(&cv);    
    printf("prod signals, myCond:%d\n",myCond);
 
    myCond=0;    
     while(!myCond)
    {
    	 pthread_cond_wait(&cv,&mutex);
    }
    pthread_mutex_unlock(&mutex);
    printf("mutex in Prod unlock\n");
    
  }
  printf("@P-EXIT\n");
  pthread_exit(NULL);
}


int consumeT() {
  if ( pred(pnum) ) { csum += pnum; 
  printf( "csum:%d\n",csum );
  }
  return pnum;
}

void *Consume(void *a) {
  int p;

  p=1;
  while (p) {
   
    pthread_mutex_lock(&mutex);
    printf("mutex in con lock\n");
    while(myCond){
      pthread_cond_wait(&cv,&mutex);      
      printf("consumer waits\n");
    }
    
    printf("@C-READY\n");
    p = consumeT();
    printf("@CONSUMED %d\n",csum);
    
    myCond=1;
    pthread_cond_signal(&cv);
    printf("mutex in con unlock\n");
    pthread_mutex_unlock(&mutex);
  }
  printf("@C-EXIT\n");
  pthread_exit(NULL);
}


int main (int argc, const char * argv[]) {
  // the current number predicate
  static pthread_t prod,cons;
	long rc;

  pred = &cond1;
  if (argc>1) {
    if      (!strncmp(argv[1],"2",10)) { pred = &cond2; }
    else if (!strncmp(argv[1],"3",10)) { pred = &cond3; }
  }


  pnum = 999;
  csum=0;
  srand(time(0));

  printf("@P-CREATE\n");
 	rc = pthread_create(&prod,NULL,Produce,(void *)0);
	if (rc) {
			printf("@P-ERROR %ld\n",rc);
			exit(-1);
		}
  

  printf("@C-CREATE\n");
 	rc = pthread_create(&cons,NULL,Consume,(void *)0);
	if (rc) {
			printf("@C-ERROR %ld\n",rc);
			exit(-1);
		}

  printf("@P-JOIN\n");
  pthread_join( prod, NULL);
  printf("@C-JOIN\n");
  pthread_join( cons, NULL);


  printf("@CSUM=%d.\n",csum);

  return 0;
}
