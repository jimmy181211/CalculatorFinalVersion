package calculator.complex.binary;

import calculator.complex.ComplexNum;
import calculator.operation.BinaryOperation;

public class Multiply extends BinaryOperation {

	public Multiply() {
		super("*",4);
	}
	
	public static ComplexNum multiply(ComplexNum c,Double num) {
		return new ComplexNum(num*c.re(),num*c.im());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		if(numObj2.getClass()==Double.class) {
			return (E)multiply((ComplexNum)numObj1,(Double)numObj2);
		}
		return (E)ComplexNum.multiply((ComplexNum)numObj1,(ComplexNum)numObj2);
	}
}
