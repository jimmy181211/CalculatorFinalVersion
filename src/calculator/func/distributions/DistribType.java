package calculator.func.distributions;

import calculator.ErrorCodes;

/*
 * @version v5.0
 * @ClassName: DistribType
 * @Description:
 * this class is the superclass of all distributions (non-inverse). It uses Template Method design pattern
 * notice that there are two isValid() methods, one for the default parameters of each distribution, another one, 
 * with method parameter 'x', justifying whether the x (passed from a specific request of value eg. P(X=x)) is valid
 * 
 */

public abstract class DistribType extends Type implements DistribProperties {
	public DistribType(Double[] params) {
		super(params);
	}

	// check for x
	@Override
	public void isValid(Double x) {
		if (x >= 0) {
			return;
		}
		System.out.println(ErrorCodes.get("14") + " for binomial distribution");
		System.exit(0);
	}
}
