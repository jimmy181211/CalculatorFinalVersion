package calculator.funcTools;

public class AccNumHashMap extends AccHashMap<Double, Integer> {

	public AccNumHashMap() {
		super();
	}

	@Override
	protected Integer add(Integer val1, Integer val2) {
		return val1 + val2;
	}

	@Override
	protected int hash(Double key) {
		return key.intValue();
	}

}
