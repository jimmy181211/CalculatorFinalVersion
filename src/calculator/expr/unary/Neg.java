package calculator.expr.unary;

import calculator.expr.Expr;
import calculator.operation.UnaryOperation;

public class Neg extends UnaryOperation {

	public Neg() {
		super("~");
	}

	public static Expr neg(Expr expr) {
		expr.getNum().traverse(term -> {
			term.val = -term.val;
		});
		return expr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) neg((Expr) numObj);
	}
}
