package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class Random extends UnaryOperation {

	public Random() {
		super("rand");
	}

	@SuppressWarnings("unchecked")
	// numObj: DynArray<>(a,b), the lower bound and the upper bound of the random
	// number respectively
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Double> dynarr = (DynArray<Double>) numObj;
		Double lower = dynarr.get(0);
		Double upper = dynarr.get(1);
		Double result = Math.random() * (upper - lower) + lower;
		return (E) result;
	}

}
