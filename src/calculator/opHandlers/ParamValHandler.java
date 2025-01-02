package calculator.opHandlers;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.main.RPNCalc;

public class ParamValHandler {
	// a parameter can be: param = e. Here, e is the 'str' that we need to handle
	// it's possible that 'str' is an expression, eg: param = e + 3 * i
	// also, 'str' have be an array: param = (1, 4, 3, 6), where 'str' is (1, 4, 3,
	// 6)
	private static Boolean isMulti = false;

	public static boolean isMulti() {
		isMulti = false;
		return isMulti;
	}

	public static Object handle(String str, final HashMapS<Object> varMap) {
		return handle(str, varMap, false);
	}

	public static Object handle(String str, final HashMapS<Object> varMap, boolean isExpr) {
		// when the value (in variable form) exist
		Object result = OprandFunc.getOprVal(str, varMap);
		// The 'str' may be an expression that is to be evaluated
		if (result == null) {
			try {
				result = new RPNCalc(varMap.clone()).checked().calcSingle(str);
			}
			// if catch any Exception, it means that str is not a valid expression
			catch (Exception e) {
				// in this mode, the 'invalid' expression might not be evaluable, it is an Expr
				if (isExpr) {
					return str;
				}
				System.out.println("The parameter " + str + " is not a valid expression!");
				result = null;
			}
		}
		if (result != null) {
			return result;
		}
		// for the form: X=(a,b,c,d,...), where var: X; str: (a,b,c,d,...)
		DynArray<String> vals = Functions.splitsProtected(str, ",", '(');
		if (vals.size() != 1) {
			// get rid of the '(' and ')'
			vals.replace(Functions.replace(vals.get(0), "(", ""), 0);
			vals.replace(Functions.replace(vals.get(vals.size() - 1), ")", ""), vals.size() - 1);
			DynArray<Object> results = new DynArray<>(vals.size());
			for (int i = 0; i < vals.size(); i++) {
				results.add(handle(vals.get(i), varMap));
			}
			isMulti = true;
			return results;
		}
		// for integration, the parameter is not a number, it is in form of x = (a->b)
		// instead of x=a
		else if (str.contains("->")) {
			// get rid of the brackets
			String temp = Functions.replace(str, "(", "");
			temp = Functions.replace(temp, ")", "");
			String[] strVals = Functions.split(temp, "->");
			try {
				return new Double[] { (Double) handle(strVals[0], varMap), (Double) handle(strVals[1], varMap) };
			}
			/*
			 * if the compiler can't cast from Object to Double, it means that the original
			 * data is not a numerical value eg: it is {1,2,3} instead of 1, which is
			 * invalid to be an integral boundary. Hence throw an Error and exit the program
			 */
			catch (Exception e) {
				System.out
						.println(ErrorCodes.get("05") + ": the boundaries for an Integral should both be numerical values");
				System.exit(0);
				return null;
			}
		} else {
			System.out.println(ErrorCodes.get("13") + str);
			System.exit(0);
			return null;
		}
	}
}
