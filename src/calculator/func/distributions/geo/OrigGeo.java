package calculator.func.distributions.geo;

import calculator.func.distributions.DistribType;
import calculator.func.distributions.negbinomial.OrigNegBinomial;

public class OrigGeo extends DistribType {

	public OrigGeo(Double[] params) {
		super(params);
	}

	// by default P(X<=x)
	@Override
	public Double cFunc(Double x) {
		isValid(x);
		if (!isEqual) {
			x--;
		}
		return OrigNegBinomial.cd(1, params[0], x.intValue());
	}

	@Override
	public Double pFunc(Double x) {
		isValid(x);
		return OrigNegBinomial.pd(1, params[0], x.intValue());
	}
	
	public Double p() {
		return params[0];
	}

	@Override
	public Double getExp() {
		return 1/params[0];
	}

	@Override
	public Double getVariant() {
		return (1-params[0])/Math.pow(params[0],2);
	}
}
