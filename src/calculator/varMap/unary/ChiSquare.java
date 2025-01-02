package calculator.varMap.unary;

import calculator.func.FuncOperation;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperation;

public class ChiSquare extends UnaryOperation {
	//degree of freedom
	private static Integer df;

	public ChiSquare() {
		super("chisquare");
		// TODO Autcho-generated constructor stub
	}

	// regression:UDFunc
	// elements in expected and xVals are in ascending order
	public static Double chiSquare(FuncOperation regression, Double[] expected, Double[] xVals) {
		if (xVals.length != expected.length) {
			System.out.println(errors.getErr("24") + " when doing chiSquare calculation!");
			System.exit(0);
		}
		// if a value in expected is smaller than 5, combine the columns
		DynArray<Double> combinedExp = new DynArray<>(expected.length);
		DynArray<DynArray<Double>> combinedXVals = new DynArray<>(expected.length);

		int j = 0;
		for (int i = 0; i < expected.length; i++) {
			combinedXVals.add(new DynArray<Double>(2));
			combinedXVals.get(j++).add(xVals[i]);
			while (expected[j] < 5) {
				expected[j] += expected[i + 1];
				combinedXVals.get(j).add(xVals[i + 1]);
				i++;
			}
			combinedExp.add(expected[j]);
		}
		/*
		 * find the minimum value of the expected list, if it is too large, then reduce
		 * all the values down to order of two
		 */
		Double min = Double.MAX_VALUE;
		for (int i = 0; i < combinedExp.size(); i++) {
			if (combinedExp.get(i) < min) {
				min = combinedExp.get(i);
			}
		}
		Double reduceFactor = 1.0;
		if (min > 100) {
			reduceFactor = min / 10.0;
		}
		reduceFactor = Functions.sf(reduceFactor, 1);
		for (int i = 0; i < combinedExp.size(); i++) {
			// reduce each element by the reduceFactor
			combinedExp.replace(combinedExp.get(i) / reduceFactor, i);
		}
		// calculate the ChiSquare using the formula: sigma((E-O)^2/E)
		Double chiSquare = 0.0;
		Double observed;
		for (int i = 0; i < combinedExp.size(); i++) {
			// calc observed value
			observed = 0.0;
			for (int k = 0; k < combinedXVals.size(); k++) {
				// format of the regression formula is checked so that the string doesn't need
				// to be validated
				observed += (Double) regression.operate(new FuncOpInfo(regression.getVars()[0], combinedXVals.get(k)));
			}
			chiSquare += Math.pow(combinedExp.get(i) - observed, 2) / combinedExp.get(i);
		}
		// set degree of freedom
		df = combinedExp.size() - 1;
		return chiSquare;
	}

	public static Integer getDegree() {
		return df;
	}

	@SuppressWarnings("unchecked")
	@Override // numObj: [regression, expected values, xVals]
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) chiSquare((FuncOperation) dynarr.get(0), (Double[]) dynarr.get(1), (Double[]) dynarr.get(2));
	}

}
