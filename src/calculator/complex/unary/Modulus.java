package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Modulus extends UnaryOperation {

	public Modulus() {
		super("z");
	}
	
	public static Double modulus(ComplexNum c) {
		return Math.sqrt(Math.pow(c.re(),2)+Math.pow(c.im(), 2));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)modulus((ComplexNum)numObj);
	}

}
