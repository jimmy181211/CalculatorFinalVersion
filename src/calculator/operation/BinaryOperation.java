package calculator.operation;

public abstract class BinaryOperation extends Arithmetics {
	public BinaryOperation(String operator, int prio) {
		super(operator, prio);
	}
	
	public <E,T> E operate(T numObj){
		new Exception("the method is not callable").printStackTrace();
		return null;
	}
}
