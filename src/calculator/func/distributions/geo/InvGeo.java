package calculator.func.distributions.geo;

import calculator.func.distributions.InvDistribType;
import calculator.func.distributions.negbinomial.InvNegBinomial;

public class InvGeo extends InvDistribType {

	public InvGeo(Double[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double[] cFunc(Double pX) {
		isValid(pX);
		return InvNegBinomial.invCd(1,params[0],pX,true,isEqual);
	}
	
	//find the first pd smaller or equal to pX
	@Override
	public Double[] pFunc(Double pX) {
		isValid(pX);
		return InvNegBinomial.invCd(1,params[0],pX,false,isEqual);
	}
}
