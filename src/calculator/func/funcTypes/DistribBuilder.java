package calculator.func.funcTypes;

import calculator.func.distributions.DistribFactory;
import calculator.func.funcObjs.DistribFunc;

public class DistribBuilder implements InfoBuilder {
	private Type type = Type.Distrib;

	@SuppressWarnings("unchecked")
	public DistribFunc build(String name, String[] vars, Object[] params) {
		return new DistribFunc(vars, DistribFactory.work(name, (Double[]) params[0]));
	}

	@Override
	public Type getType() {
		return this.type;
	}
}
