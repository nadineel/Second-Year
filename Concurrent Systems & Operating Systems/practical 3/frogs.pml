#define LILLYPAD 5

mtype frogs[LILLYPAD]; 

proctype moveRight(byte at) {
end:do 
	:: 	atomic {
			(at < LILLYPAD) && (frogs[0] == at+1) -> 
			frogs[0] = at; 
        if
        ::(frogs[1] == at) -> 
        printf("MOVE FROG%d FROM %d TO %d\n", 1, frogs[1], at+1);
          frogs[1] = at+1;
          frogs[0] = at;     
        fi
			at = at + 1;
      printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3], frogs[4]);
		}
	:: atomic {
			(at < LILLYPAD-1) &&(frogs[0] != at+1) && (frogs[0] == at+2) -> 
        if
        ::(frogs[1] == at) -> 
          printf("MOVE FROG%d FROM %d TO %d\n", 1, frogs[1], at+2);
          frogs[1] = at+2;
          frogs[0] = at; 
        fi
			at = at + 2;
      printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3], frogs[4]);
		}
	od
}


proctype moveLeft(byte at) {
end:do
	:: atomic {
			(at > 1) &&(frogs[0] == at-1) -> 
        if
        ::(frogs[2] == at) -> 
         printf("MOVE FROG%d FROM %d TO %d\n", 2, frogs[2], at-1);
         frogs[2] = at-1;
         frogs[0] = at;
        ::(frogs[3] == at) -> 
         printf("MOVE FROG%d FROM %d TO %d\n", 3, frogs[3], at-1);
         frogs[3] = at-1;
         frogs[0] = at;   
        ::(frogs[4] == at) -> 
         printf("MOVE FROG%d FROM %d TO %d\n", 4, frogs[4], at-1);
         frogs[4] = at-1;
         frogs[0] = at; 
        fi
      at = at - 1;
      printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3], frogs[4]);
		}
	:: atomic {
			(at > 2) && (frogs[0] != at-1) && (frogs[0] == at-2) -> 
		    if
        ::(frogs[2] == at) -> 
        printf("MOVE FROG%d FROM %d TO %d\n", 2, frogs[2], at-2);
        frogs[2] = at-2;
        frogs[0] = at; 
        ::(frogs[3] == at) -> 
        printf("MOVE FROG%d FROM %d TO %d\n", 3, frogs[3], at-2);
        frogs[3] = at-2;
        frogs[0] = at; 
        ::(frogs[4] == at) -> 
        printf("MOVE FROG%d FROM %d TO %d\n", 4, frogs[4], at-2);
        frogs[4] = at-2;
        frogs[0] = at; 
        fi
		  at = at - 2;
      printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3], frogs[4]);
		}
	od
}

init { 
bool done=false;
atomic{
    frogs[0] = 2;
    frogs[1] = 1;
    frogs[2] = 3;
    frogs[3] = 4;
    frogs[4] = 5;
  printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3], frogs[4]);
}
  atomic{
      do
      :: done==true -> break;
   	  :: else ->
         printf( "START FROG %d AT %d GOING LEFT\n", 4, frogs[4]);
         run moveLeft(frogs[4]);
         printf( "START FROG %d AT %d GOING LEFT\n", 3, frogs[3]);
         run moveLeft(frogs[3]);
         printf( "START FROG %d AT %d GOING LEFT\n", 2, frogs[2]);
         run moveLeft(frogs[2]);
         printf( "START FROG %d AT %d GOING RIGHT\n", 1, frogs[1]);
         run moveRight(frogs[1]);
         
		     if
         ::(frogs[1]==5)->done=true;
           else->skip;
         fi
         
        
      od
        
      printf( "DONE!\n" );
      assert(!done);        
  }
}