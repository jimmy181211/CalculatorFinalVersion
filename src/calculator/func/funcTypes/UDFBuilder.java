package calculator.func.funcTypes;

import calculator.func.funcObjs.UDFunc;

public class UDFBuilder implements InfoBuilder {
	private Type type = Type.UDF;

	@SuppressWarnings("unchecked")
	@Override
	public UDFunc build(String operator, String[] vars, Object[] params) {
		// by default: params[0] is expression
		boolean isPolar = false;
		if (params.length == 2) {
			isPolar = (Boolean) params[1];
		}
		return new UDFunc(vars, (String) params[0], isPolar);
	}

	public Type getType() {
		return this.type;
	}
}
