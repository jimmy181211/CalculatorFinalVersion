package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.unary.Reciprocal;
import calculator.operation.UnaryOperation;

public class Sec extends UnaryOperation {
	public Sec() {
		super("sec");
	}
	
	public static ComplexNum sec(ComplexNum cangle) {
		ComplexNum val=Cos.cos(cangle);
		if(val.isEmpty()) {
			System.out.println(errors.getErr("00")+" for sec(x)");
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
