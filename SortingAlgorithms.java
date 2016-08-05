/**********************
 **** Kevin Mathis ****
 ****  08/27/15    ****
 ****  CSCI-5330   ****
 **********************/

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {
	// Creating arrays that will be used for sorting algorithms
	private static int[] myOriginalArray;
	private static int[] myBubbleArray;
	private static int[] mySelectionArray;
	private static int[] myInsertionArray;
	private static int[] myQuickArray;
	private static int left = 0;
	private static int right;
	private static int n;

	public static void main(String args[]) {
		// Printing header
		System.out
		.print("Algorithm Used | 1000 elements | 5000 elements | 25000 elements | 100000 elements"
				+ "\n=================================================================================\n");

		// Create array of size 1000
		n = 1000;
		createArray(n);
		// Fills myOriginalArray with random nums (0-250) and copies that array
		// 4 times for algorithm arrays
		fillArray();

		// After array size has changed we must also change int right (place
		// holder used in quick sort)
		right = myQuickArray.length - 1;
		// Start time for algorithm (to be subtracted off later to calculate
		// total run-time)
		long quickStartTime = System.nanoTime();
		quickSortArray(myQuickArray, left, right);
		double quickTime1000 = (System.nanoTime() - quickStartTime) / 1000000.0;

		long selectionStartTime = System.nanoTime();
		selectionSortArray(mySelectionArray);
		double selectionTime1000 = (System.nanoTime() - selectionStartTime) / 1000000.0;

		long bubbleStartTime = System.nanoTime();
		bubbleSortArray(myBubbleArray);
		double bubbleTime1000 = (System.nanoTime() - bubbleStartTime) / 1000000.0;

		long insertionStartTime = System.nanoTime();
		insertionSortArray(myInsertionArray);
		double insertionTime1000 = (System.nanoTime() - insertionStartTime) / 1000000.0;

		// Create array of size 5000
		n = 5000;
		createArray(n);
		fillArray();

		right = myQuickArray.length - 1;
		quickStartTime = System.nanoTime();
		quickSortArray(myQuickArray, left, right);
		double quickTime5000 = (System.nanoTime() - quickStartTime) / 1000000.0;

		selectionStartTime = System.nanoTime();
		selectionSortArray(mySelectionArray);
		double selectionTime5000 = (System.nanoTime() - selectionStartTime) / 1000000.0;

		insertionStartTime = System.nanoTime();
		insertionSortArray(myInsertionArray);
		double insertionTime5000 = (System.nanoTime() - insertionStartTime) / 1000000.0;

		bubbleStartTime = System.nanoTime();
		bubbleSortArray(myBubbleArray);
		double bubbleTime5000 = (System.nanoTime() - bubbleStartTime) / 1000000.0;

		// Create array of size 25000
		n = 25000;
		createArray(n);
		fillArray();

		right = myQuickArray.length - 1;
		quickStartTime = System.nanoTime();
		quickSortArray(myQuickArray, left, right);
		double quickTime25000 = (System.nanoTime() - quickStartTime) / 1000000.0;

		selectionStartTime = System.nanoTime();
		selectionSortArray(mySelectionArray);
		double selectionTime25000 = (System.nanoTime() - selectionStartTime) / 1000000.0;

		insertionStartTime = System.nanoTime();
		insertionSortArray(myInsertionArray);
		double insertionTime25000 = (System.nanoTime() - insertionStartTime) / 1000000.0;

		bubbleStartTime = System.nanoTime();
		bubbleSortArray(myBubbleArray);
		double bubbleTime25000 = (System.nanoTime() - bubbleStartTime) / 1000000.0;

		// Create array of size 100000
		n = 100000;
		createArray(n);
		fillArray();

		right = myQuickArray.length - 1;
		quickStartTime = System.nanoTime();
		quickSortArray(myQuickArray, left, right);
		double quickTime100000 = (System.nanoTime() - quickStartTime) / 1000000.0;
		// After algorithms finish all 4 sorts the results are printed to
		// console.
		System.out
		.printf("Quick Sort     |----%.2f ms----|----%.2f ms----|----%.2f ms-----|---%.2f ms------\n",
				quickTime1000, quickTime5000, quickTime25000, quickTime100000);

		insertionStartTime = System.nanoTime();
		insertionSortArray(myInsertionArray);
		double insertionTime100000 = (System.nanoTime() - insertionStartTime) / 1000000.0;
		System.out
		.printf("Insertion Sort |----%.2f ms----|----%.2f ms---|----%.2f ms----|---%.2f ms---\n",
				insertionTime1000, insertionTime5000, insertionTime25000, insertionTime100000);

		selectionStartTime = System.nanoTime();
		selectionSortArray(mySelectionArray);
		double selectionTime100000 = (System.nanoTime() - selectionStartTime) / 1000000.0;
		System.out
		.printf("Selection Sort |----%.2f ms----|----%.2f ms---|----%.2f ms---|---%.2f ms---\n",
				selectionTime1000, selectionTime5000, selectionTime25000, selectionTime100000);

		bubbleStartTime = System.nanoTime();
		bubbleSortArray(myBubbleArray);
		double bubbleTime100000 = (System.nanoTime() - bubbleStartTime) / 1000000.0;
		System.out
		.printf("Bubble Sort    |----%.2f ms---|----%.2f ms---|----%.2f ms---|---%.2f ms--\n",
				bubbleTime1000, bubbleTime5000, bubbleTime25000, bubbleTime100000);
	}

	// Creates array of size n
	public static int[] createArray(int n) {
		myOriginalArray = new int[n];
		return myOriginalArray;
	}

	// Fills array with random ints (0-250)
	public static void fillArray() {
		for (int i = 0; i < myOriginalArray.length - 1; i++) {
			Random rand = new Random();
			myOriginalArray[i] = rand.nextInt(250);
		}
		// Copies original array 4 times for algorithm arrays
		myBubbleArray = Arrays.copyOf(myOriginalArray, myOriginalArray.length);
		mySelectionArray = Arrays.copyOf(myOriginalArray, myOriginalArray.length);
		myInsertionArray = Arrays.copyOf(myOriginalArray, myOriginalArray.length);
		myQuickArray = Arrays.copyOf(myOriginalArray, myOriginalArray.length);
	}

	// Quick sort algorithm
	public static int[] quickSortArray(int[] array, int left, int right) {
		// Calculate the middle index of given array
		int middle = (left + (right - left) / 2);
		// Assign pivot position to middle index
		int pivot = array[middle];

		// Make left side less than pivot and right side greater than
		int i = left;
		int j = right;
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			// If index I is < J then swap these elements, increment I and J
			// accordingly
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
		// Recursively recall quick sort algorithm to sort sub-sections of it
		if (left < j) {
			quickSortArray(array, left, j);
		}
		if (right > i) {
			quickSortArray(array, right, i);
		}
		return myQuickArray;
	}

	// Selection sorting algorithm
	private static int[] selectionSortArray(int[] array) {

		int temp;
		for (int i = array.length - 1; i > 0; i--) {
			int first = 0;
			for (int j = 1; j <= i; j++) {
				// Run through array until we find an element less than index[0]
				// (first) and swap them, repeat process.
				if (array[j] > array[first])
					first = j;
			}
			temp = array[first];
			array[first] = array[i];
			array[i] = temp;
		}
		return mySelectionArray;
	}

	// Bubble sorting algorithm
	private static int[] bubbleSortArray(int[] array) {
		// Set boolean value to true in beginning
		boolean swapped = true;
		int temp;
		while (swapped) {
			// Change boolean to false after loop starts and no swaps have been
			// made (this ends algorithm)
			swapped = false;
			for (int i = 0; i < array.length - 1; i++) {
				for (int j = 1; j < array.length - i; j++) {
					// if element at index j-1 is greater than index j, then
					// swap them (putting into acsending order)
					if (array[j - 1] > array[j]) {
						temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;
						swapped = true;
					}
				}
			}
		}
		return myBubbleArray;
	}

	// Insertion sorting algorithm
	public static int[] insertionSortArray(int[] array) {
		int i;
		int j;
		int key;
		for (i = 1; i < array.length; i++) {
			// Setting a key to position to compare with
			key = array[i];
			for (j = i - 1; (j >= 0) && (array[j] > key); j--) {
				// swap indexs [j+1] with [j] if array[j] > key (array[i])
				array[j + 1] = array[j];
			}
			// Index [j+1] becomes new key
			array[j + 1] = key;
		}
		return myInsertionArray;
	}
}