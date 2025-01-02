package calculator.func.distributions.poisson;

import calculator.func.distributions.Distribution;

public class Poisson extends Distribution {

	public Poisson(Double[] params) {
		super("PO");
		isValid(params);
		this.addObjs(new OrigPoisson(params), new InvPoisson(params));
	}

	public Poisson() {
		super("PO");
	}

	// λ and x
	@Override
	protected void isValid(Double[] params) {
		if (params[0] >= 0) {
			return;
		}
		System.out.println(errors.getErr("14") + " for poisson distribution");
		System.exit(0);
	}
}
