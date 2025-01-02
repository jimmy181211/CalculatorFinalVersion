package calculator.complex.binary;

import calculator.complex.ComplexNum;
import calculator.operation.BinaryOperation;

public class Add extends BinaryOperation {

	public Add() {
		super("+",3);
	}
	
	public static ComplexNum add(ComplexNum c1, ComplexNum c2) {
		return new ComplexNum(c1.re()+c2.re(),c1.im()+c2.im());
	}
	
	public static ComplexNum add(ComplexNum c,Double re) {
		return new ComplexNum(c.re()+re,c.im());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		if(numObj2.getClass()==Double.class) {
			return (E)add((ComplexNum)numObj1,(Double)numObj2);
		}
		return (E)add((ComplexNum)numObj1,(ComplexNum)numObj2);
	}
	
	public static void main(String[] args) {
		ComplexNum result=add(new ComplexNum(2.3,2.5),1.0);
		System.out.println(result.re()+" "+result.im());
		System.out.println("hello world");
	}
}
