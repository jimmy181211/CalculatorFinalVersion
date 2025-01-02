package calculator.numeric.unary;

import calculator.numeric.binary.Arrangement;
import calculator.operation.UnaryOperation;

public class Factorial extends UnaryOperation{

	public Factorial() {
		super("!");
	}
	
	//maximum num: 170
	public static Double factorial(int num) {
		return Arrangement.arrangement(num,num);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)factorial((Integer)numObj);
	}

}
