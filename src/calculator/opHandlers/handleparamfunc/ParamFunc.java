package calculator.opHandlers.handleparamfunc;

import calculator.opHandlers.ParamFuncHandler;

/*
 * @version 2.0
 * @Description: this class is separate from ParamFuncHandler class. what it does:
 * 1. It stores two InitParamFunc objects: unary and binary
 * 2. It determines whether an operator: String is of type paramFunc by looping through the paramFunc list
 * 3. It encapsulates the setter and getter methods of isType from InitParamFunc class
 */
public final class ParamFunc {
	protected static InitParamFunc paramFuncsBin = new InitBinaryFunc();
	protected static InitParamFunc paramFuncsUn = new InitUnaryFunc();

	public static boolean isParamFunc(String op, boolean isUn) {
		InitParamFunc paramFuncs = isUn ? paramFuncsUn : paramFuncsBin;
		for (int i = 0; i < paramFuncs.getParamFuncs().size(); i++) {
			if (op.equals(paramFuncs.getParamFuncs().get(i).operator)) {
				if (isUn) {
					paramFuncsUn.isType = true;
				} else {
					paramFuncsBin.isType = true;
				}
				return true;
			}
		}
		setTypes(false);
		return false;
	}

	public static ParamFuncHandler checkIsTypeInit() {
		if (paramFuncsUn.isType == null || paramFuncsBin.isType == null) {
			new Exception(
					"isUn or isBin variable is not initialised! Please call isParamFunc() method before using the addTo() method");
		}
		return new ParamFuncHandler();
	}

	public static Boolean isBinType() {
		return paramFuncsBin.isType;
	}

	public static Boolean isUnType() {
		return paramFuncsUn.isType;
	}

	private static void setTypes(Boolean isType) {
		paramFuncsUn.isType = isType;
		paramFuncsBin.isType = isType;
	}

	public static void setTypesNull() {
		setTypes(null);
	}
}
