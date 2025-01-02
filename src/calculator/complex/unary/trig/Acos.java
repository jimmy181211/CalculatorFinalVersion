package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Add;
import calculator.complex.binary.Pow;
import calculator.operation.UnaryOperation;

public class Acos extends UnaryOperation {

	public Acos() {
		super("acos");
	}
	
	public static ComplexNum acos(ComplexNum c) {
		ComplexNum temp=Pow.pow(Add.add(Pow.pow(c, 2.0), 1.0), 0.5);
		ComplexNum term=Add.add(c,temp);
		return Func.invTrigfunc(ComplexNum.multiply
				(term, ComplexNum.i()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)acos((ComplexNum)numObj);
	}

	public static void main(String[] args) {
		ComplexNum num=new ComplexNum(2.0,4.3);
		System.out.println(acos(num).im());
	}
}
