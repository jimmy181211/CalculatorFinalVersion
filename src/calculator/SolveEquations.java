package calculator;

import calculator.func.FuncOperation;
import calculator.funcTools.AbstrHashMap;
import calculator.funcTools.BiFunction;
import calculator.funcTools.DynArray;

public class SolveEquations {
	/*
	 * what should be prevented: divergent, multiple root, and approximation
	 * oscillation
	 */
	// range is the range of x where the root will be detected; x is the starting
	// point to choose
	private static Double newtonIterMethod(FuncOperation expr, Double[] range, BiFunction<FuncOperation, Double, Double> eval,
			BiFunction<FuncOperation, Double, Double> diff) {
		Double x = Math.random() * (range[1] - range[0]) + range[0];
		Double y, temp;
		int cnt = 0;
		do {
			if (cnt++ > 10000) {
				return null;// when the approximation diverges
			}
			temp = x;
			y = eval.apply(expr, x);
			x = x - y / diff.apply(expr, x);
			if (x == -temp) {
				return null;
			}
		} while (Math.abs(y) > 0.001);
		return x;
	}

	public static DynArray<Double> newtonMethod(FuncOperation expr, Double[] range, BiFunction<FuncOperation, Double, Double> eval,
			BiFunction<FuncOperation, Double, Double> diff) {
		AbstrHashMap<Double, Double> rootMap = new AbstrHashMap<>() {
			@Override
			protected int hash(Double key) {
				return key.intValue();
			}
		};
		//use for loop to find multiple roots
		for (int i = 0; i < 20; i++) {
			Double root = newtonIterMethod(expr, range, eval, diff);
			if (root != null) {
				rootMap.put(root, root);
			}
		}
		return rootMap.traverseVal();
	}
}
