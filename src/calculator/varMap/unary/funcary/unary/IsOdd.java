package calculator.varMap.unary.funcary.unary;

import calculator.expr.binary.Add;
import calculator.funcTools.toArrComponents.AddVars;
import calculator.expr.binary.Subtract;

import java.util.function.Function;

import calculator.ErrorCodes;
import calculator.expr.Expr;
import calculator.expr.unary.EliminateNeg;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.BiFunction;
import calculator.funcTools.DynArray;
import calculator.main.RPNCalc;
import calculator.observeCls.CalcModeType;
import calculator.observeCls.ExprModePublisher;
import calculator.operation.UnaryOperation;
import calculator.varMap.unary.VarNeg;

public class IsOdd extends UnaryOperation {
	private static ExprModePublisher calcModePub;

	public IsOdd() {
		super("isodd");
		calcModePub = new ExprModePublisher();
		calcModePub.subscribe(new AddVars());
	}

	private static BiFunction<Expr, Expr, Expr> operate(boolean isSubtract) {
		return isSubtract ? (a, b) -> Subtract.subtract(a, b) : (a, b) -> Add.add(a, b);
	}

	public static Boolean parity(UDFunc udfunc, Object var, boolean isEven) {
		String func = udfunc.expr();
		BiFunction<Expr, Expr, Expr> testOperate = operate(isEven);

		calcModePub.notifySubscriber(CalcModeType.expr);
		// get the expression form of the string
		Expr expr = (Expr) new RPNCalc(null).calcSingle(func);
		calcModePub.notifySubscriber(CalcModeType.calc);
		// transfer all the negative signs within the term to the coefficient
		expr = EliminateNeg.eliminateNeg(expr);
		// negate all the appointed variables: f(x)->f(-x)
		Expr expr2 = VarNeg.neg(expr, var);

		// if is odd: f(-x)=-f(x), then we have f(-x)+f(x)=0; if is even: f(-x)=f(x),
		// then we have: f(x)-f(-x)=0
		Expr result = testOperate.apply(expr, expr2);
		if (result.getNum().size() == 0) {
			return true;
		}
		return false;
	}

	// this method is specialised for operate() method. Reused in IsEven class
	protected static Function<DynArray<Object>, Boolean> parity(boolean isEven) {
		return (dynarr) -> {
			int idx = 0;
			if (dynarr.get(1).getClass() == UDFunc.class) {
				idx++;
			}
			try {
				UDFunc func = (UDFunc) dynarr.get(idx);
				Object var = dynarr.get(++idx % 2);
				return parity(func, var, isEven);
			}
			// if a problem occurs when casting the variable
			catch (Exception e) {
				System.out.println(ErrorCodes.get("05")
						+ ": the first argument of isOdd function must be of either String type of Expr type");
				System.exit(0);
				return null;
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) parity(false);
	}

}
