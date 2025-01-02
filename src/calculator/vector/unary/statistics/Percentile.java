package calculator.vector.unary.statistics;

import java.util.Arrays;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class Percentile extends UnaryOperation {
	public Percentile() {
		super("percentile");
		// TODO Auto-generated constructor stub
	}

	// the parameter 'percent' is: eg: 56%, 87%, which are 0.56, 0.87 respectively
	public static Double percentile(Double[] values, Double percent) {
		Arrays.sort(values);
		return values[(int) Math.ceil(values.length * percent / 100) - 1];
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) percentile((Double[]) dynarr.get(0), (Double) dynarr.get(1));
	}
}
