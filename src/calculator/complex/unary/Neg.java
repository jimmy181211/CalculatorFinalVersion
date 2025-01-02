package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Neg extends UnaryOperation {
	public Neg() {
		super("~");
	}
	
	public static ComplexNum neg(ComplexNum c) {
		return new ComplexNum(-c.re(),-c.im());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)neg((ComplexNum)numObj);
	}

}
