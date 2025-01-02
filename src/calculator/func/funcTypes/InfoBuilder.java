package calculator.func.funcTypes;

import calculator.func.FuncOperation;
import calculator.func.FuncType;

/*
 * @Description: this class is the Command class of FuncOp invoker
 * 1. it extends FuncType, which is for the FuncTypeFactory class to map one FuncType to one concreteBuilder
 * 2. it has build method, which initialises subclass of funcOperation. Only called in FuncOp type
 */
public interface InfoBuilder extends FuncType {
	public <E extends FuncOperation> E build(String name, String[] vars, Object[] params);
}
