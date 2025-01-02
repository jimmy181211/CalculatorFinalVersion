package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Conjugate extends UnaryOperation{

	public Conjugate(String operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}
	
	public static ComplexNum conjugate(ComplexNum c) {
		return new ComplexNum(c.re(),-c.im());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) conjugate((ComplexNum)numObj);
	}

}
