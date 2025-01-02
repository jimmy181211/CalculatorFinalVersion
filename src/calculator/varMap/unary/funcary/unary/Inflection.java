package calculator.varMap.unary.funcary.unary;

import calculator.SolveEquations;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class Inflection extends UnaryOperation{

	public Inflection() {
		super("inflect");
	}
	
	public static DynArray<Double> findInflection(UDFunc func,Double[] range){
		return SolveEquations.newtonMethod(func, range, Functions.diff(1), Functions.diff(2));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) findInflection((UDFunc) dynarr.get(0), (Double[]) dynarr.get(1));
	}

}
