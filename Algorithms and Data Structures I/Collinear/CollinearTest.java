import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 * Test class for Collinear.java
 *
 * @author
 * @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new Collinear();
	}
/*	public static void main(String[] args)
	{
		
		
	 In in = new In(args[0]);
	 int[] a = in.readAllInts();
	 Stopwatch stopwatch = new Stopwatch();
	 StdOut.println(Collinear.countCollinear(a1, a2, a3).count(a));
	 double time = stopwatch.elapsedTime();
	 StdOut.println("elapsed time " + time);
	}
*/

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the two methods work for empty arrays
	 */
	@Test
	public void testEmpty() {
		int expectedResult = 0;

		assertEquals("countCollinear failed with 3 empty arrays", expectedResult,
				Collinear.countCollinear(new int[0], new int[0], new int[0]));
		assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult,
				Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleFalse() {
		int[] a3 = { 15 };
		int[] a2 = { 5 };
		int[] a1 = { 10 };

		int expectedResult = 0;

		assertEquals("countCollinear({10}, {5}, {15})", expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleTrue() {
		int[] a3 = { 15, 5 };
		int[] a2 = { 5 };
		int[] a1 = { 10, 15, 5 };

		int expectedResult = 1;

		assertEquals(
				"countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3)
				+ ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	// TODO: add more tests here. Each line of code and each decision in
	// Collinear.java should
	// be executed at least once from at least one test.
	
	//Check if countCollinear is working
	@Test
    public void testCountCollinear(){
 
    int[] a1  =  {1,4,5};
    int[] a2  =  {1,3,2};
    int[] a3  =  {7,8,3,4};
       
    assertEquals(1,Collinear.countCollinear(a1, a2, a3));
   
    }
	
	//Check if countCollinearFast is working
	@Test
    public void testCountCollinearFast(){
	 
		int[] a1  =  {1,4,5};
	    int[] a2  =  {1,3,2};
	    int[] a3  =  {7,8,3,4};
	       
    assertEquals(1,Collinear.countCollinearFast(a1, a2, a3));
	   
   }
	
	//Check if sort is working
	@Test
	public void testSort() {
		int[] a1= {3,1,2}; 
		int[] test = {1,2,3};
		int expectedResult=0;
		Collinear.sort(a1);
//		System.out.println(Arrays.toString(a1));
		if(Arrays.equals(a1, test)) {
			expectedResult=1;
		}
		
	assertEquals(1,expectedResult);
	}
	

	//Check if binarySearch is working
		@Test
		public void testbinarySearch() {
			int expectedResult=0;
			int[] a1= {1,2,3,4,5};
			if(Collinear.binarySearch(a1, 4)) {
				expectedResult=1;
			}
			assertEquals(1,expectedResult);
		}
}
