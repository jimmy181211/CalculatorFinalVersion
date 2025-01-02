package calculator.expr.unary;

import calculator.expr.Expr;
import calculator.expr.PolyOrder;
import calculator.operation.UnaryOperation;

public class PolyDescOrder extends UnaryOperation {

	public PolyDescOrder() {
		super("desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Expr expr = (Expr) numObj;
		PolyOrder.checkSortSize(expr);
		return (E) PolyOrder.order(expr, false);
	}
}
