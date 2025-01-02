package calculator.complex.unary.hyperbolic;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Multiply;
import calculator.operation.UnaryOperation;

public class Cosh extends UnaryOperation{

	public Cosh() {
		super("cosh");
	}
	
	public static ComplexNum cosh(ComplexNum cangle) {
		return Multiply.multiply(Func.hyperPart(cangle, true),0.5);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)cosh((ComplexNum)numObj);
	}
}
