package calculator.opHandlers.handleVar.concreteHandlers;

import calculator.opHandlers.OprandFunc;
import calculator.opHandlers.handleVar.VarHandler;

public class NumHandler extends VarHandler<Double>{
	
	@Override
	public Double handle(String str) {
		if(OprandFunc.getNonVarOprVal(str)!=null) {
			return Double.valueOf(str);
		}
		return null;
	}
}
