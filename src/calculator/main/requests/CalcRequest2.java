package calculator.main.requests;

import calculator.ErrorCodes;
import calculator.funcTools.Clone;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;

/*
 * @Description: this class is the request class. It encapsulates the data needed for the command classes
 */
public class CalcRequest2 {
	private String expr;
	private String[] vars;
	private DynArray<Object[]> data;

	private void check() {
		// if the number of variables doesn't match the number of values
		if (vars != null && data != null && vars.length != data.get(0).length) {
			System.out.println(ErrorCodes.get("21"));
			System.exit(0);
		}
		// check variable validity
		Functions.checkVarsFormat(this.vars);
	}

	private void exprCheck() {
		if (expr == null) {
			System.out.println(ErrorCodes.get("23") + " the expression is null!");
			System.exit(0);
		}
		// format the expression
		expr = Functions.formatExpr(expr);
	}

	// multiple variables: data has the format: dynarr ->
	// [{a1,a2,...,an},{b1,b2,...,bn},...,{z1,z2,...,zn}]
	public CalcRequest2(String expr, String[] vars, DynArray<Object[]> input) {
		this.expr = expr;
		exprCheck();
		content(vars, input);
	}

	// for expressions with no variables
	public CalcRequest2(String expr) {
		this.expr = expr;
		exprCheck();
	}

	// single variable: data has the format: [a1,a2,...,an]
	public CalcRequest2(String expr, String var, Object[] input) {
		this.expr = expr;
		exprCheck();
		DynArray<Object[]> dynarr = new DynArray<>(1);
		dynarr.add(input);
		content(new String[] { var }, dynarr);
	}

	// method chaining
	public CalcRequest2 content(String[] vars, DynArray<Object[]> input) {
		this.vars = vars;
		this.data = input;
		check();
		return this;
	}

	public String getExpr() {
		return this.expr;
	}

	//the methods for multi-calc
	public String[] getVars() {
		if (vars != null) {
			return Clone.clone(vars);
		}
		return null;
	}

	public DynArray<Object[]> getDataList() {
		return data.ROM(true);
	}
}
