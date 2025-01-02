package calculator.func.distributions.binomial;

import java.util.function.BiFunction;

import calculator.func.distributions.InvDistribType;
import calculator.funcTools.TriFunction;

public final class InvBinomial extends InvDistribType {

	public InvBinomial(Double[] params) {
		super(params);
	}

	public static Double[] cFunc(Integer n, Double p, Double pX, boolean distribOpt, boolean isEqual) {
		// choosing comparing mode (< or <=)
		BiFunction<Double, Double, Boolean> compare = compare(isEqual);
		// choosing distribFunc: cd or pd
		TriFunction<Integer, Double, Integer, Double> distribFunc = distribCalc(distribOpt);

		int exp = (int) (n * p);// the expectation
		Double[] result = new Double[2];// store the result

		// from expectation of X to 0
		for (int i = exp; i >= 0; i--) {
			if (compare.apply(distribFunc.apply(n, p, i), pX)) {
				result[0] = (double) i;
				break;
			}
		} // from expectation of X to n
		for (int i = exp + 1; i <= n; i++) {
			if (compare.apply(distribFunc.apply(n, p, i), pX)) {
				result[1] = (double) i;
				break;
			}
		}
		return result;
	}

	protected static TriFunction<Integer, Double, Integer, Double> distribCalc(boolean funcOpt) {
		return funcOpt ? (n, p, x) -> OrigBinomial.cd(n, p, x) : (n, p, x) -> OrigBinomial.pd(n, p, x);
	}

	@Override
	public Double[] cFunc(Double x) {
		isValid(x);
		return cFunc(params[0].intValue(),params[1],x,true,isEqual);
	}

	@Override
	public Double[] pFunc(Double x) {
		isValid(x);
		return cFunc(params[0].intValue(),params[1],x,false,isEqual);
	}
}
