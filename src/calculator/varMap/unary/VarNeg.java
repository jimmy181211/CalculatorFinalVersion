package calculator.varMap.unary;

import calculator.expr.Expr;
import calculator.expr.Var;
import calculator.expr.unary.EliminateNeg;
import calculator.funcTools.DynArray;
import calculator.funcTools.termInfo.Poly;
import calculator.operation.UnaryOperation;

/*
 * @Description: this class searches through the expr appointed var and negates all.
 */
public class VarNeg extends UnaryOperation {

	public VarNeg() {
		super("neg");
	}

	public static Poly neg(Poly poly, Object var) {
		// first negate all, then use the neg() method in Var class to simplify the
		// expression down
		// step 1: negate all appointed vars
		poly.traverse(term -> {
			term.key.traverse(v -> {
				Var varObj = v.key;
				// if is the appointed var
				if (varObj.content().equals(var)) {
					varObj.neg();
				}
			});
		});// step2: eliminate the negative sign at each var, externalise them to the
			// coefficient of each term
		return EliminateNeg.eliminateNeg(poly);
	}

	public static Expr neg(Expr expr, Object var) {
		return new Expr(neg(expr.getNum(), var), neg(expr.getDenom(), var));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		return (E) neg((Expr) dynarr.get(0), dynarr.get(1));
	}

}
