package calculator.func.distributions.negbinomial;

import calculator.func.distributions.DistribType;
import calculator.numeric.binary.Combination;

public class OrigNegBinomial extends DistribType {
	// params[0]:success; params[1]:p
	public OrigNegBinomial(Double[] params) {
		super(params);
	}

	public static Double pd(int success, double p, int x) {
		Double pqTerms = Math.pow(p, success) * Math.pow(1 - p, x - success);
		return Combination.combination(x - 1, success - 1) * pqTerms;
	}

	// P(X<=x)
	public static Double cd(int success, double p, int x) {
		Double sum = 0.0;
		for (int i = success; i <= x; i++) {
			sum += pd(success, p, i);
		}
		return sum;
	}

	@Override
	public Object cFunc(Double x) {
		isValid(x);
		if (!isEqual) {
			x--;
		}
		return cd(params[0].intValue(), params[1], x.intValue());
	}

	@Override
	public Object pFunc(Double x) {
		isValid(x);
		return pd(params[0].intValue(), params[1], x.intValue());
	}

	public Double suc() {
		return params[0];
	}

	public Double p() {
		return params[1];
	}

	@Override
	public Double getExp() {
		return params[0]/params[1];
	}

	@Override
	public Double getVariant() {
		return params[0]*(1-params[1])/Math.pow(params[1], 2);
	}
}
