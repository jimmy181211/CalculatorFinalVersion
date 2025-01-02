package calculator.matrix.unary;

import calculator.operation.UnaryOperation;

public class Transpose extends UnaryOperation {
	public Transpose() {
		super("T");
	}
	
	public static Double[][] transpose(Double[][] mtrx) {
		Double[][] tMtrx=new Double[mtrx[0].length][mtrx.length];
		for(int i=0;i<mtrx.length;i++) {
			for(int j=0;j<mtrx[0].length;j++) {
				tMtrx[j][i]=mtrx[i][j];
			}
		}
		return tMtrx;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)transpose((Double[][])numObj);
	}
}
