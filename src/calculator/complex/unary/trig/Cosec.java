package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.unary.Reciprocal;
import calculator.operation.UnaryOperation;

public class Cosec extends UnaryOperation {
	
	public Cosec() {
		super("csc");
	}

	public static ComplexNum sec(ComplexNum cangle) {
		ComplexNum val=Sin.sin(cangle);
		if(val.isEmpty()) {
			System.out.println(errors.getErr("00")+" for csc(x)");
			System.exit(0);
		}
		return Reciprocal.reciprocal(val);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sec((ComplexNum)numObj);
	}

}
