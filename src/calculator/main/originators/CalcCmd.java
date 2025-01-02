package calculator.main.originators;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.main.RPNCalc;
import calculator.main.requests.CalcRequest2;
	
public class CalcCmd {
	protected static RPNCalc rpnObj;
	
	public CalcCmd(HashMapS<Object> varMap) {
		CalcCmd.rpnObj = new RPNCalc(varMap);
	}
	
	public static Object calcInner(String expr) {
		expr = Functions.formatExpr(expr);// lexical analysis
		return rpnObj.calcSingle(expr);
	}
	
	public Object calc(CalcRequest2 request) {
		return calcInner(request.getExpr());
	}

	public Object calcVar(CalcRequest2 request) {
		rpnObj.getVarMap().put(request.getVars()[0], request.getDataList().get(0)[0]);
		return calcInner(request.getExpr());
	}

	public Object calcMultiVars(CalcRequest2 request) {
		String expr = request.getExpr();
		String[] vars = request.getVars();
		DynArray<Object[]> vals = request.getDataList();

		return rpnObj.calcMulti2(expr, vars, vals);
	}
	
	public void setVarMap(HashMapS<Object> varMap) {
		CalcCmd.rpnObj.setVarMap(varMap);
	}
}
