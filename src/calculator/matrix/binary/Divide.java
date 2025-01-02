package calculator.matrix.binary;

import calculator.operation.BinaryOperation;

public class Divide extends BinaryOperation {

	public Divide() {
		super("/",4);
	}
	
	public static Double[][] divide(Double[][] mtrx, Double num) {
		if(num==0.0) {
			System.out.println(errors.getErr("06")+" for matrix operation");
			System.exit(0);
		}
		return Multiply.multiply(mtrx,1/num);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E)divide((Double[][])numObj1,(Double)numObj2);
	}

}
