package calculator.func.distributions.poisson;

import calculator.func.distributions.DistribType;
import calculator.numeric.unary.Factorial;

public class OrigPoisson extends DistribType{
	public OrigPoisson(Double[] params) {
		super(params);
	}

	//λ, x
	//P(X<=x)
	public static Double pd(double λ,int x){
		Double numerator=Math.pow(λ, x)*Math.pow(Math.E,-λ);
		return numerator/Factorial.factorial(x);
	}
	 
	//P(X<=x)
	public static Double cd(double λ,int x){
		Double sum=0.0;
		for(int i=0;i<=x;i++) {
			sum=sum+pd(λ,i);
		}
		return sum;
	}

	@Override
	public Double cFunc(Double x) {
		isValid(x);
		if(!isEqual) {
			x--;
		}
		return cd(params[0],x.intValue());
	}

	@Override
	public Double pFunc(Double x) {
		isValid(x);
		return pd(params[0],x.intValue());
	}
	
	public Double mean() {
		return params[0];
	}

	@Override
	public Double getExp() {
		return params[0];
	}

	@Override
	public Double getVariant() {
		return params[0];
	}
}
