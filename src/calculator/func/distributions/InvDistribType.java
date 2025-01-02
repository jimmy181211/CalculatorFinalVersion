package calculator.func.distributions;

import java.util.function.BiFunction;

public abstract class InvDistribType extends Type {

	public InvDistribType(Double[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void isValid(Double pX) {
		Distribution.isPValid(pX);
	}

	// comparing two numbers based on the comparing mode: < or <=
	// this is a hook method
	protected static boolean compare(Double val1, Double val2, boolean hasEqual) {
		Integer result = val1.compareTo(val2);
		// determine if it is < or <=
		return hasEqual ? result <= 0 : result < 0;
	}

	protected static BiFunction<Double, Double, Boolean> compare(boolean isEqual) {
		return isEqual ? (num1, num2) -> compare(num1, num2, false) : (num1, num2) -> compare(num1, num2, true);
	}
}
