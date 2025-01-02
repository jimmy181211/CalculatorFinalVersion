package calculator.funcTools.toArrComponents;

import calculator.funcTools.HashMapS;

public interface VarInitialiser<E> {
	// method chaining
	public abstract E setVarMap(HashMapS<Object> varMap);

	public abstract E setExpr(String expr);
}
