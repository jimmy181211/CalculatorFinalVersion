package calculator.complex.unary.hyperbolic;

import calculator.complex.ComplexNum;
import calculator.complex.binary.Add;
import calculator.complex.binary.Pow;
import calculator.operation.UnaryOperation;

public class ArSinh extends UnaryOperation {

	public ArSinh() {
		super("arsinh");
	}
	
	public static ComplexNum arsinh(ComplexNum cangle) {
		ComplexNum part=Pow.pow(Add.add(Pow.pow(cangle, 2.0), 1.0),0.5);
		return Add.add(cangle, part);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) arsinh((ComplexNum)numObj);
	}
}
