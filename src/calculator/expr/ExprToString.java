package calculator.expr;

import calculator.funcTools.Functions;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.termInfo.TermCore;

public class ExprToString {
	private static int signLen = 3;

	// this method makes the term, eg: * a * b^2 * c^4
	// the next methods get rid of the * at the front
	protected static StringBuilder entryToStringInner(TermCore core) {
		StringBuilder term = new StringBuilder();
		// a bunch of variables eg: a^2 * b^3 * c
		core.traverse(pTerm->{
			term.append(" * ");
			term.append(varFormat(pTerm.key, pTerm.val));// appending the string and the power
		});
		return term;
	}

	public static StringBuilder entryToString(TermCore core) {
		StringBuilder rawTerm = entryToStringInner(core);
		//delete the multiply sign at the start index of the string
		return rawTerm.replace(0, 3, "");
	}

	// make every term to string
	public static StringBuilder sSentryToString(Term term) {
		StringBuilder termStr = entryToStringInner(new TermCore(term.cont.key));
		// add the coefficient first
		termStr.insert(0, term.cont.val);
		return termFormat(termStr);
	}

	// this method only convert one part of the fraction into string
	protected static StringBuilder toStringPart(Poly polynomial) {
		StringBuilder expr = new StringBuilder();
		polynomial.traverse(term->{
			expr.append(sSentryToString(new Term(term)));
		});
		return fixStrFront(expr);
	}

	// the format specified for each variable in a term
	private static String varFormat(Var var, Double pow) {
		String varStr=var.toString();
		if (pow == 1.0) {
			return varStr;
		}
		// TODO: by default it only shows the power from 0 to 2 decimal place. In the
		// future this bit of logic will be changed
		int dp = Functions.dp(pow);
		if (dp == 0) {
			return String.format("%s^%.0f", varStr, pow);
		} else if (dp == 1) {
			return String.format("%s^%.1f", varStr, pow);
		} else {
			return String.format("%s^%.2f", varStr, pow);
		}
	}

	// the format specified for each term: change the format of the sign at the
	// front of the expression string
	private static StringBuilder termFormat(StringBuilder term) {
		// notice that the returned term must have a length of 3, because it's used in
		// fixStrFrong method
		if (term.charAt(0) == '-') {
			// signLen=" - ".length();
			term.replace(0, 1, " - ");
			return term;
		} else {
			// signLen=" + ".length();
			term.insert(0, " + ");
			return term;
		}
	}

	/*
	 * this method offers the format specified for the entire expression the input
	 * expr string will usually be: + ax + b^2 * y^8... the front " + " needs to be
	 * removed; or when it is " - ", it needs to be changed to "-" only
	 */
	protected static StringBuilder fixStrFront(StringBuilder expr) {
		String signStr = expr.substring(0, signLen);
		String sign = signStr.substring(1, 2);
		if (sign.equals("-")) {
			expr.replace(0, signLen, sign);
		}
		// if is positive, then the sign is unnecessary, remove it!
		else {
			expr.replace(0, signLen, "");
		}
		return expr;
	}

}
