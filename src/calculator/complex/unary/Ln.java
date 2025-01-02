package calculator.complex.unary;

import calculator.complex.ComplexNum;
import calculator.operation.UnaryOperation;

public class Ln extends UnaryOperation {

	public Ln() {
		super("ln");
	}
	
	public static ComplexNum ln(ComplexNum c) {
		if(c.r()>0) {
			return new ComplexNum(Math.log(c.r()),c.ang());
		}
		if(c.r()==0) {
			System.out.println("invalid oprand for ln being 0!");
			System.exit(0);
		}
		return new ComplexNum(Math.log(-c.r()),c.ang()+Math.PI);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)ln((ComplexNum)numObj);
	}
	
	public static void main(String[] args) {
		ComplexNum complex=new ComplexNum(-1.2,4.3);
		ComplexNum result=ln(complex);
		System.out.println(result.im());
	}
}
