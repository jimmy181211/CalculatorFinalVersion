package calculator.varMap.unary.funcary.unary;

import calculator.SolveEquations;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class FindStationary extends UnaryOperation {

	public FindStationary() {
		super("stationary");
	}

	public static DynArray<Double> findStationaries(UDFunc func,Double[] range) {
		return SolveEquations.newtonMethod(func, range, Functions.diff(1), Functions.diff(2));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		return (E)findStationaries((UDFunc)dynarr.get(0),(Double[])dynarr.get(1));
	}

}
