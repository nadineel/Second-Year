
/*in milliseconds
			| Insert| Selection   | Quick  | Merge
1000 random 		|13.457	|12.689	      |1.025   |1.419
10000 random 		|51.176	|66.005	      |2.793   |6.586
1000 few unique 	|12.119	|0.496	      |2.097   |0.537
1000 nearly ordered 	|3.26   |0.449        |0.243   |0.727
1000 reverse order 	|1.16	|0.449	      |0.191   |0.547
1000 sorted		|1.213	|0.582	      |0.150   |0.464
*/

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Bernadine Lao
 * @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty() {

		double[] a = new double[0];
		assertEquals("Empty insertionSort", 0, SortComparison.insertionSort(a).length);
		a = new double[0];
		assertEquals("Empty selectionSort", 0, SortComparison.selectionSort(a).length);
		a = new double[0];
		assertEquals("Empty mergeSort", 0, SortComparison.mergeSort(a).length);
		a = new double[0];
		assertEquals("Empty quickSort", 0, SortComparison.quickSort(a).length);

//		System.out.println(Arrays.toString(SortComparison.insertionSort(a)));
	}

	@Test
	public void testOne() {

		double[] a = { 4.2 };
		assertEquals("Stay the same insertionSort", a, SortComparison.insertionSort(a));
		assertEquals("Stay the same selsectionSort", a, SortComparison.selectionSort(a));
		assertEquals("Stay the same mergeSort", a, SortComparison.mergeSort(a));
		assertEquals("Stay the same quickSort", a, SortComparison.quickSort(a));

//		System.out.println(Arrays.toString(SortComparison.insertionSort(a)));
	}

	@Test
	public void testTwo() {

		double[] a = { 4.2, 2.0 };
//		 System.out.println(Arrays.toString(SortComparison.quickSort(a)));
		assertEquals("insertionSort", "[2.0, 4.2]", Arrays.toString(SortComparison.insertionSort(a)));
		double[] a1 = { 4.2, 2.0 };
		assertEquals("selsectionSort", "[2.0, 4.2]", Arrays.toString(SortComparison.selectionSort(a1)));
		double[] a2 = { 4.2, 2.0 };
		assertEquals("mergeSort", "[2.0, 4.2]", Arrays.toString(SortComparison.mergeSort(a2)));
		double[] a3 = { 4.2, 2.0 };
		assertEquals("quickSort", "[2.0, 4.2]", Arrays.toString(SortComparison.quickSort(a3)));

	}

	@Test
	public void testThreeUnsorted() {

		double[] a = { 0.5, 4.2, 2.0 };
		// System.out.println(Arrays.toString(SortComparison.selectionSort(a)));
		assertEquals("Stay the same insertionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.insertionSort(a)));
		double[] a1 = { 0.5, 4.2, 2.0 };
		assertEquals("Stay the same selsectionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.selectionSort(a1)));
		double[] a2 = { 0.5, 4.2, 2.0 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.mergeSort(a2)));
		double[] a3 = { 0.5, 4.2, 2.0 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.quickSort(a3)));

	}

	@Test
	public void testThreeSorted() {

		double[] a = { 0.5, 2.0, 4.2 };
		// System.out.println(Arrays.toString(SortComparison.selectionSort(a)));
		assertEquals("Stay the same insertionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.insertionSort(a)));
		double[] a1 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same selsectionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.selectionSort(a1)));
		double[] a2 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.mergeSort(a2)));
		double[] a3 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.quickSort(a3)));

	}
	
	@Test
	public void testThreeReversed() {

		double[] a = { 4.2, 2.0, 0.5 };
		// System.out.println(Arrays.toString(SortComparison.selectionSort(a)));
		assertEquals("Stay the same insertionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.insertionSort(a)));
		double[] a1 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same selsectionSort", "[0.5, 2.0, 4.2]",
				Arrays.toString(SortComparison.selectionSort(a1)));
		double[] a2 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.mergeSort(a2)));
		double[] a3 = { 0.5, 2.0, 4.2 };
		assertEquals("Stay the same mergeSort", "[0.5, 2.0, 4.2]", Arrays.toString(SortComparison.quickSort(a3)));

	}

	@Test
	public void testUnsorted() {

		double[] a = { 0.5, 4.2, 2.0, 8, 5, 112, 0 };
		// System.out.println(Arrays.toString(SortComparison.mergeSort(a)));
		assertEquals("Stay the same insertionSort", "[0.0, 0.5, 2.0, 4.2, 5.0, 8.0, 112.0]",
				Arrays.toString(SortComparison.insertionSort(a)));
		double[] a1 = { 0.5, 4.2, 2.0, 8, 5, 112, 0 };
		assertEquals("Stay the same selsectionSort", "[0.0, 0.5, 2.0, 4.2, 5.0, 8.0, 112.0]",
				Arrays.toString(SortComparison.selectionSort(a1)));
		double[] a2 = { 0.5, 4.2, 2.0, 8, 5, 112, 0 };
		assertEquals("Stay the same mergeSort", "[0.0, 0.5, 2.0, 4.2, 5.0, 8.0, 112.0]",
				Arrays.toString(SortComparison.mergeSort(a2)));
		double[] a3 = { 0.5, 4.2, 2.0, 8, 5, 112, 0 };
		assertEquals("Stay the same mergeSort", "[0.0, 0.5, 2.0, 4.2, 5.0, 8.0, 112.0]",
				Arrays.toString(SortComparison.quickSort(a3)));

	}

	// TODO: add more tests here. Each line of code and ech decision in
	// Collinear.java should
	// be executed at least once from at least one test.

	// ----------------------------------------------------------
	/**
	 * Main Method. Use this main method to create the experiments needed to answer
	 * the experimental performance questions of this assignment.
	 * 
	 * @throws FileNotFoundException
	 *
	 */
	public static void main(String[] args) throws IOException {

		String[] files = new String[] {
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbers1000.txt",
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbers10000.txt",
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbers1000Duplicates.txt",
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbersNearlyOrdered1000.txt",
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbersReverse1000.txt",
				"C://Users/Owner/Desktop/2nd Year/ICS/homeworks/Algo 2/Assign 1/numbersSorted1000.txt" };
		Scanner scanner = new Scanner(new File(files[0]));
		double[] number1000 = new double[1000];
		int i = 0;
		while (scanner.hasNextInt()) {
			number1000[i++] = scanner.nextInt();
		}

		scanner = new Scanner(new File(files[1]));
		double[] number10000 = new double[10000];
		i = 0;
		while (scanner.hasNextInt()) {
			number10000[i++] = scanner.nextInt();
		}

		scanner = new Scanner(new File(files[2]));
		double[] duplicates1000 = new double[1000];
		i = 0;
		while (scanner.hasNextInt()) {
			duplicates1000[i++] = scanner.nextInt();
		}

		scanner = new Scanner(new File(files[3]));
		double[] nearOrder1000 = new double[1000];
		i = 0;
		while (scanner.hasNextInt()) {
			nearOrder1000[i++] = scanner.nextInt();
		}

		scanner = new Scanner(new File(files[4]));
		double[] reverse1000 = new double[1000];
		i = 0;
		while (scanner.hasNextInt()) {
			reverse1000[i++] = scanner.nextInt();
		}

		scanner = new Scanner(new File(files[5]));
		double[] sorted1000 = new double[1000];
		i = 0;
		while (scanner.hasNextInt()) {
			sorted1000[i++] = scanner.nextInt();
		}

// Insertion Sort
		System.out.println("Insertion Sort:");
		double start=System.nanoTime();
		SortComparison.insertionSort(number1000);
		double now= System.nanoTime();
		double elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000", elapsedTime);

		start=System.nanoTime();
		SortComparison.insertionSort(number10000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 10000", elapsedTime);

		start=System.nanoTime();
		SortComparison.insertionSort(duplicates1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 duplicates", elapsedTime);

		start=System.nanoTime();
		SortComparison.insertionSort(nearOrder1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 nearlyOrdered", elapsedTime);

		start=System.nanoTime();
		SortComparison.insertionSort(reverse1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 reversed", elapsedTime);

		start=System.nanoTime();
		SortComparison.insertionSort(sorted1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 sorted", elapsedTime);

// Selection Sort
		System.out.println("Selection Sort:");
		start=System.nanoTime();
		SortComparison.selectionSort(number1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000", elapsedTime);

		start=System.nanoTime();
		SortComparison.selectionSort(number10000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 10000", elapsedTime);

		start=System.nanoTime();
		SortComparison.selectionSort(duplicates1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 duplicates", elapsedTime);

		start=System.nanoTime();
		SortComparison.selectionSort(nearOrder1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 nearlyOrdered", elapsedTime);

		start=System.nanoTime();
		SortComparison.selectionSort(reverse1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 reversed", elapsedTime);

		start=System.nanoTime();
		SortComparison.selectionSort(sorted1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 sorted", elapsedTime);
		
//Quick Sort
		System.out.println("Quick Sort:");
		start=System.nanoTime();
		SortComparison.quickSort(number1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000", elapsedTime);

		start=System.nanoTime();
		SortComparison.quickSort(number10000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 10000", elapsedTime);

		start=System.nanoTime();
		SortComparison.quickSort(duplicates1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 duplicates", elapsedTime);

		start=System.nanoTime();
		SortComparison.quickSort(nearOrder1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 nearlyOrdered", elapsedTime);

		start=System.nanoTime();
		SortComparison.quickSort(reverse1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 reversed", elapsedTime);

		start=System.nanoTime();
		SortComparison.quickSort(sorted1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 sorted", elapsedTime);
		
//Merge Sort
		System.out.println("Merge Sort:");
		start=System.nanoTime();
		SortComparison.mergeSort(number1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000", elapsedTime);

		start=System.nanoTime();
		SortComparison.mergeSort(number10000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 10000", elapsedTime);

		start=System.nanoTime();
		SortComparison.mergeSort(duplicates1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 duplicates", elapsedTime);

		start=System.nanoTime();
		SortComparison.mergeSort(nearOrder1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 nearlyOrdered", elapsedTime);

		start=System.nanoTime();
		SortComparison.mergeSort(reverse1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 reversed", elapsedTime);

		start=System.nanoTime();
		SortComparison.mergeSort(sorted1000);
		now= System.nanoTime();
		elapsedTime=(now-start)/1000000;
		System.out.printf("%s: %.3f ms\n", "elapsed time for 1000 sorted", elapsedTime);
	}

}
