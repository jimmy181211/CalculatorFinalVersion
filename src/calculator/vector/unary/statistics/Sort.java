package calculator.vector.unary.statistics;

import calculator.funcTools.BiFunction;
import calculator.operation.UnaryOperation;

public class Sort extends UnaryOperation {

	public Sort() {
		super("sort");
	}

	private static BiFunction<Double, Double, Boolean> compare(boolean isDesc) {
		return isDesc ? (a, b) -> a < b : (a, b) -> a > b;
	}

	// this algorithm is applied when the data set is relatively small
	public static void insertionSort(Double[] arr, boolean isDesc) {
		BiFunction<Double, Double, Boolean> compare = compare(isDesc);
		Double base;
		int j;
		for (int i = 1; i < arr.length; i++) {
			base = arr[i];
			j = i - 1;
			while (j >= 0 && compare.apply(arr[j], base)) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = base;
		}
	}

	public static void quickSort(Double[] arr) {
		quick(arr, 0, arr.length - 1);
	}

	private static void quick(Double[] arr, int left, int right) {
		// if there is only one element in the partition, end the sorting
		if (left >= right) {
			return;
		}
		/*
		 * the elements to the left of p are those smaller than it. Likewise, those to
		 * the right are bigger than it
		 */
		int p = partition(arr, left, right);
		quick(arr, left, p - 1);
		quick(arr, p + 1, right);
	}

	private static void swap(Double[] arr, int idx1, int idx2) {
		Double temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	// lomuto partition method. It returns the index of the pivot
	private static int partition(Double[] arr, int left, int right) {
		// define the right-most element as the pivot
		Double pv = arr[right];
		int i = left;// finds the element bigger than the pivot
		int j = i;// finds the element smaller than the pivot
		while (j < right) {
			// if the current element is smaller than the pivot
			if (arr[j] < pv) {
				if (i != j) {
					swap(arr, i, j);
				}
				i++;
			}
			j++;
		}
		swap(arr, i, right);
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Double[] arr = (Double[]) numObj;
		if (arr.length <= 15) {
			insertionSort(arr, false);
		} else {
			quickSort(arr);
		}
		return (E) arr;
	}

	public static void main(String[] args) {
		Double[] arr = { 2.1, 5.4, 8.5, 2.1, 1.2, 1.2, 5.2, 0.1 };
		insertionSort(arr, true);
		for (Double e : arr) {
			System.out.print(e + " ");
		}
	}
}
