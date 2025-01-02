package calculator.varMap.unary.funcary.unary;

import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class Monotonicity extends UnaryOperation {

	public Monotonicity() {
		super("monotonicity");
	}

	// Double[] is the x-range; MonoType determines whether it is increasing,
	// decreasing or neither
	public static DynArray<DataPack<MonoType>> monotonicity(UDFunc func, Double[] range) {
		DynArray<DataPack<StyType>> coords = FindExtremum.findExtremum(func, range);
		// add the range into the coords dynamic array
		coords.insert(new DataPack<>(range[0], null, null), 0);
		coords.add(new DataPack<>(range[1], null, null));

		DynArray<DataPack<MonoType>> ranges = new DynArray<>(coords.size());
		MonoType[] types = { MonoType.increasing, MonoType.decreasing };

		// likely that it is strictly increasing/decreasing in the given range
		if (coords.size() == 0) {
			Integer result = Increasing.monotonicity(func, range);
			MonoType type;
			if (result > 0) {
				type = types[0];
			}
			// in this case the function is parallel to x-axis because it has neither
			// extremums nor monotonicity
			else if (result == 0) {
				type = MonoType.none;
			} else {
				type = types[1];
			}
			ranges.add(new DataPack<>(range[0], range[1], type));
			return ranges;
		}
		// this index is used to traverse the types array
		int idx = 0;
		if (coords.get(1).type == StyType.min) {
			idx = 1;
		}
		for (int i = 1; i < coords.size(); i++) {
			// num1 is the x-coordinate of the extremum point
			ranges.add(new DataPack<>(coords.get(i - 1).num1, coords.get(i).num1, types[idx++ % types.length]));
		}
		return ranges;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) monotonicity((UDFunc) dynarr.get(0), (Double[]) dynarr.get(1));
	}

}
