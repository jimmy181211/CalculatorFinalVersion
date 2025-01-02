package calculator.expr.unary;

import calculator.expr.Expr;
import calculator.expr.PolyOrder;
import calculator.operation.UnaryOperation;

/*
 * @Description: this class sorts an polynomial expression in ascending order using the highest power each term has
 */
public class PolyAscOrder extends UnaryOperation {
	public PolyAscOrder() {
		super("asc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Expr expr = (Expr) numObj;
		PolyOrder.checkSortSize(expr);
		return (E) PolyOrder.order(expr, true);
	}

}
