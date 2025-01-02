package calculator.varMap.unary.funcary.unary;

import calculator.SolveEquations;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperation;
import calculator.varMap.unary.funcary.binary.Differentiate;

public class Increasing extends UnaryOperation {

	public Increasing() {
		super("isincreasing");
	}

	public static Integer monotonicity(UDFunc func, Double[] range) {
		// determine if the differential has root, which means that there is no
		// stationary point
		DynArray<Double> dynarr = SolveEquations.newtonMethod(func, range, Functions.diff(1), Functions.diff(2));
		boolean isInflection = false;
		if (dynarr.size() == 1) {
			DynArray<Double> diff2 = SolveEquations.newtonMethod(func, range, Functions.diff(2), Functions.diff(3));
			Double x = Functions.sf(dynarr.get(0), 5);
			// need to consider the case where the stationary point is a point of inflection
			for (int i = 0; i < diff2.size(); i++) {
				// it means that the stationary point is a point of inflection, then it doesn't
				// affect the function being a monotonic function in the give range
				if (x == Functions.sf(diff2.get(i), 5)) {
					isInflection = true;
					break;
				}
			}
		}
		/*
		 * determine whether it is decreasing or increasing by substituting values into
		 * the differential. If the result is positive, it is increasing, otherwise, it
		 * is decreasing
		 */
		if (dynarr.size() == 0 || isInflection) {
			FuncOpInfo param = new FuncOpInfo();
			// need to be careful not to choose the point of inflection otherwise the
			// differentiation result will be very close to/equal to zero, causing confusion
			param.set(dynarr.size() != 0 ? dynarr.get(0) + 1.0 : 1.0);
			// increasing
			if ((Double) new Differentiate().differentiate(func, param) > 0) {
				return 1;
			}
			// decreasing
			else {
				return -1;
			}
		}
		return 0;
	}

	public static Boolean isIncreasing(UDFunc func, Double[] range) {
		Integer outcome = monotonicity(func, range);
		return outcome == 1 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) isIncreasing((UDFunc) dynarr.get(0), (Double[]) dynarr.get(1));
	}

}
