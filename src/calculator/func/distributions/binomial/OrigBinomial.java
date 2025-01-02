package calculator.func.distributions.binomial;

import calculator.func.distributions.DistribType;
import calculator.func.distributions.poisson.OrigPoisson;
import calculator.numeric.binary.Combination;

public class OrigBinomial extends DistribType {
	// params[0]=n, params[1]=p;
	public OrigBinomial(Double[] params) {
		super(params);
	}

	// binomial distribution discrete
	public static Double pd(int n, double p, int x) {
		// derived from the formula: nCr*p^r*(1-p)^(n-r)
		if (n >= 100 && n * p <= 10) {
			return OrigPoisson.pd((double) n * p, x);
		}
		return Combination.combination(n, x) * Math.pow(p, x) * Math.pow(1 - p, n - x);
	}

	// P(X<=x)
	public static Double cd(int n, double p, int x) {
		Double sum = 0.0;
		for (int i = 0; i <= x; i++) {
			sum = sum + pd(n, p, i);
		}
		return sum;
	}

	@Override
	public Double pFunc(Double x) {
		isValid(x);
		return pd(params[0].intValue(), params[1], x.intValue());
	}

	@Override
	public Double cFunc(Double x) {
		isValid(x);
		if (!isEqual) {
			x--;
		}
		return cd(params[0].intValue(), params[1], x.intValue());
	}

	public Double n() {
		return params[0];
	}

	public Double p() {
		return params[1];
	}

	@Override
	public Double getExp() {
		return params[0]*params[1];
	}

	@Override
	public Double getVariant() {
		return params[0]*(1-params[1])*params[1];
	}
}
