package calculator.operation;

public interface Operates {
	public Object operate(String oprandType, String op, Object numObj1, Object numObj2);
	public Object operate(String oprandType, String op, Object numObj); 
}
