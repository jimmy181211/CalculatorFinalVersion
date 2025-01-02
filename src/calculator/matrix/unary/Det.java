package calculator.matrix.unary;

import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class Det extends UnaryOperation {
	public Det() {
		super("det");
	}
	
	public static Double[][] getSubMtrx(int[] pos,Double[][] mtrx) {
		Double[][] subMtrx=new Double[mtrx.length-1][mtrx.length-1];
		int row=0,col=0;
		for(int y=0;y<mtrx.length;y++) { 
			for(int x=0;x<mtrx[0].length;x++) {
				if(y!=pos[1] && x!=pos[0]) {
					subMtrx[row][col++]=mtrx[y][x];
				}
			}
			if(y!=pos[1]) {
				row++;
				col=0;
			}
		}
		return subMtrx;
	}

	public static Double det2D(Double[][] mtrx) {
		double a=mtrx[0][0],b=mtrx[0][1],c=mtrx[1][0],d=mtrx[1][1];
		Double result=Functions.cleanOverflow(a,d)-Functions.cleanOverflow(b,c);
		return result!=0.0?result:null;
	}
	
	public static Double findDet(int pos,Double[][] mtrx) {
		Double subDet=0.0;
		if(mtrx.length<=2) {
			return det2D(mtrx);
		}
		while(pos<mtrx.length) {
			Double element=mtrx[0][pos];
			Double[][] subMtrx=getSubMtrx(new int[] {pos,0},mtrx);
			subDet+=Functions.cleanOverflow(Functions.cleanOverflow(findDet(0,subMtrx),element),Math.pow(-1,pos));
			pos++;
		}
		return subDet;
	}
	
	public static Double det(Double[][] mtrx) {
		if(mtrx.length!=mtrx[0].length) {//filter out the non-n*n matrices
			return null;
		}
		return findDet(0,mtrx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)det((Double[][])numObj);
	}

}
