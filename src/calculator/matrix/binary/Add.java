package calculator.matrix.binary;

import calculator.funcTools.Clone;
import calculator.operation.BinaryOperation;

public class Add extends BinaryOperation {
	public Add() {
		super("+",3);
	}
	
	public static Double[][] add(Double[][] mtrx,Double[][] mtrx2) {
		Double[][] tempMtrx=Clone.clone(mtrx);
		for(int i=0;i<mtrx.length;i++) {
			for(int j=0;j<mtrx[0].length;j++) {
				tempMtrx[i][j]+=mtrx2[i][j];
			}
		}return tempMtrx;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double[][] mtrx=(Double[][])numObj1,mtrx2=(Double[][])numObj2;
		//have to be the same size, and the matrix has to be valid
		checkMtrcSizes(mtrx,mtrx2);
		return (E)add(mtrx,mtrx2);
	}

}
