package calculator.opHandlers.handleOp.concreteHandlers;

import calculator.ErrorCodes;
import calculator.func.FuncType.Type;
import calculator.funcTools.Functions;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.OprandFunc;
import calculator.opHandlers.handleOp.FuncHandler;

public final class DistribHandler extends FuncHandler {
	@Override
	public FuncOp handle(String[] input) {
		// consider the right-hand-side being a distribution declaration. eg:
		// X~B(a,b)
		String[] parts = Functions.split(input[0], "~");

		// if is not a distribution type entity
		if (parts.length == 1) {
			return null;
		}
		// strArr contains, eg: for parts being B(a,b), strArr is: {"B", "a,b)"}
		String[] strArr = Functions.split(parts[1], "(");
		String[] vars = new String[] { parts[0] };
		String[] params = getParams(strArr[1]);
		Double[] paramsD = new Double[params.length];

		// check if the parameters are all valid operands
		for (int i = 0; i < params.length; i++) {
			if (!OprandFunc.isOprand(params[i])) {
				System.out.println(ErrorCodes.get("20") + " when appending user-defined operators");
				System.exit(0);
			}
			paramsD[i] = (Double) OprandFunc.getOprVal(params[i]);
		}
		FuncOp funcOp = new FuncOp();
		funcOp.build(Type.Distrib, Functions.upper(strArr[0]), vars, paramsD);
		return funcOp;
	}

}
