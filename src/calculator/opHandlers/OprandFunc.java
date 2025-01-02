package calculator.opHandlers;

import java.util.Optional;

import calculator.SymbolType.ST;
import calculator.complex.ComplexNum;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.main.originators.VarManager;

public class OprandFunc {
	private static HashMapS<Object> varMap;
	private static HashMapS<Object> specVals = HashMapS.createHashMap(new String[] { "e", "φ", "π", "i" },
			new Object[] { Math.E, (1 + Math.pow(5, 0.5)) / 2, Math.PI, ComplexNum.i() });

	public OprandFunc(HashMapS<Object> givenVarMap) {
		initVarMap(givenVarMap);
	}

	public OprandFunc(VarManager manager) {
		initVarMap(manager.getVarMap());
	}

	public OprandFunc() {
	}

	public static void initVarMap(HashMapS<Object> givenVarMap) {
		HashMapS<Object> spare;
		spare=varMap==null?initiateVarMap():varMap;
		varMap = Optional.ofNullable(givenVarMap).orElse(spare);
	}

	public static boolean isOprand(String str) {
		return getOprVal(str) == null ? false : true;
	}

	public static boolean isOprand(ST type) {
		return type == ST.num || type == ST.var || type == ST.fopr;
	}

	// static method
	public static Object getOprVal(String str, HashMapS<Object> varMap) {
		Object val = OprandFunc.getOprVal(str);
		// special values has higher priority
		if (val != null) {
			return val;
		}
		Object val2 = varMap.get(str);
		if (val2 != null) {
			return val2;
		}
		return null;
	}

	// for the string that is either variable or a numerical value
	public static Object getNonVarOprVal(String str) {
		if (Functions.isNum(str)) {
			return Double.valueOf(str);
		}
		return specVals.get(str);
	}

	// non-static method
	public static Object getOprVal(String str) {
		if (Functions.isNum(str)) {
			Double.valueOf(str);
		}
		return varMap.get(str);
	}

	// add special values eg. pi, e, to the varMap
	public static HashMapS<Object> initiateVarMap() {
		return specVals.clone();
	}
}
