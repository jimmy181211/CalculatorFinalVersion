package calculator.opHandlers;

import calculator.func.FuncOperation;
import calculator.func.FuncType.Type;
import calculator.func.FuncTypeFactory;

/* @version 6.0
 * @Description: this class uses visitor design pattern 
 * it encapsulates FuncOperation and communicates with the external environment,
 * separating the actual implementation of algorithm and the access of properties
 */
public class FuncOp extends TempParam implements IsMulti {
	// eg: f(a,b,c,d)=...., vars:{a,b,c,d}
	// eg: P(X)=X~B(a,b), vars: {X}
	private FuncOperation func;

	public FuncOp() {
	}

	public FuncOp(FuncOperation func) {
		this.func = func;
	}

	public <E extends FuncOperation> E build(Type type, String name, String[] vars, Object[] params) {
		E func = FuncTypeFactory.get(type).build(name, vars, params);
		this.func = func;
		return func;
	}

	// this method undertakes the interface to communicate with OperationH class
	// It should only be called by OperationH class
	// other operate requests should use func.operate() method
	public <K> K operate(FuncOpInfo info) {
		this.isVisited = true;
		return this.getFunc().operate(info);
	}

	/*
	 * only used in ParamValFunc calling it will set isMulti to true, not the
	 * reverse. This method will only be called once until the funcOp object is
	 * demolished at the end of the calculation
	 */
	@Override
	public void setIsMulti(boolean isMulti) {
		this.getFunc().setIsMulti(isMulti);
	}

	// used in ToArrayComponent
	public String[] getVars() {
		return this.getFunc().getVars();
	}

	public String operator() {
		return this.getFunc().operator;
	}

	public int getPrio() {
		return this.getFunc().prio;
	}

	public FuncOperation getFunc() {
		return func;
	}

	@Override
	public boolean getIsMulti() {
		return func.getIsMulti();
	}
}
