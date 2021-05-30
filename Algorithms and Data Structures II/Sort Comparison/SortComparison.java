// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Bernadine Lao
 * @version HT 2020
 */
import java.util.Arrays;

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {

		double temp = 0;
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}
		return a;
	}

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {

		double tmp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			int low = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[low]) {
					low = j;
				}
			}

			tmp = a[i];
			a[i] = a[low];
			a[low] = tmp;

		}
		return a;
	}// end selectionsort

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {

		recursiveQuick(a, 0, a.length - 1);
		return a;
		

	}// end quicksort

	private static void recursiveQuick(double[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		
		int pivotPos = partition(a, lo, hi);
		//System.out.println("pivot:"+pivotPos);
		recursiveQuick(a, lo, pivotPos - 1);
		recursiveQuick(a, pivotPos + 1, hi);
		
	}

	private static int partition(double[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		double pivot = a[lo];
		while (true) {
			while ((a[++i] < pivot)) {
				if (i == hi){
					break;
				}
			}
//move j to the left till beside pivot 			
			while ((pivot < a[--j])) {
				//if (j == lo){				//not need this line if pivot=a[0]
					//break;				//as a[0] never < a[--j] when j=1
				//}
			}
			
			if (i >= j) {
				break;
			}
			double temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}

		a[lo] = a[j];
		a[j] = pivot;
		return j;
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSort(double a[]) {

		int n = a.length;
		double temp[] = new double[n];
		for (int curr_size = 1; curr_size < n; curr_size = 2 * curr_size) {
			for (int left = 0; left < n - 1; left += 2 * curr_size) {
				int right = Math.min(left + 2 * curr_size - 1, n - 1);
				merge(a, temp, left, left + curr_size - 1, right);
			}
		}
		return a;
	}

	// merge

	private static void merge(double[] a, double[] temp, int first, int mid, int last) {
		for (int c = first; c <= last; c++) {
			temp[c] = a[c];
		}
		int i = first;
		int j = mid + 1;
		for (int c = first; c <= last; c++) {
			if (i > mid) {
				a[c] = temp[j++];
			} else if (j > last) {
				a[c] = temp[i++];
			} else if (temp[j] < temp[i]) {
				a[c] = temp[j++];
			} else {
				a[c] = temp[i++];
			}
		}
	}

	//public static void main(String[] args) {
		//wasnt told to do anything here
		// todo: do experiments as per assignment instructions
	//}

}// end class
