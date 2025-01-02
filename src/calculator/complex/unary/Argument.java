package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Argument extends UnaryOperation{

	public Argument() {
		super("arg");
		// TODO Auto-generated constructor stub
	}
	
	public Double arg(ComplexNum c) {
		return c.ang();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)arg((ComplexNum)numObj);
	}

}
