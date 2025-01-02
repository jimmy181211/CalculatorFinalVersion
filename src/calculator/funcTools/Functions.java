package calculator.funcTools;

import java.math.BigDecimal;

import calculator.ErrorCodes;
import calculator.func.FuncOperation;
import calculator.opHandlers.FuncOpInfo;
import calculator.opHandlers.OprandFunc;
import calculator.operators.Operators;
import calculator.varMap.unary.funcary.binary.Differentiate;

public class Functions {
	private static Differentiate diffObj = new Differentiate();
	
	public static boolean isNum(char chr) {
		return IsNum.isNum(chr);
	}

	public static boolean isNum(String str) {
		return IsNum.isNum(str);
	}
	public static BiFunction<FuncOperation, Double, Double> diff(int num) {
		return (func, val) -> {
			FuncOpInfo param=new FuncOpInfo();
			param.set(val);
			try {
				return (Double)diffObj.differentiate(func, param ,num);
			}
			catch(Exception e) {
				System.out.println("the calculator doesn't support multi-value equation solving or returning non-numerical values");
				System.exit(0);
				return null;
			}
		};
	}

	public static <E> String toString(E[][] arr, boolean isVertical) {
		return ToString.toString(arr, isVertical);
	}

	public static <E> String toString(E[] arr) {
		return ToString.toString(arr);
	}

	public static boolean isNum(StringBuilder strbuilder) {
		return IsNum.isNum(strbuilder);
	}

	public static String removeSpaces(String str) {
		String newStr = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != ' ') {
				newStr += ch;
			}
		}
		return newStr;
	}

	public static boolean contains(String str, char target) {
		return Split.contains(str, target);
	}

	public static boolean contains(String str, String target) {
		return Split.contains(str, target);
	}

	public static String replaceFirst(String str, String replaced, String replacement) {
		if (!Split.contains(str, replaced)) {
			return str;
		}
		CharSequence[] comps = Functions.split(str, replaced);
		return comps[0] + replacement + comps[1];
	}

	public static String replace(String str, String replaced, String replacement) {
		if (!Split.contains(str, replaced)) {
			return str;
		}
		String[] comps = Functions.split(str, replaced);
		if (comps.length <= 1) {
			return comps[0];
		}
		return comps[0] + replacement + replace(comps[1], replaced, replacement);
	}

	// determine how many decimal places there are
	public static int dp(String strNum) {
		return Dp.dp(strNum);
	}

	// override
	public static int dp(Double num) {
		return dp(Double.toString(num));
	}

	// change it to the number of decimal places
	public static double toDp(Double num, int d_p) {
		return Dp.toDp(num, d_p);
	}

	// sf can't be bigger than 20 as that is bigger than the maximum number that
	// long type can hold
	public static double sf(double num, int sf) {
		return SignificantFigure.sf(num, sf);
	}

	public static double sf(BigDecimal num, int sf) {
		return sf(num.doubleValue(), sf);
	}

	// overload
	public static double sf(String strNum, int sf) {
		return sf(Double.parseDouble(strNum), sf);
	}

	public static String[] split(String str, String target) {
		return Split.split(str, target);
	}

	public static DynArray<String> splits(String str, String splitter) {
		return Split.splits(str, splitter);
	}

	public static DynArray<String> splitsProtected(String str, String splitter, char protect) {
		return Split.splitsProtected(str, splitter, protect);
	}

	public static char lower(char chr) {
		if (!(chr >= 65 && chr <= 90)) {
			return chr;
		}
		return (char) (chr + 32);
	}

	public static boolean isUpper(char ch) {
		return ch >= 65 && ch <= 90;
	}

	public static boolean isLower(char ch) {
		return ch >= 97 && ch <= 122;
	}

	public static String lower(String str) {
		String lowerStr = "";
		for (int i = 0; i < str.length(); i++) {
			lowerStr += lower(str.charAt(i));
		}
		return lowerStr;
	}

	public static char upper(char chr) {
		// if char is not a smaller case letter, return char
		if (!(chr <= 122 && chr >= 97)) {
			return chr;
		}
		return (char) (chr - 32);
	}

	public static String upper(String str) {
		String upperStr = "";
		for (int i = 0; i < str.length(); i++) {
			upperStr += upper(str.charAt(i));
		}
		return upperStr;
	}

	public static boolean inNum(char ch) {
		return ch >= 48 && ch <= 57 || ch == '.';
	}

	public static int search(Object target, Object[] arr) {
		if (target == null) {
			return -1;
		}
		for (int i = 0; i < arr.length; i++) {
			if (target.equals(arr[i])) {
				return i;
			}
		}
		return -1;
	}

	// if a variable is in any of the arrays listed
	public static Integer[] search(Object target, Object[][] arrays) {
		Integer[] result = new Integer[2];
		if (target == null) {
			return result;
		}
		int val;
		for (int i = 0; i < arrays.length; i++) {
			val = search(target, arrays[i]);
			if (val != -1) {
				result[0] = i;
				result[1] = val;
				return result;
			}
		}
		return result;
	}

	public static boolean isLetter(String letter) {
		for (int i = 0; i < letter.length(); i++) {
			char ch = letter.charAt(i);
			if (!isLetter(ch)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isLetter(char ch) {
		return (65 <= ch && ch <= 90) || (97 <= ch && ch <= 123);
	}

	public static boolean rfind(CharSequence str, String target) {
		return rfind(str, target, str.length());
	}

	public static boolean rfind(CharSequence str, String target, int maxsize) {
		int len = str.length();
		int startpos = len - maxsize;
		// consider when maxsize is bigger than len
		startpos = startpos < 0 ? 0 : startpos;
		for (int i = len - 1; i >= startpos; i--) {
			if (target.equals(str.subSequence(i, len))) {
				return true;
			}
		}
		return false;
	}

	public static Double cleanOverflow(Double a, Double b, Double result) {
		return Functions.toDp(result, Functions.dp(a) + Functions.dp(b));
	}

	public static Double cleanOverflow(Double a, Double b) {
		return Functions.toDp(a * b, Functions.dp(a) + Functions.dp(b));
	}

	public static String[] combineArrs(String[] arr1, String[] arr2) {
		String[] newArr = new String[arr1.length + arr2.length];
		int i = 0;
		for (; i < arr1.length; i++) {
			newArr[i] = arr1[i];
		}
		for (int j = 0; j < arr2.length; j++) {
			newArr[i + j] = arr2[j];
		}
		return newArr;
	}

	public static void checkVarsFormat(String[] vars) {
		for (String var : vars) {
			new OprandFunc();
			// if the variable is a number, or an pre-defined operator don't need to worry
			// about the special values
			// because the new values will cover the pre-setted special values and that is
			// fine
			if (OprandFunc.isOprand(var) || Operators.getBinary(var) != null || Operators.getUnary(var) != null) {
				System.out.println(ErrorCodes.get("16") + " of the variable");
				System.exit(0);
			}
		}
	}

	public static String formatExpr(String expr) {
		// convert the values to Double
		if (!Brackets.isValidBrkt(expr)) {
			System.out.println(ErrorCodes.get("10"));
			System.exit(0);
		}
		return Functions.removeSpaces(expr);
	}
}
