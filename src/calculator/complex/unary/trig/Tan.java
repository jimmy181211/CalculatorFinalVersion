package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Divide;
import calculator.operation.UnaryOperation;

public class Tan extends UnaryOperation {

	public Tan() {
		super("tan");
		// TODO Auto-generated constructor stub
	}
	
	public static ComplexNum tan(ComplexNum cangle) {
		ComplexNum denominator=Func.trigPart(cangle, true);
		if(denominator.isEmpty()) {
			System.out.println(errors.getErr("00")+" for tan(x)");
			System.exit(0);
		}
		return Divide.divide(Func.trigPart(cangle, false), denominator);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)tan((ComplexNum)numObj);
	}

}
