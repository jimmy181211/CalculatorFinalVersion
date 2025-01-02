package calculator.func.distributions.normal;

import calculator.opHandlers.FuncOp;
import calculator.opHandlers.FuncOpInfo;
import calculator.varMap.unary.funcary.binary.Integrate;
import calculator.func.FuncOperation;
import calculator.func.FuncType.Type;
import calculator.func.distributions.DistribType;

public class OrigNormal extends DistribType {
	public OrigNormal(Double[] params) {
		super(params);
	}

	// 'ndf' stands for normal distribution density function
	private static FuncOperation densityFunc = new FuncOp().build(Type.Distrib,"ndf", new String[] { "x" },
			new Object[] { "1/sqrt(2π)e^(-0.5x^2)" });
	private static FuncOpInfo info = new FuncOpInfo();

	static Double cd(double μ, double σ, double bound) {
		bound = (bound - μ) / σ;
		Integrate integral = new Integrate();
		
		// -8 is an estimate of the lowest bound, because the probability of x value
		// smaller than -8 is negligible
		if (bound >= 4) {
			info.set(new Double[] { bound, 8.0 });
			return 1 - (Double) integral.integrate(densityFunc, info,null);
		} else if (bound > -4 && bound < 0) {
			info.set(new Double[] { bound, 0.0 });
			return 0.5 - (Double) integral.integrate(densityFunc, info,null);
		} else if (bound < -4) {
			info.set(new Double[] { -8.0, bound });
			return (Double) integral.integrate(densityFunc, info,null);
		} else {
			info.set(new Double[] { 0.0, bound });
			return 0.5 + (Double) integral.integrate(densityFunc, info,null);
		}
	}

	static Double cdRange(double μ, double σ, double lower, double upper) {
		info.set(new Double[] { (lower - μ) / σ, (upper - μ) / σ });
		return (Double) new Integrate().integrate(densityFunc, info,null);
	}

	static Double pd(double μ, double σ, double num) {
		return cdRange(μ, σ, num - 0.5, num + 0.5);
	}

	@Override
	public Object cFunc(Double x) {
		isValid(x);
		return cd(params[0], params[1], x);
	}

	@Override
	public Object pFunc(Double x) {
		isValid(x);
		return pd(params[0], params[1], x);
	}

	public Double mean() {
		return params[0];
	}

	public Double sd() {
		return params[1];
	}

	public static void main(String[] args) {
		OrigNormal normalObj = new OrigNormal(new Double[] { 5.4, 1.2 });
		Double value = (Double) normalObj.cFunc(1.1);
		System.out.println(value);
	}

	@Override
	public Double getExp() {
		return params[0];
	}

	@Override
	public Double getVariant() {
		return Math.pow(params[1],2);
	}
}
