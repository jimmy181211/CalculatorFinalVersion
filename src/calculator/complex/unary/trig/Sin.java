package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.operation.UnaryOperation;

public class Sin extends UnaryOperation {

	public Sin() {
		super("sin");
	}
	
	/*let cangle=x+yi
	 * sinθ=(e^(iθ)-e^(-iθ))/2.... simplification to obtain the result:
	 * sin(x+yi)=[e^(-y)(cosx+isinx)-e^y(cos-x+isin-x)]/(2i)
	 */
	public static ComplexNum sin(ComplexNum cangle) {
		return Func.trig(cangle,false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sin((ComplexNum)numObj);
	}

}
