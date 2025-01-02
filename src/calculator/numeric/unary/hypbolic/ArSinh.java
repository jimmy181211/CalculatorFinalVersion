package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Ln;
import calculator.operation.UnaryOperationAng;

public class ArSinh extends UnaryOperationAng{
	public ArSinh() {
		super("arsinh");
	}
	
	public static Double arsinh(double val) {
		return Ln.ln(val+Math.sqrt(val*val+1));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)changeAngOutMode(arsinh((Double)numObj));
	}

}
