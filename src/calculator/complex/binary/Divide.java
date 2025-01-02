package calculator.complex.binary;

import calculator.complex.ComplexNum;
import calculator.complex.unary.Reciprocal;
import calculator.operation.BinaryOperation;

public class Divide extends BinaryOperation {

	public Divide() {
		super("/", 4);
		// TODO Auto-generated constructor stub
	}

	public static ComplexNum divide(ComplexNum c1, ComplexNum c2) {
		ComplexNum result = new ComplexNum();
		result.setTrig(c1.r() / c2.r(), c1.ang() - c2.ang());
		result.trigToNorm();
		return result;
	}

	public static ComplexNum divide(Double num, ComplexNum c) {
		return Multiply.multiply(Reciprocal.reciprocal(c),num);
	}

	@SuppressWarnings("unchecked")
	@Override // numObj1/numObj2
	public <E, T, C> E operate(T numObj1, C numObj2) {
		if (numObj2.getClass() == Double.class) {
			return (E) Multiply.multiply((ComplexNum) numObj1, 1 / (Double) numObj2);
		}
		if (numObj1.getClass() == Double.class) {
			return (E) divide((Double) numObj1, (ComplexNum) numObj2);
		}
		return (E) divide((ComplexNum) numObj1, (ComplexNum) numObj2);
	}

}
