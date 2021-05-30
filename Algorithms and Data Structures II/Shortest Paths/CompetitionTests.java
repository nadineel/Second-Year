//@author: Bernadine Lao

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	System.out.println("Hello Dijkstra");
		int time ;
		time = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50).timeRequiredforCompetition();
		assertEquals("TinyEWD Dijsktra", 38, time);
		time = new CompetitionDijkstra("tinyEWD.txt", 0, 50, 50).timeRequiredforCompetition();
		assertEquals("TinyEWD Dijsktra", -1, time);		
    	time= new CompetitionDijkstra("1000EWD.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("1000EWD Dijsktra", 28, time);
    	time= new CompetitionDijkstra("1000EWD.txt", 50,49,100).timeRequiredforCompetition();
    	assertEquals("1000EWD Dijsktra", -1, time);
    	time= new CompetitionDijkstra("dummyFile", 50,50,50).timeRequiredforCompetition();    	
    	assertEquals("Dummy file used", -1, time);
    	time= new CompetitionDijkstra(null, 50,50,50).timeRequiredforCompetition();
    	assertEquals("Null as file", -1, time);    	
    }

    @Test
    public void testFWConstructor() {
    	System.out.println("hello FW");    	
    	int time ;
		time = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50).timeRequiredforCompetition();
		assertEquals("TinyEWD Dijsktra", 38, time);
		time = new CompetitionFloydWarshall("tinyEWD.txt", 0, 50, 50).timeRequiredforCompetition();
		assertEquals("TinyEWD Dijsktra", -1, time);		
    	time= new CompetitionFloydWarshall("1000EWD.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("1000EWD Dijsktra", 28, time);
    	time= new CompetitionFloydWarshall("1000EWD.txt", 50,49,100).timeRequiredforCompetition();
    	assertEquals("1000EWD Dijsktra", -1, time);
    	time= new CompetitionFloydWarshall("dummyFile", 50,50,50).timeRequiredforCompetition();    	
    	assertEquals("Dummy file used", -1, time);
    	time= new CompetitionFloydWarshall(null, 50,50,50).timeRequiredforCompetition();
    	assertEquals("Null as file", -1, time);    	
    }


    @Test
    public void testIfInputWork() {
    	System.out.println("hello all inputcases");
    
//try all cases Dijkstra 
    	int time;
    	time = new CompetitionDijkstra("input-A.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-A", -1, time);
    	time = new CompetitionDijkstra("input-B.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-B", 10000, time);
    	time = new CompetitionDijkstra("input-C.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-C", -1, time);
    	time = new CompetitionDijkstra("input-D.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-D", 38, time);
    	time = new CompetitionDijkstra("input-E.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-E", 28, time);
    	time = new CompetitionDijkstra("input-F.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-F", -1, time);
    	time = new CompetitionDijkstra("input-G.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-G", -1, time);
    	time = new CompetitionDijkstra("input-H.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-H", -1, time);
    	time = new CompetitionDijkstra("input-I.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-I", 240, time);
    	time = new CompetitionDijkstra("input-J.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-J", -1, time);
    	time = new CompetitionDijkstra("input-K.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-K", 320, time);
    	time = new CompetitionDijkstra("input-L.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-L", 160, time);
    	time = new CompetitionDijkstra("input-M.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-M", 300, time);
    	time = new CompetitionDijkstra("input-N.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-N", 160, time);

//try all cases  FloydWarshall  	
    	time = new CompetitionFloydWarshall("input-A.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-A", -1, time);
    	time = new CompetitionFloydWarshall("input-B.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-B", 10000, time);
    	time = new CompetitionFloydWarshall("input-C.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-C", -1, time);
    	time = new CompetitionFloydWarshall("input-D.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-D", 38, time);
    	time = new CompetitionFloydWarshall("input-E.txt", 50,50,50).timeRequiredforCompetition();
    	assertEquals("Input-E", 28, time);
    	time = new CompetitionFloydWarshall("input-F.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-F", -1, time);
    	time = new CompetitionFloydWarshall("input-G.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-G", -1, time);
    	time = new CompetitionFloydWarshall("input-H.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-H", -1, time);
    	time = new CompetitionFloydWarshall("input-I.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-I", 240, time);
    	time = new CompetitionFloydWarshall("input-J.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-J", -1, time);
    	time = new CompetitionFloydWarshall("input-K.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-K", 320, time);
    	time = new CompetitionFloydWarshall("input-L.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-L", 160, time);
    	time = new CompetitionFloydWarshall("input-M.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-M", 300, time);
    	time = new CompetitionFloydWarshall("input-N.txt", 50, 50, 50).timeRequiredforCompetition();
    	assertEquals("Input-N", 160, time);
    }
}