package calculator.matrix.binary;

import calculator.funcTools.Clone;
import calculator.funcTools.Functions;
import calculator.matrix.unary.Transpose;
import calculator.operation.BinaryOperation;

public class Multiply extends BinaryOperation {
	public Multiply() {
		super("*",4);
	}
	
	public static Double[][] multiply(Double[][] mtrx,Double num) {
		Double[][] tempMtrx=Clone.clone(mtrx);
		int dp;
		int dpNum=Functions.dp(num);
		for(int i=0;i<tempMtrx.length;i++) {
			for(int j=0;j<tempMtrx[0].length;j++) {
				dp=Functions.dp(tempMtrx[i][j])+dpNum;
				tempMtrx[i][j]=Functions.toDp(tempMtrx[i][j]*num,dp);
			}
		}return tempMtrx;
	}
	
	private static Double rowMultiply(Double[] mtrx1Row, Double[] mtrx2Row) {
		Double result=0.0;
		int dp;
		int maxDp=0;
		for(int i=0;i<mtrx1Row.length;i++) {
			dp=Functions.dp(mtrx1Row[i])+Functions.dp(mtrx2Row[i]);
			if(dp>maxDp) {
				maxDp=dp;
			}
			result+=Functions.toDp(mtrx1Row[i]*mtrx2Row[i],dp);
		}
		return Functions.toDp(result,maxDp);
	}
	
	public static Double[][] product(Double[][] mtrx,Double[][] mtrx2) {
		return productInner(mtrx,Transpose.transpose(mtrx2));
	}
	
	//row multiply row, col multiply col
	public static Double[][] productInner(Double[][] mtrx,Double[][] tmtrx) {
		Double[][] newMtrx=new Double[mtrx.length][tmtrx.length];
		for(int i=0;i<mtrx.length;i++) {
			for(int j=0;j<tmtrx.length;j++) {
				newMtrx[i][j]=rowMultiply(mtrx[i],tmtrx[j]);
			}
		}return newMtrx;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double [][] mtrx1=(Double[][])numObj1;
		if(numObj2.getClass()==Double.class) {
			return (E)multiply(mtrx1,(Double)numObj2);
		}
		else {
			Double[][] mtrx2=(Double[][])numObj2;
			Double[][] tmtrx=Transpose.transpose(mtrx2);
			//if can't operate
			checkMtrcSizes(mtrx1,tmtrx);
			return (E)productInner(mtrx1,tmtrx);
		}
	}
	
	public static void main(String[] args) {
		Double[][] matrix= {{0.0,-1.0},{1.0,0.0}};
		Double[][] result=product(matrix,matrix);
	}
}
