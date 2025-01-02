package calculator.numeric.binary;

import calculator.operation.BinaryOperation;

public class Arrangement extends BinaryOperation{
	public Arrangement() {
		super("A",4);
	}

	public static void isValid(int n,int r) {
		if(n<0 || r<0 || r>n) {//n and r can't be negative
			System.out.println(errors.getErr("14"));
			System.exit(0); 
		}
	}
	
	public static Double arrangement(int n,int r) {
		isValid(n,r);
		Double product=1.0;
		for(int i=n;i>n-r;i--) {
			product=product*i;
		}
		return product;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double num1=(Double)numObj1;Double num2=(Double)numObj2;
		return (E)arrangement(num1.intValue(),num2.intValue());
	}
}
