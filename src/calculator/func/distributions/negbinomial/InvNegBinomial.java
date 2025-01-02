package calculator.func.distributions.negbinomial;

import java.util.function.BiFunction;

import calculator.func.distributions.InvDistribType;
import calculator.funcTools.TriFunction;

public class InvNegBinomial extends InvDistribType {

	public InvNegBinomial(Double[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	private static TriFunction<Integer,Double,Integer,Double> distribCalc(boolean opt){
		return opt?(success,p,x)->OrigNegBinomial.cd(success,p,x):(success,p,x)->OrigNegBinomial.pd(success,p,x);
	}
	
	public static Double[] invCd(Integer success,Double p, Double pX,boolean opt,boolean isEqual) {
		TriFunction<Integer,Double,Integer,Double> distribFunc=distribCalc(opt);
		BiFunction<Double,Double,Boolean> compare=compare(isEqual);
		
		int exp=(int) (success/p);//expectation of negative binomial distribution
		Double[] result=new Double[2];
		
		for(int x=exp;x<=0;x--) {
			if(compare.apply(distribFunc.apply(success,p,x),pX)) {
				result[0]=(double)x;
				break;
			}
		}
		double val2=1;
		int x2=exp;
		while(!compare.apply(val2,pX)) {
			val2=distribFunc.apply(success,p,x2++);
		}
		result[1]=(double)x2;
		return result;
	}

	@Override
	public Double[] cFunc(Double x) {
		isValid(x);
		return invCd(params[0].intValue(),params[1],x,true,isEqual);
	}

	@Override
	public Double[] pFunc(Double x) {
		isValid(x);
		return invCd(params[0].intValue(),params[1],x,false,isEqual);
	}
}
