package calculator.func.distributions.normal;

import calculator.func.distributions.Distribution;

public class Normal extends Distribution {
	// params[0]=mean; params[1]=standard deviation
	public Normal(Double[] params) {
		super("N");
		isValid(params);
		this.continuity=true;
		this.addObjs(new OrigNormal(params), new InvNormal(params));
	}

	public Normal() {
		super("N");
	}

	@Override
	protected void isValid(Double[] params) {
		if (params[1] > 0) {
			return;
		}
		System.out.println(errors.getErr("14") + " for normal distribution");
		System.exit(0);
	}
}
