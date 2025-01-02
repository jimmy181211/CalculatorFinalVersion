package calculator.varMap.unary.funcary.unary;

import calculator.func.FuncOperation;
import calculator.operation.UnaryOperation;

/*
 * @Description: this class outputs the variant of a distribution 
 */
public class Variant extends UnaryOperation{

	public Variant() {
		super("var");
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) ((FuncOperation) numObj).operate(this.getClass());
	}

}
