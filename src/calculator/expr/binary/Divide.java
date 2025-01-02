package calculator.expr.binary;

import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.operation.BinaryOperation;

public class Divide extends BinaryOperation {

	public Divide() {
		super("/", 4);
	}

	public static Expr divide(Expr expr1, Expr expr2) {
		return Multiply.multiply(expr1, Operate.reciprocal(expr2));
	}

	public static Expr divide(Expr expr, Double num) {
		return Multiply.multiply(expr, 1.0 / num);
	}

	public static Expr divide(Double num, Expr expr) {
		return Multiply.multiply(Operate.reciprocal(expr), num);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		// a number divided by an expression 'expr'
		if (numObj1.getClass() == Double.class && numObj2.getClass() == Expr.class) {
			return (E) divide((Double) numObj1, (Expr) numObj2);
		}
		Expr expr = (Expr) numObj1;
		if (numObj2.getClass() == Expr.class) {
			return (E) divide(expr, (Expr) numObj2);
		} else if (numObj2.getClass() == Double.class) {
			return (E) divide(expr, (Double) numObj2);
		} else {
			return (E) Operate.invalidOpr();
		}
	}

}
