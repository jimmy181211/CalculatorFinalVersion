package calculator.opHandlers.handleVar.concreteHandlers;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.handleVar.VarHandler;

public class VarMapHandler extends VarHandler<HashMapS<Object>> {
	private HashMapS<Object> varMap;
	/*
	 * the oprand has the same form as vector, so after VecHandler processes the
	 * data, if it can't process it, sign is true, the data will be passed to this
	 * handler. Else it is false, the data will be directly dispatched to
	 * MatrixHandler
	 */
	public static boolean signal = false;

	public VarMapHandler(HashMapS<Object> varMap) {
		this.varMap = varMap;
	}

	@Override
	public HashMapS<Object> handle(String str) {
		if (!signal) {
			return null;
		}
		if (str.charAt(0) == '[') {
			str = str.substring(1, str.length() - 1);
		}
		HashMapS<Object> hashMap = new HashMapS<>();
		DynArray<String> vars = Functions.splits(str, ",");
		Object val;
		String e;
		for (int i = 0; i < vars.size(); i++) {
			e = vars.get(i);
			val = varMap.get(e);
			// it's likely that it is a number in this case
			if (val == null) {
				hashMap.put(e, Double.valueOf(e));
			} else {
				hashMap.put(e, val);
			}
		}
		signal = false;
		return hashMap;
	}

}
