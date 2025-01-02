package calculator.complex.unary.hyperbolic;

import calculator.complex.ComplexNum;
import calculator.complex.binary.Add;
import calculator.complex.binary.Divide;
import calculator.complex.binary.Multiply;
import calculator.complex.unary.Ln;
import calculator.complex.unary.Neg;
import calculator.operation.UnaryOperation;

public class ArTanh extends UnaryOperation {

	public ArTanh() {
		super("artanh");
	}
	
	public static ComplexNum artanh(ComplexNum cangle) {
		ComplexNum base= Divide.divide(Add.add(cangle, 1.0), Add.add(Neg.neg(cangle), 1.0));
		return Multiply.multiply(Ln.ln(base),0.5);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)artanh((ComplexNum)numObj);
	}

}
