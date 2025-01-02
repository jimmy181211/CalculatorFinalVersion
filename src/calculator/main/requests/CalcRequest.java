package calculator.main.requests;

import calculator.ErrorCodes;
import calculator.funcTools.Clone;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;

public final class CalcRequest {
	private String expr;
	private String[] vars;
	private DynArray<Object[]> data;

	private void check() {
		if (expr == null) {
			System.out.println(ErrorCodes.get("23") + " the expression is null!");
			System.exit(0);
		}
		// format the expression
		expr = Functions.formatExpr(expr);
		// if the number of variables doesn't match the number of values
		if (vars != null && data != null && vars.length != data.get(0).length) {
			System.out.println(ErrorCodes.get("21"));
			System.exit(0);
		}
		// check variable validity
		Functions.checkVarsFormat(this.vars);
	}

	// data has the format: dynarr ->
	// [{a1,a2,...,an},{b1,b2,...,bn},...,{z1,z2,...,zn}]
	public CalcRequest(String expr, String[] vars, DynArray<Object[]> input) {
		this.expr = expr;
		content(vars, input);
		check();
	}

	// for expressions with no variables
	public CalcRequest(String expr) {
		this.expr = expr;
		check();
	}

	// data has the format: [a1,a2,...,an]
	public CalcRequest(String expr, String var, Object[] input) {
		this.expr = expr;
		DynArray<Object[]> dynarr = new DynArray<>(1);
		dynarr.add(input);
		content(new String[] { var }, dynarr);
		check();
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

	// delay the full instantiation, chaining method
	// vars: either String or String[]; input: either DynArray<Object[]> or Object[]
	public CalcRequest content(String[] vars, DynArray<Object[]> input) {
		// change format
		DynArray<Object[]> data = new DynArray<>(input.get(0).length);
		Object[] dataRow = new Object[input.size()];
		try {
			for (int i = 0; i < input.get(0).length; i++) {
				for (int j = 0; j < input.size(); j++) {
					dataRow[j] = input.get(j)[i];
				}
				data.add(dataRow);
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.get("24") + " the number of values for each variable are not the same");
			System.exit(0);
		}
		this.data = data;
		return this;
	}
}
