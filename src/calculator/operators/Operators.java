package calculator.operators;

import calculator.opHandlers.FuncOp;

//This class is less useful. It aggregates the sub-classes of AbstrOperators and return the priority of a particular key
public class Operators {
	private static UnaryOperators unaryOp=new UnaryOperators();
	private static BinaryOperators binaryOp=new BinaryOperators();
	private static FunctionOperators functionOp;

	public Operators setFunctionOp(FunctionOperators funcOp) {
		functionOp = funcOp;
		return this;
	}

	public static Integer getUnary(String var) {
		return unaryOp.get(var);
	}

	public static Integer getBinary(String var) {
		return binaryOp.get(var);
	}

	public static FuncOp getFunction(String var) {
		if (functionOp != null) {
			return functionOp.get(var);
		}
		return null;
	}
}
