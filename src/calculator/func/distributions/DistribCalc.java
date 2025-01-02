package calculator.func.distributions;

public interface DistribCalc {
	// for Func(X<=x)
	public Object cFunc(Double x);
	// for Func(X=x)
	public Object pFunc(Double x);
}
