package calculator.func;

public interface FuncType {
	public static enum Type {
		UDF, Distrib;
	}
	public Type getType();
}
