package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.binary.Add;
import calculator.complex.binary.Divide;
import calculator.complex.unary.Ln;
import calculator.complex.unary.Neg;
import calculator.operation.UnaryOperation;

public class Atan extends UnaryOperation {
	public Atan() {
		super("atan");
	}
	
	/*
	 *0.5i*ln[(1-x)/(1+x)]
	 *the negative sign is flip as the power of the base
	 */
	public static ComplexNum atan(ComplexNum cval) {
		ComplexNum logNum=Divide.divide(Add.add(Neg.neg(cval), 1.0),Add.add(cval, 1.0));
		ComplexNum ln=Ln.ln(logNum);
		return ComplexNum.multiply(ln, new ComplexNum(0.0,0.5));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)atan((ComplexNum)numObj);
	}
}
