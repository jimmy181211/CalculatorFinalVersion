package calculator.numeric.binary;

import calculator.numeric.unary.Factorial;
import calculator.numeric.unary.Ln;
import calculator.operation.BinaryOperation;

public class Pow extends BinaryOperation{
	public Pow() {
		super("^",5);
	}
	//O(logn)
	public static Double powInt(Double base,Integer exp) {
		if(exp==0) {
			return 1.0;
		}
		else if(exp%2==1) {
			return powInt(base,exp-1)*base;
		}
		//if exp is an even number
		else {
			Double temp=powInt(base,exp/2);
			return temp*temp;
		}
	}
	public static Double powDec(Double base,Double exp) {
		Double sum=0.0;
		for(int i=0;i<12;i++) {
			sum+=powInt(exp*Ln.ln(base),i)/Factorial.factorial(i);
		}
		return sum;
	}
	
	/* a^x x=[x]+{x}->powInt(a,[x])*pow(a,{x});
	 * split the expression into two parts: one with integer exponent
	 * one with decimal exponent, deal with them separately
	 */
	public static Double pow(Double base,Double exp) {
		Double expDec=exp%1.0;
		//make sure the decimal is small so the approximation is accurate
		if(expDec>0.5) {
			return powInt(base,exp.intValue()+1)/powDec(base,1-expDec);
		}
		return powInt(base,exp.intValue())*powDec(base,expDec);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E)pow((Double)numObj1,(Double)numObj2);
	}
	
	public static void main(String[] args) {
		System.out.println(Pow.pow(15.8,16.5));
	}
}
