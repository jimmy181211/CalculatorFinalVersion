package calculator.expr.binary;

import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.operation.BinaryOperation;

public class Subtract extends BinaryOperation{

	public Subtract() {
		super("-",3);
	}
	
	public static Expr subtract(Expr expr1,Expr expr2) {
		return Add.operate(expr1, expr2, true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Expr expr=(Expr)numObj1;
		if(numObj2.getClass()==Expr.class) {
			return (E)subtract(expr,(Expr)numObj2);
		}
		else if(numObj2.getClass()==Double.class) {
			return (E)Add.add(expr,-(Double)numObj2);
		}
		else {
			return (E)Operate.invalidOpr();
		}
	}

}
