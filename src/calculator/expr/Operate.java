package calculator.expr;

import java.util.function.Function;

import calculator.ErrorCodes;
import calculator.funcTools.BiFunction;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.termInfo.VarObj;

public class Operate {
	private static Function<VarObj, VarObj> changePow(boolean isDivide) {
		return !isDivide ? (entry) -> (entry) : (entry) -> {
			entry.cont.val = -entry.cont.val;
			return entry;
		};
	}

	private static BiFunction<Double, Double, Double> product(boolean isDivide) {
		return isDivide ? (a, b) -> (a * b) : (a, b) -> (a / b);
	}

	// get reciprocal of the term
	public static Term reciprocal(Term comp) {
		comp.cont.key.traverse(entry -> {
			// reverse the power
			entry.val = -entry.val;
		});
		// get reciprocal for the coefficient
		comp.cont.val = 1 / comp.cont.val;
		return comp;
	}

	// only does the variable merging and adding, not yet the multiplication/
	// division of the coefficients
	public static Term operate(Term compA,
			Term compB, boolean isDivide) {

		BiFunction<Double, Double, Double> product = product(isDivide);
		Double coeff = product.apply(compA.cont.val, compB.cont.val);
		// by default that compA will be the term returned out of the function
		compA.cont.val = coeff;
		// if one of the two terms is a constant
		if (compA.cont.key.get(Expr.CONSTANT) != null) {
			compB.cont.val = coeff;
			return compB;
		} else if (compB.cont.key.get(Expr.CONSTANT) != null) {
			return compA;
		}
		Function<VarObj, VarObj> func = changePow(isDivide);
		compB.cont.key.traverse(var -> {
			VarObj entry = new VarObj(compA.cont.key.getE(func.apply(new VarObj(var)).cont.key));
			// if the variable exists in the list, then simply increase the power of the
			// corresponding term by its power
			if (entry.cont != null) {
				entry.cont.val += var.val;
			} else {
				compA.cont.key.put(var);
			}
		});
		return compA;
	}

	// multiplication and division between two terms
	public static Term multiply(Term compA,
			Term compB) {
		return operate(compA, compB, false);
	}

	// multiplication and division between a term and a number
	public static Term multiply(Term compA, Double num) {
		compA.cont.val *= num;
		return compA;
	}

	public static Expr reciprocal(Expr expr) {
		return new Expr(expr.getDenom(), expr.getNum());
	}

	public static Function<Double, Double> toggle(boolean isneg) {
		return isneg ? (a) -> (-a) : (a) -> (a);
	}

	// this method changes the negative sign in the power if the power if even, and
	// changes the negative sign in 'content' attribute of Var object if needed
	public static Term simplify(Term term) {
		Double sign=1.0;
		DynArray<Entry<Var,Double>> dynarr=term.cont.key.traverse();
		VarObj var;
		for(int i=0;i<dynarr.size();i++) {
			var=new VarObj(dynarr.get(i));
			//if var has a negative sign
			if(!var.cont.key.isPositive) {
				var.cont.key.isPositive=!var.cont.key.isPositive;
				//if the power is not even
				if(var.cont.val%2.0!=0) {
					sign*=-1.0;
				}
			}
		}term.cont.val*=sign;
		return term;
	}

	public static Object invalidOpr() {
		System.out.println(ErrorCodes.get("05") + " the oprand can only be an Expr or a number so far!");
		System.exit(0);
		return null;
	}
}
