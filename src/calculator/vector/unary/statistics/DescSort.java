package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class DescSort extends UnaryOperation{

	public DescSort() {
		super("desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Double[] arr = (Double[]) numObj;
		Sort.insertionSort(arr,true);
		return (E) arr;
	}

}
