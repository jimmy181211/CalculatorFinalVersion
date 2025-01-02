package calculator.varMap;

import calculator.varMap.unary.RotateVec;

public class Test {
	public static void main(String [] args) {
		System.out.println("execute");
		Double[] vec=RotateVec.rotate3DX(new Double[] {1.0,3.0,4.1},Math.PI/3);
		System.out.println(vec[0]+" "+vec[1]+" "+vec[2]);
	}
}
