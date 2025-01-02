package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Ln;
import calculator.operation.UnaryOperationAng;

public class ArCosh extends UnaryOperationAng{
	public ArCosh() {
		super("arcosh");
	}
	
	public static Double arcosh(double val) {
		return Ln.ln(val+Math.sqrt(val*val-1));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)changeAngOutMode(arcosh((Double)numObj));
	}

}
