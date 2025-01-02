package calculator.varMap.unary.funcary.unary;

import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class Decreasing extends UnaryOperation {

	public Decreasing() {
		super("isdecreasing");
	}

	public static Boolean isDecreasing(UDFunc func, Double[] range) {
		Integer outcome = Increasing.monotonicity(func, range);
		return outcome == -1 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) isDecreasing((UDFunc) dynarr.get(0), (Double[]) dynarr.get(1));
	}

}
