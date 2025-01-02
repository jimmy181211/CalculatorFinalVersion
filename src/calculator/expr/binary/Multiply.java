package calculator.expr.binary;

import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.operation.BinaryOperation;

public class Multiply extends BinaryOperation {

	public Multiply() {
		super("*", 4);
	}

	public static Poly multiply(Poly polyA, Poly polyB) {
		Poly result = new Poly();
		polyA.traverse(termA -> {
			polyB.traverse(termB -> {
				// multiply each term and store them into the new Expr
				Term newTerm = Operate.multiply(new Term(termA), new Term(termB));
				result.put(newTerm.cont.key, newTerm.cont.val);
			});
		});
		return result;
	}

	public static Expr multiply(Expr exprA, Expr exprB) {
		return new Expr(multiply(exprA.getNum(), exprB.getNum()),
				multiply(exprA.getDenom(), exprB.getDenom()));
	}

	public static Poly multiply(Poly exprPart, Double num) {
		exprPart.traverse(term -> {
			term.val *= num;
		});
		return exprPart;
	}

	public static Expr multiply(Expr expr, Double num) {
		return new Expr(multiply(expr.getNum(), num), expr.getDenom());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Expr expr = (Expr) numObj1;
		if (numObj2.getClass() == Expr.class) {
			return (E) multiply(expr, (Expr) numObj2);
		} else if (numObj2.getClass() == Double.class) {
			return (E) multiply(expr, (Double) numObj2);
		} else {
			return (E) Operate.invalidOpr();
		}
	}
}
