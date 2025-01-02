package calculator.opHandlers.handleOp.concreteHandlers;

import calculator.func.FuncType.Type;
import calculator.funcTools.Functions;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.handleOp.FuncHandler;

public class UDFHandler extends FuncHandler {
	private boolean isPolar=false;
	private String isPolar(String str) {
		if (Functions.lower(str).contains("polar")) {
			isPolar=true;
			str=Functions.replace(str, "polar", "");
		} 
		return str;
	}

	// input[0]: expression; input[1]: funcName; input[2]: params;
	@Override
	public FuncOp handle(String[] input) {
		isPolar(input[0]);
		isPolar(input[1]);
		String[] vars = getParams(input[2]);
		Functions.checkVarsFormat(vars);
		// parameters, expression
		FuncOp funcOp = new FuncOp();
		funcOp.build(Type.UDF, null, vars, new Object[] { input[0], isPolar });
		return funcOp;
	}
}
