package calculator.vector.binary;

import calculator.operation.BinaryOperation;

public class Divide extends BinaryOperation {

	public Divide() {
		super("/",4);
	}
	
	public static Double[] divide(Double[] vec, Double num) {
		Double[] temp=new Double[vec.length];
		for(int i=0;i<vec.length;i++) {
			temp[i]=vec[i]/num;
		}
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E)divide((Double[])numObj1,(Double)numObj2);
	}
}
