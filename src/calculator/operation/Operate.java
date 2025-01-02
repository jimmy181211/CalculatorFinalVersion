package calculator.operation;

public interface Operate {
	public abstract <E, T, C> E operate(T numObj1, C numObj2);
	public abstract <E,T> E operate(T numObj);
}
