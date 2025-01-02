package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;
import calculator.vector.unary.Sort;

public class Medium extends UnaryOperation {

	public Medium() {
		super("medium");
		// TODO Auto-generated constructor stub
	}
	
	public static Double medium(Double[] data) {
		Sort.insertion(data);
		int mid=data.length/2;
		if(data.length%2==1) {
			return data[mid];
		}
		else {
			return (data[mid-1]+data[mid])/2;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)medium((Double[])numObj);
	}

}
