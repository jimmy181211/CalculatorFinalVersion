package calculator.opHandlers.handleVar.concreteHandlers;

import calculator.funcTools.HashMapS;
import calculator.main.originators.CalcCmd;
import calculator.main.requests.CalcRequest2;
import calculator.opHandlers.handleVar.VarHandler;

public class OperHandler extends VarHandler<Object> {
	private HashMapS<Object> varMap;

	public OperHandler(HashMapS<Object> varMap) {
		this.varMap = varMap;
	}

	@Override
	public Object handle(String str) {
		return new CalcCmd(varMap).calc(new CalcRequest2(str));
	}

}
