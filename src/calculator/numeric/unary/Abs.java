package calculator.numeric.unary;

import calculator.operation.UnaryOperation;

public class Abs extends UnaryOperation{

	public Abs() {
		super("abs");
	}
	
	public static Double abs(Double num) {
		return num<0?-num:num;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)abs((Double)numObj);
	}

}
