package calculator.matrix.unary;

import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class Inverse extends UnaryOperation {

	public Inverse() {
		super("inv");
	}
	
	public static Double[][] inverse(Double[][] mtrx){
		if(mtrx.length!=mtrx[0].length) {//filter out the non-n*n matrices
			System.out.println(errors.getErr("15"));
			System.exit(0);
		}
		Double detOfMtrx=Det.det(mtrx);
		if(detOfMtrx==0.0) {
			//zero error!
			System.out.println(errors.getErr("06")+" for matrix operation");
			System.exit(0);
		}
		Double[][] inverseMtrx=new Double[mtrx.length][mtrx[0].length];
		for(int i=0;i<mtrx.length;i++) {
			for(int j=0;j<mtrx.length;j++) {
				inverseMtrx[i][j]=Functions.cleanOverflow(Det.det(Det.getSubMtrx(new int[] {i,j},mtrx)),Math.pow(-1, i+j))/detOfMtrx;
			}
		}
		return inverseMtrx;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)inverse((Double[][])numObj);
	}
	
	public static void main(String[] args) {
		System.out.println("hello world!");
		Double[][] matrix= {
				{1.0,2.0,3.1},
				{2.1,2.4,5.1},
				{2.1,7.5,9.1}
		};
		Double[][] result=inverse(matrix);
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[0].length;j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
}
