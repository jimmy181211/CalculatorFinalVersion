package calculator.opHandlers.handleparamfunc;

import java.util.List;
import java.util.Optional;
import calculator.operation.UnaryOperation;

/*
 * @Description: 
 * 1. this class stores the isType and paramFuncs list
 * 2. it defines two ways to initiate ParamFuncs -> system default way and a manual way)
 * 3. it offers getter and setter methods for the protected attribute isType, and a getter method for paramFuncs attribute
 */
public abstract class InitParamFunc {
	protected Boolean isType;
	protected List<UnaryOperation> paramFuncs;

	public InitParamFunc(List<UnaryOperation> paramFuncs) {
		this.paramFuncs = Optional.ofNullable(paramFuncs).orElseThrow();
	}

	public List<UnaryOperation> getParamFuncs() {
		return this.paramFuncs;
	}
}
