package calculator.func.distributions;

public abstract class Type implements DistribCalc{
	public final Double[] params;
	protected static Boolean isEqual;
	
	// this constructor entry is for its sub-classes
	public Type(Double[] params) {
		this.params = params;
	}
	public abstract void isValid(Double x);
}
