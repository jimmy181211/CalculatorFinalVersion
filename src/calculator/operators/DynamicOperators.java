package calculator.operators;

import calculator.funcTools.HashMapS;
import calculator.main.originators.FuncOpManager;
import calculator.opHandlers.FuncOp;

/* T is the generic type for a single operator e.g. it can be an object from FuncOp() class*/

public abstract class DynamicOperators extends AbstrOperators {
	protected HashMapS<FuncOp> funcOps;

	// when there are user-defined functions passing in, encapsulated in FuncOp
	public DynamicOperators(FuncOpManager manager) {
		// the super-class ops:HashMapS is set null
		super(null, 0);
		// this.funcOps will vary as manager.funcOps varies via addFuncOp(), so no need to update this object
		this.funcOps = manager.opMap;
		// update opMaxSz
		funcOps.traverse(values -> {
			if (values.key.length() > this.opMaxSz) {
				this.opMaxSz = values.key.length();
			}
		});
	}

	// if no user-defined functions are passed:
	public DynamicOperators(String[] opKeys) {
		super(initiate(opKeys, createPrios(opKeys.length, 6)), findMaxSize(opKeys));
		funcOps = new HashMapS<>();
	}

	@Override
	public FuncOp get(String key) {
		return funcOps.get(key);
	}
}
