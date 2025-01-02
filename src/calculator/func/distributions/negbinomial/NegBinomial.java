package calculator.func.distributions.negbinomial;

import calculator.func.distributions.Distribution;

public class NegBinomial extends Distribution {
	// success,p
	public NegBinomial(Double[] params) {
		super("NEGB");
		isValid(params);
		this.addObjs(new OrigNegBinomial(params), new InvNegBinomial(params));
	}

	public NegBinomial() {
		super("NEGB");
	}

	@Override
	public void isValid(Double[] params) {
		if (params[0] < 0) {
			System.out.println(errors.getErr("14") + " for negative binomial");
			System.exit(0);
		}
		Distribution.isPValid(params[1]);
	}
}
