package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Abs;
import calculator.operation.UnaryOperationAng;

public class Sinh extends UnaryOperationAng{

	public Sinh() {
		super("sinh");
	}
	
	public static Double sinh(double val) {
		if(Abs.abs(val)>235) {
			System.out.println(errors.getErr("07")+" for sinh(x)");
			System.exit(0);
		}
		return (Math.pow(Math.E,val)-Math.pow(Math.E, -val))/2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sinh(changeAngInMode((Double)numObj));
	}

}
