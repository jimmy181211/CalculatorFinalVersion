package calculator.matrix.unary;

import calculator.funcTools.Clone;
import calculator.operation.UnaryOperation;

public class Neg extends UnaryOperation {

	public Neg() {
		super("~");
	}	
	
	public static Double[][] neg(Double[][] mtrx) {
		Double[][] tempMtrx=Clone.clone(mtrx);
		for(int i=0;i<tempMtrx.length;i++) {
			for(int j=0;j<tempMtrx[0].length;j++) {
				tempMtrx[i][j]=-tempMtrx[i][j];
			}
		}return tempMtrx;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)neg((Double[][])numObj);
	}

}
