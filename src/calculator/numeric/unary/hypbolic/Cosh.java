package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Abs;
import calculator.operation.UnaryOperationAng;

public class Cosh extends UnaryOperationAng{

	public Cosh() {
		super("cosh");
	}
	
	public static Double cosh(double val) {
		if(Abs.abs(val)>235) {
			System.out.println(errors.getErr("07")+" for cosh(x)");
			System.exit(0);
		}
		return (Math.pow(Math.E,val)+Math.pow(Math.E, -val))/2;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)cosh(changeAngInMode((Double)numObj));
	}
}