package calculator.opHandlers.handleVar.concreteHandlers;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.opHandlers.handleVar.VarHandler;

public class VecHandler extends VarHandler<Double[]> {

	@Override
	public Double[] handle(String str) {
		if (str.charAt(0) == '[') {
			// remove the brackets
			str = str.substring(1, str.length() - 1);
		}
		// this is not a vector, it is a matrix instead
		if (str.charAt(0) == '[') {
			return null;
		}
		DynArray<String> dynarr = Functions.splits(str, ",");
		// if it is not a vector
		if (dynarr.size() == 1) {
			return null;
		}
		Double[] result = new Double[dynarr.size()];
		for (int i = 0; i < dynarr.size(); i++) {
			try {
				result[i] = (Double) prevHandler.handle(dynarr.get(i));
			} catch (Exception e) {
				// if the data stored is not a number, then varMapHandler is used instead
				VarMapHandler.signal = true;
			}
		}
		return result;
	}
}
