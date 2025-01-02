package calculator.vector.binary;

import calculator.funcTools.Functions;
import calculator.operation.BinaryOperation;

public class Multiply extends BinaryOperation {
	public Multiply() {
		super("*",4);
	}
	
	public static Double[] multiply(Double[] vec, Double num) {
		Double[] temp=new Double[vec.length];
		int dp;
		int dpNum=Functions.dp(num);
		for(int i=0;i<vec.length;i++) {
			dp=Functions.dp(vec[i])+dpNum;
			temp[i]=Functions.toDp(vec[i]*num,dp);
		}
		return temp;
	}

	public static Double dotProduct(Double[] vec, Double[] vec2) {
		Double result=0.0;
		for(int i=0;i<vec.length;i++) {
			int dp=Functions.dp(vec[i])+Functions.dp(vec2[i]);
			result+=Functions.toDp(vec[i]*vec2[i],dp);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double [] vec=(Double[])numObj1;
		if(numObj2.getClass()==Double.class) {
			return (E)multiply(vec,(Double)numObj2);
		}
		else {
			Double[] vec2=(Double[])numObj2;
			//if can't operate
			checkLengths(vec,vec2);
			return (E)dotProduct(vec,vec2);
		}
	}
}
