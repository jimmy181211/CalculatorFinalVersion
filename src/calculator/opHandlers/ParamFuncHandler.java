package calculator.opHandlers;

import calculator.funcTools.DynArray;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.handleparamfunc.ParamFunc;

/*
 * @Description: this class is used in toArray()->FunctionComponent() and FuncComponent()
 */
public class ParamFuncHandler {
	public static boolean isParam = false;
	public static String varStore;
	public static String[] comparators = { ">", "<", ">=", "<=", "=" };

	/*
	 * encapsulate the method in ParamFunc sets isUn and isBin of ParamFunc, which
	 * will be used in addTo() method
	 */

	public static boolean isBinaryParamFunc(String op) {
		return ParamFunc.isParamFunc(op, false);
	}

	public static boolean isUnaryParamFunc(String op) {
		return ParamFunc.isParamFunc(op, true);
	}

	/*
	 * the operations whose oprand is UDF these methods are for unary/binary
	 * operators of oprand functions (FuncOp)
	 */
	public static void funcOpInitiate(String op, String var) {
		if (ParamFunc.isBinType()) {
			isParam = true;
			varStore = var;
		}
	}

	public static void addTo(String op, FuncOp func, FuncOpInfo funcInfo, HashMapS<Object> varMap) {
		ParamFunc.checkIsTypeInit();
		DynArray<Object> param = new DynArray<>(2);
		// get the funcOperation object related to the class FuncOp
		param.add(func.getFunc());

		// binTypes are Differentiation and integration
		if (ParamFunc.isBinType()) {
			funcInfo.setVar(varStore, ParamFuncHandler.class);
			param.add(funcInfo);// add FuncOpInfo
			isParam = false;
		} else if (ParamFunc.isUnType()) {
			param.add(funcInfo);
		} else {
			// if it is not a paramFunction, just ignore it.
			return;
		}
		ParamFunc.setTypesNull();
		varMap.put(op, param);
	}
}
