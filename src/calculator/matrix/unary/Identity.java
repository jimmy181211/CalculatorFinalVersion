package calculator.matrix.unary;

import calculator.operation.UnaryOperation;

public class Identity extends UnaryOperation {

	public Identity() {
		super("I");
	}
	
	public static Double[][] identity(int dimension){
		Double[][] identity=new Double[dimension][dimension];
		for(int i=0;i<identity.length;i++) {
			identity[i][i]=1.0;
		}
		return identity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)identity(((Double)numObj).intValue());
	}

}
