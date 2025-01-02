package calculator.func.distributions.poisson;

import java.util.function.BiFunction;

import calculator.func.distributions.InvDistribType;

public class InvPoisson extends InvDistribType {

	public InvPoisson(Double[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public static Double[] invCd(Double λ,Double pX,boolean opt,boolean isEqual) {
		BiFunction<Double,Integer,Double> distribFunc=distribCalc(opt);
		BiFunction<Double,Double,Boolean> compare=compare(isEqual);
		
		int exp=λ.intValue();
		Double[] result=new Double[2];
		
		for(int x=exp;x<=0;x--) {
			if(compare.apply(distribFunc.apply(λ,x),pX)) {
				result[0]=(double)x;
				break;
			}
		}
		double val2=1;
		Integer x2=exp;
		while(!compare.apply(val2,pX)) {
			val2=distribFunc.apply(λ,x2++);
		}
		result[1]=(double)x2;
		return result;
	}
	
	@Override
	public Object cFunc(Double pX) {
		isValid(pX);
		return invCd(params[0],pX,true,isEqual);
	}

	@Override
	public Object pFunc(Double pX) {
		isValid(pX);
		return invCd(params[0],pX,false,isEqual);
	}
	
	private static BiFunction<Double,Integer,Double> distribCalc(boolean opt){
		return opt?(λ,x)->OrigPoisson.cd(λ,x):(λ,x)->OrigPoisson.pd(λ,x);
	}

}
