package calculator.operators;


public abstract class StaticOperators extends AbstrOperators{
	public StaticOperators(String[] opKeys,Integer[] prios) {
		super(initiate(opKeys,prios),findMaxSize(opKeys));
	}
	
	public Integer get(String key) {
		return ops.get(key);
	}
}
