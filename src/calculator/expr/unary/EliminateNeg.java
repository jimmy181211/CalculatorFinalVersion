package calculator.expr.unary;

import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.operation.UnaryOperation;

/*
 * @Description: the purpose of the class is to externalise the negative sign from each variable to the coefficient to facilitate operations
 */
public class EliminateNeg extends UnaryOperation {

	public EliminateNeg() {
		super("eliminateNeg");
	}

	public static Poly eliminateNeg(Poly poly) {
		poly.traverse(term->{
			Operate.simplify(new Term(term));
		});
		return poly;
	}

	public static Expr eliminateNeg(Expr expr) {
		return new Expr(eliminateNeg(expr.getNum()), eliminateNeg(expr.getDenom()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) eliminateNeg((Expr)numObj);
	}

}
