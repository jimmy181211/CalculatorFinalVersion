package calculator.func.distributions.binomial;

import calculator.func.distributions.DistribProperties;
import calculator.func.distributions.Distribution;

public class Binomial extends Distribution implements DistribProperties {
	/*
	 * n,p
	 */
	public Binomial(Double[] params) {
		super("B");
		isValid(params);
		this.addObjs(new OrigBinomial(params),new InvBinomial(params));
	}
	
	public Binomial() {
		super("B");
	}

	@Override
	protected void isValid(Double[] params) {
		if (params[0] <= 0) {
			System.out.println(errors.getErr("14") + " for binomial");
			System.exit(0);
		}
		isPValid(params[1]);
	}

	@Override
	public Double getExp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getVariant() {
		// TODO Auto-generated method stub
		return null;
	}
}
