package calculator.operation;

import calculator.ErrorCodes;
//these classes called are oprand class
import calculator.complex.ComplexNum;
import calculator.expr.Expr;
import calculator.funcTools.DynArray;
import calculator.main.originators.FuncOpManager;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.OperationProxy.DT;

public final class OperationH {
	private static OperationProxy proxy;
	private static FuncOpManager opManager;

	/*
	 * the parameters are passed from RPN class. The update of manager and funcop in
	 * which class will affect the values stored in this class. So update of the
	 * values in here is unnecessary
	 */
	public OperationH(FuncOpManager manager) {
		OperationH.opManager = manager;
		OperationH.proxy = new OperationProxy();
	}

	private static boolean isbool(Double num) {
		return num == 1.0 || num == 0.0;
	}

	// binary operation
	public static Object operate(String op, Object num1, Object num2) {
		boolean isReverse = false;
		String oprType = null;

		// numeric
		if (num1.getClass() == Double.class && num2.getClass() == Double.class) {
			oprType = DT.num;
		}
		// vector
		else if (num1.getClass() == Double[].class || num2.getClass() == Double[].class) {
			if (num1.getClass() == Double.class) {
				isReverse = isReverse(op);
			}
			oprType = DT.vec;
		}
		// matrix
		else if (num1.getClass() == Double[][].class || num2.getClass() == Double[][].class) {
			if (num1.getClass() == Double.class) {
				isReverse = isReverse(op);
			}
			oprType = DT.mtrx;
		}
		// boolean
		else if (num1.getClass() == Boolean.class && num2.getClass() == Boolean.class
				|| isbool((Double) num1) && isbool((Double) num2)) {
			oprType = DT.bool;
		}
		// complex
		else if (num1.getClass() == ComplexNum.class || num2.getClass() == ComplexNum.class) {
			if (num1.getClass() == Double.class && !op.equals("/")) {
				isReverse = true;
			}
			oprType = DT.complx;
		}
		// expression
		else if (num1.getClass() == Expr.class || num2.getClass() == Expr.class) {
			// division doesn't follow the commutative rule so shouldn't apply reverse
			// function on it
			if (num1.getClass() == Double.class && !op.equals("/")) {
				isReverse = true;
			}
			oprType = DT.expr;
		}
		// if the oprand can't be identified:
		else {
			System.out.println(String.format((String) ErrorCodes.get("01") + " for binary operation", op));
			System.exit(0);
		}
		// swap values of num1 and num2
		if (isReverse) {
			Object temp = num1;
			num1 = num2;
			num2 = temp;
		}
		return proxy.operate(oprType, op, num1, num2);
	}

	private static boolean isReverse(String op) {
		if (!op.equals("/")) {
			return true;
		} else {
			System.out.println(ErrorCodes.get("05") + ": a double can't divide a matrix");
			System.exit(0);
		}
		return false;
	}

	// unary operation
	public static Object operate(String op, Object num, boolean isun) {
		String oprType = null;
		if (num.getClass() == Double.class) {
			oprType = DT.num;
		}
		// vectors
		else if (num.getClass() == Double[].class) {
			oprType = DT.vec;
		}
		// matrix
		else if (num.getClass() == Double[][].class) {
			oprType = DT.mtrx;
		}
		// boolean
		else if (isbool((Double) num) || num.getClass() == Boolean.class) {
			oprType = DT.bool;
		}
		// varMap
		else if (num.getClass() == DynArray.class) {
			oprType = DT.varMap;
		}
		// complex
		else if (num.getClass() == ComplexNum.class) {
			oprType = DT.complx;
		}
		// expression evaluation
		else if (num.getClass() == Expr.class) {
			oprType = DT.expr;
		}
		// if the oprand can't be identified:
		else {
			System.out.println(String.format((String) ErrorCodes.get("01") + " for unary operation", op));
			System.exit(0);
		}
		return proxy.operate(oprType, op, num);
	}

	// the oprand must be funcOpInfo
	public static Object operate(String op, Object num) {
		return opManager.getOp(op).operate((FuncOpInfo) num);
	}

	public static void main(String[] args) {
	}
}
