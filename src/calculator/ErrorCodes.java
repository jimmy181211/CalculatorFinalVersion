package calculator;

import calculator.funcTools.AVLTree2;

public class ErrorCodes {
	/*
	 * errorCode:errorMessage 0 denotes a series of operation errors 1 indicates the
	 * format errors 2 reflects all the parameter type errors + operation between
	 * two number of different types (20)
	 */
	private static AVLTree2<String> addErrorCodes() {
		AVLTree2<String> errors = new AVLTree2<>();
		errors.put("00", "out of domain");
		errors.put("01", "opeartion %s not found");
		errors.put("02", "undefined variable/operator");
		errors.put("03", "incorrect use of operator");
		errors.put("04", "oprands %s not matched");
		errors.put("05", "invalid oprand type");
		errors.put("06", "zero error");
		errors.put("07", "oprand too big");
		errors.put("10", "invalid bracket");
		errors.put("11", "invalid distribution notation");
		errors.put("12", "incomplete calculus notations");
		errors.put("13", "invalid symbol");
		errors.put("14", "parameter not in range");
		errors.put("15", "invalid format of matrix/matrices");
		errors.put("16", "incorrect format");
		errors.put("17", "key-value dismatch");
		errors.put("20", "incorrect type of parameter");
		errors.put("21", "key-value dismatch");
		errors.put("22", "incorrect number of parameters");
		errors.put("23", "null error");
		errors.put("24", "parameter not matching");
		errors.put("25", "can't solve the equation");
		return errors;
	}

	private final static AVLTree2<String> errors = addErrorCodes();

	public String getErr(String code) {
		return (String) errors.get(code);
	}

	public static String get(String code) {
		return (String) errors.get(code);
	}

	public static void main(String[] args) {
		System.out.println(get("23"));
	}
}
