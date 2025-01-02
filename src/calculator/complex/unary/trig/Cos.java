package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.operation.UnaryOperation;

public class Cos extends UnaryOperation {

	public Cos() {
		super("cos");
	}
	
	public static ComplexNum cos(ComplexNum cangle) {
		return Func.trig(cangle, true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)cos((ComplexNum)numObj);
	}

}
