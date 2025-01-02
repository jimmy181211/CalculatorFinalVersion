package calculator.expr.binary;

import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.HashMapVar;
import calculator.numeric.binary.Combination;
import calculator.operation.BinaryOperation;

public class Pow extends BinaryOperation {

	public Pow() {
		super("pow", 5);
	}

	private static Term powTerm(Term term, int exp) {
		term.cont.val = Math.pow(term.cont.val, exp);
		term.cont.key.traverse(var -> {
			var.val *= exp;
		});
		return term;
	}

	private static Poly powExpr(Term term, int exp) {
		Poly terms = new Poly();
		terms.put(powTerm(term, exp).cont);
		return terms;
	}

	// expr1 has only one term, expr2 has multiple terms
	public static Poly powInner(DynArray<Entry<HashMapVar<Double>, Double>> terms, Integer exp) {
		Poly expanded = new Poly();
		Double coeff;
		Term term;
		// base case where the expr has only two terms
		if (terms.size() == 2) {
			// expand it using binomial
			for (int i = 0; i < exp; i++) {
				coeff = Combination.combination(exp, i);
				term = Operate.multiply(powTerm(new Term(terms.get(0)), i), powTerm(new Term(terms.get(1)), exp - i));
				expanded.put(Operate.multiply(term, coeff).cont);
			}
		} else {
			term = new Term(terms.removeAt(0));
			for (int i = 0; i < exp; i++) {
				coeff = Combination.combination(exp, i);
				Multiply.multiply(Multiply.multiply(powExpr(term, i), powInner(terms, exp)), coeff).traverse(currTerm -> {
					expanded.put(currTerm);
				});
			}
		}
		return expanded;
	}

	public static Expr pow(Expr expr, Integer exp) {
		return new Expr(powInner(expr.getNum().traverse(), exp), powInner(expr.getDenom().traverse(), exp));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		return (E) pow((Expr) numObj1, ((Double) numObj2).intValue());
	}
}
