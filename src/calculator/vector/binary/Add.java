package calculator.vector.binary;

import calculator.operation.BinaryOperation;

public class Add extends BinaryOperation {

	public Add() {
		super("+",3);
	}
	
	public static Double[] add(Double[] vec, Double[] vec2) {
		Double[] newVec=new Double[vec.length];
		for(int i=0;i<vec.length;i++) {
			newVec[i]=vec[i]+vec2[i];
		}
		return newVec;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double[] vec=(Double[])numObj1,vec2=(Double[])numObj2;
		checkLengths(vec,vec2);
		return (E)add(vec,vec2);
	}

}
