package calculator.complex.binary;

import calculator.complex.ComplexNum;
import calculator.complex.unary.Neg;
import calculator.operation.BinaryOperation;

public class Subtract extends BinaryOperation {

	public Subtract() {
		super("-",3);
		// TODO Auto-generated constructor stub
	}
	public static ComplexNum subtract(ComplexNum c1,ComplexNum c2) {
		return Add.add(c1, Neg.neg(c2));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		if(numObj2.getClass()==Double.class) {
			return (E)Add.add((ComplexNum)numObj1, -(Double)numObj2);
		}
		if(numObj1.getClass()==Double.class) {
			return (E)Add.add(Neg.neg((ComplexNum)numObj2), (Double)numObj1);
		}
		return (E)subtract((ComplexNum)numObj1, (ComplexNum)numObj2);
	}
	
	public static void main(String[] args) {
		ComplexNum result=Subtract.subtract(new ComplexNum(1.1,3.2), new ComplexNum(2.1,4.3));
		System.out.println(result.re()+"+"+result.im()+"i");
	}
}
