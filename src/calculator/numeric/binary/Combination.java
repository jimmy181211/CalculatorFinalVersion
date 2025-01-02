package calculator.numeric.binary;

import calculator.numeric.unary.Factorial;
import calculator.operation.BinaryOperation;

public class Combination extends BinaryOperation{
	public Combination() {
		super("C",4);
	}
	
	public static Double combination(int n,int r) {
		Arrangement.isValid(n,r);
		if(r>n/2) {//reduce the calculation load using the property of symmetry
			r=n-r;
		}
		return Arrangement.arrangement(n,r)/Factorial.factorial(r);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double num1=(Double)numObj1;Double num2=(Double)numObj2;
		return (E)combination(num1.intValue(),num2.intValue());
	}

}
