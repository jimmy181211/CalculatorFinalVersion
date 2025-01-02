package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Reciprocal extends UnaryOperation {

	public Reciprocal() {
		super("recip");
		// TODO Auto-generated constructor stub
	}
	
	public static ComplexNum reciprocal(ComplexNum c) {
		Double denominator=Math.pow(c.re(), 2)-Math.pow(c.im(), 2);
		return new ComplexNum(c.re()/denominator,-c.im()/denominator);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)reciprocal((ComplexNum)numObj);
	}
	
	public static void main(String[] args) {
		ComplexNum c=new ComplexNum(1.5,2.5);
		ComplexNum reciprocal=reciprocal(c);
		System.out.println(reciprocal.re()+" + i"+reciprocal.im());
	}
}
