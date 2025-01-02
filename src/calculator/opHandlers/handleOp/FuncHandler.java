package calculator.opHandlers.handleOp;

import calculator.AbstrHandler;
import calculator.funcTools.Functions;

public abstract class FuncHandler extends AbstrHandler<String[]> {
	// str is in form of: a,b) because the bracket was taken in advance
	protected static String[] getParams(String param) {
		String str = Functions.split(param, ")")[0];
		String[] vars = Functions.split(str.substring(0, str.length() - 1), ",");
		return vars;
	}
}
