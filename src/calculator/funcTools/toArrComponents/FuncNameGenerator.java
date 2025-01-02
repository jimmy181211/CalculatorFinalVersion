package calculator.funcTools.toArrComponents;

import calculator.funcTools.Functions;

public interface FuncNameGenerator {
	static int[] cnts = new int[26];

	// this is for the functional variables, to ensure the uniqueness of each
	// variable
	public static String generate(String op) {
		Character var = Functions.upper(op.charAt(0));
		int idx = var - 65;
		return var + Integer.toString(cnts[idx]++);
	}
}
