package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Ln;
import calculator.operation.UnaryOperationAng;

public class ArTanh extends UnaryOperationAng{

	public ArTanh() {
		super("artanh");
	}
	
	public static Double artanh(double val) {
		if(val<=-1) {
			System.out.println(errors.getErr("00")+" for artanh(x)");
			System.exit(0);
		}
		return 0.5*Ln.ln((val+1)/(1-val));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)changeAngOutMode(artanh((Double)numObj));
	}
}
