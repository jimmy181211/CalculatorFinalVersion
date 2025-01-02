package calculator.complex.unary.hyperbolic;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Multiply;
import calculator.operation.UnaryOperation;

public class Sinh extends UnaryOperation {

	public Sinh() {
		super("sinh");
	}
	
	public static ComplexNum sinh(ComplexNum cangle) {
		return Multiply.multiply(Func.hyperPart(cangle, false),0.5);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sinh((ComplexNum)numObj);
	}

}
