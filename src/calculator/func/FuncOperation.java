package calculator.func;

import java.util.Optional;
import calculator.funcTools.CloneObj;
import calculator.opHandlers.IsMulti;
import calculator.operation.UnaryOperation;

public abstract class FuncOperation extends UnaryOperation implements IsMulti, CloneObj<FuncOperation>, FuncType {
	protected String[] vars;
	// if is multi, then the operation will return multiple results
	// it is a dynamic state that doesn't need to be initialised
	protected boolean isMulti = false;
	protected Type type;

	public FuncOperation(String operator, String[] vars, Integer prio, Type type) {
		super(operator);
		this.type = type;
		this.vars = vars;
		this.prio = Optional.ofNullable(prio).orElse(this.prio);
	}

	@Override
	public Type getType() {
		return this.type;
	}

	// this is for the initialising delay (by InfoBuilder)
	public FuncOperation() {
		super("");
	}

	public String[] getVars() {
		return this.vars;
	}

	@Override
	public void setIsMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	@Override
	public boolean getIsMulti() {
		return this.isMulti;
	}

	@Override
	public abstract FuncOperation clone();
}
