package calculator.matrix.binary;

import calculator.funcTools.Clone;
import calculator.operation.BinaryOperation;

public class Pow extends BinaryOperation {
	public Pow() {
		super("^",5);
	}

	public static Double[][] pow(Double[][] mtrx, Double num){
		int exp=num.intValue();
		//needs to be a squared matrix
		if(mtrx.length!=mtrx[0].length) {
			System.out.println(errors.getErr("15")+ " for pow operation");
			System.exit(0);
		}
		Double[][] copy=Clone.clone(mtrx);
		for(int i=0;i<exp-1;i++) {
			copy=Multiply.product(copy,copy);
		}
		return copy;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E)pow((Double[][]) numObj1,(Double)numObj2);
	}

}
