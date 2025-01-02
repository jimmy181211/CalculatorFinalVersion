package calculator.vector.unary;

import calculator.operation.UnaryOperation;

public class Sort extends UnaryOperation {

	public Sort() {
		super("sort");
		// TODO Auto-generated constructor stub
	}

	public static void insertion(Double[] unsortedArr) {
		for (int i = 0; i < unsortedArr.length; i++) {
			Double base = unsortedArr[i];
			int j = i - 1;
			while (unsortedArr[j] > base && j >= 0) {
				unsortedArr[j + 1] = unsortedArr[j];
				j -= 1;
			}
			unsortedArr[j + 1] = base;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Double[] unsortedArr = (Double[]) numObj;
		insertion(unsortedArr);
		return (E) unsortedArr;
	}

}
