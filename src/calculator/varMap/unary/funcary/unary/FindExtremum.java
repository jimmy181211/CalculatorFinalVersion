package calculator.varMap.unary.funcary.unary;

import calculator.ErrorCodes;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperation;
import calculator.varMap.unary.funcary.binary.Differentiate;

public class FindExtremum extends UnaryOperation {

	public FindExtremum() {
		super("findMaxMin");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) findExtremum((UDFunc) dynarr.get(0), (Double[]) dynarr.get(1));
	}

	public static DynArray<DataPack<StyType>> findExtremum(UDFunc func, Double[] range) {
		return findStationaries(func, range, true);
	}

	public static DynArray<DataPack<StyType>> findStationaries(UDFunc func, Double[] range, boolean isExtremum) {
		DynArray<DataPack<StyType>> inflects = new DynArray<>(2), extremums = inflects.clone();
		DynArray<Double> stationaries = FindStationary.findStationaries(func, range);

		FuncOpInfo param = new FuncOpInfo();
		Double result = 0.0;
		StyType type;
		DynArray<DataPack<StyType>> receiver;
		// preparation for the calculation of y-values
		func.update(param);

		// test if it is maximum, minimum, or inflection point
		for (int i = 0; i < stationaries.size(); i++) {
			param.set(stationaries.get(i));
			try {
				result = (Double) new Differentiate().differentiate(func, param, 2);
			}
			// if can't cast to Double:
			catch (Exception e) {
				System.out.println(ErrorCodes.get("05") + ": find extremum function can only do singleCalc not multiCalc");
				System.exit(0);
			}
			receiver = extremums;
			// if is minimum
			if (result > Math.pow(10, -3)) {
				type = StyType.min;
			}
			// if is maximum
			if (result < -Math.pow(10, -3)) {
				type = StyType.max;
			}
			// if is inflection
			else {
				type = StyType.inflect;
				receiver = inflects;
			}
			receiver.add(new DataPack<>((Double) param.get(), func.operate(null), type));
		}

		return isExtremum ? extremums : inflects;
	}
}
