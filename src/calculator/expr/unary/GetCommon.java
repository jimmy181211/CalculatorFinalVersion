package calculator.expr.unary;

import calculator.expr.Expr;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.termInfo.VarObj;
import calculator.funcTools.HashMapVar;
import calculator.operation.UnaryOperation;
import calculator.varMap.unary.HCF;
import calculator.vector.unary.statistics.Min;

/*
 * @Description: this class extracts the common components that all the terms of the expression share
 */
public class GetCommon extends UnaryOperation {

	public GetCommon() {
		super("common");
	}

	// the returned value is in form of a term
	private static Term getCommon(Term termA, Term termB) {
		if (termA == null) {
			return termB;
		} else if (termB == null) {
			return termA;
		}
		Term common = new Term(new Entry<>());
		termA.cont.key.traverse(varA -> {
			VarObj varB = new VarObj(termB.cont.key.getE(varA.key));
			// if currVar is not null, then both term A and term B has this 'var'
			if (varB != null) {
				// compare the power of the variable of each term, take the minimum
				Double pow = Min.min(new Double[] { varB.cont.val, varA.val });
				common.cont.key.put(varB.cont.key, pow);
			}
		});
		// find the HCF of the two coefficients
		common.cont.val = HCF.hcf(termA.cont.val, termB.cont.val);
		return common;
	}

	public static Term getCommon(Poly poly) {
		Term common = null;
		DynArray<Entry<HashMapVar<Double>, Double>> polyDynarr = poly.traverse();
		for(int i=0;i<polyDynarr.size();i++) {
			common=getCommon(common,new Term(polyDynarr.get(i)));
		}

		return common;
	}

	// that is the "end-user" function
	public static Expr getCommon(Expr expr) {
		// there will be two terms technically -- on at the denominator one at the
		// numerator
		Poly numerator=new Poly();		
		Poly denominator=new Poly();
		numerator.put(getCommon(expr.getNum()).cont);
		denominator.put(getCommon(expr.getDenom()).cont);
		return new Expr(numerator, denominator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) getCommon((Expr) numObj);
	}
}
