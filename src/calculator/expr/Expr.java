package calculator.expr;

import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.termInfo.TermCore;
import calculator.funcTools.HashMapVar;

public class Expr {
	// key: the varMap of variables and its power; value: the coefficient
	private Poly numerator = new Poly();
	private Poly denominator = new Poly();
	public final static String CONSTANT = "CONST_";

	public Expr() {
		genConstTerm();
	}

	private void genConstTerm() {
		// generate an empty constant term
		HashMapVar<Double> map = new HashMapVar<>();
		// declaring that this term is a constant
		map.put(CONSTANT, 0.0);
		numerator.put(map, 0.0);
		// by default the denominator has a magnitude of '1'
		denominator.put(map, 1.0);
	}

	public Expr(String var) {
		TermCore map = new TermCore();
		// it says: a term has variable 'var' of power 1
		map.put(new Var(var), 1.0);
		// the coefficient is 1.0
		numerator.put(map, 1.0);
		genConstTerm();
	}

	public Expr(Poly numerator, Poly denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Expr clone() {
		return new Expr(numerator.clone(), denominator.clone());
	}

	public boolean isConst(boolean isNumerator) {
		Poly poly = isNumerator ? numerator : denominator;
		if (poly.size() == 1 && poly.getE(CONSTANT, 0.0) != null) {
			return true;
		}
		return false;
	}

	public Double removeConst(boolean isNumerator) {
		Poly poly = isNumerator ? this.numerator : this.denominator;
		return poly.removeE(CONSTANT, 0.0).val;
	}

	public Double[] removeConsts() {
		Double[] results = new Double[2];
		results[0] = removeConst(true);
		results[1] = removeConst(false);
		return results;
	}

	/*
	 * these methods pass the attributes by reference so the external modifications
	 * do make a difference on the internal environment
	 */
	public Poly getNum() {
		return numerator;
	}

	public Poly getDenom() {
		return denominator;
	}

	public static boolean equalsInner(Poly polyA, Poly polyB) {
		if (polyA.size() != polyB.size()) {
			return false;
		}
		DynArray<Entry<HashMapVar<Double>, Double>> entries1 = polyA.traverse();
		Term term1;
		Term term2;
		for (int i = 0; i < entries1.size(); i++) {
			term1 = new Term(entries1.get(i));
			term2 = new Term(polyB.getE(term1.cont.key));
			if (term2.cont == null || term2.cont.val != term1.cont.val) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Expr expr) {
		return equalsInner(numerator, expr.getNum()) && equalsInner(denominator, expr.getDenom());
	}

	@Override
	public String toString() {
		StringBuilder numerator = ExprToString.toStringPart(this.numerator);
		StringBuilder denominator = ExprToString.toStringPart(this.denominator);
		// method chaining
		return numerator.append(" / ").append(denominator).toString();
	}
}
