package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Divide;
import calculator.operation.UnaryOperation;

public class Cot extends UnaryOperation {

	public Cot() {
		super("cot");
		// TODO Auto-generated constructor stub
	}
	
	public static ComplexNum cot(ComplexNum cangle) {
		ComplexNum denominator=Func.trigPart(cangle, false);
		if(denominator.isEmpty()) {
			System.out.println(errors.getErr("00")+" for cot(x)");
			System.exit(0);
		}
		return Divide.divide(Func.trigPart(cangle, true), denominator);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)cot((ComplexNum)numObj);
	}

}
