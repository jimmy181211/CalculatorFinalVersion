package calculator.vector.binary;

import calculator.operation.BinaryOperation;
import calculator.vector.unary.Norm;

public class Pow extends BinaryOperation {

	public Pow() {
		super("^",5);
	}
	
	public static Double pow(Double[] vec,Double num) {
		return Math.pow(Norm.norm(vec), num);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E)pow((Double[])numObj1,(Double)numObj2);
	}

}
