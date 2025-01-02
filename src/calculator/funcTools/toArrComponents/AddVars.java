package calculator.funcTools.toArrComponents;

import calculator.ErrorCodes;
import calculator.SymbolType.ST;
import calculator.funcTools.Brackets;
import calculator.funcTools.CircLinkList;
import calculator.funcTools.Frame;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.observeCls.CalcModeType;
import calculator.observeCls.Subscriber;
import calculator.opHandlers.CheckFormat;

public class AddVars implements Subscriber<CalcModeType> {
	final static Frame multiplyOp = Frame.frame("*", ST.bin);

	private static CalcModeType type;
	private CircLinkList<Frame> llk = new CircLinkList<>();
	private String cache;
	private Character currCh;
	private int i = 0;

	public CircLinkList<Frame> addVars(final String cache, final HashMapS<Object> varMap) {
		if (cache == null || cache == "") {
			return null;
		}
		this.cache = cache;
		Object val;
		boolean addBrkt = true;// set true so that it won't remove an element without a reason
		StringBuilder num = new StringBuilder();

		for (this.i = 0; i < cache.length(); i++) {
			currCh = cache.charAt(i);
			
			// if is expr, it doesn't need the variables to be declared before hand
			if (type == CalcModeType.expr && Functions.isLetter(currCh)) {
				llk.add(Frame.frame(currCh.toString(), ST.symbol));
				continue;
			}

			val = varMap.get(currCh.toString());
			if (val != null) {
				processVars(val, varMap);
			}
			// if is a complex number
			else if (currCh == 'i') {
				llk.add(Frame.frame("i", ST.var));
			}
			// if is a number (potentially)
			else if (Functions.inNum(currCh)) {
				num = processNum(num);
			}
			// if is a bracket
			else if (Brackets.isBrkt(currCh)) {
				addBrkt = processBrkt();
			}
			// the variable doesn't exist
			else if (Functions.isLetter(currCh)) {
				System.out.println(ErrorCodes.get("02") + " " + currCh.toString());
				System.exit(0);
			} else {
				System.out.println(ErrorCodes.get("13") + " " + currCh.toString());
				System.exit(0);
			}
		}
		// if an element is not an open bracket, the element next to it must be a '*'
		// sign, hence remove which.
		if (!addBrkt) {
			llk.remove();// remove the extra "*" sign
			// the * sign will be added again in the main program depending on whether the
			// operator is unary or binary
		}
		return llk;
	}

	private StringBuilder processNum(StringBuilder num) {
		num.append(currCh);
		boolean isNextNotInNum = i + 1 < cache.length() && !Functions.inNum(cache.charAt(i + 1));
		// if num is a valid number and the next ch is not a number

		if ((isNextNotInNum || i + 1 >= cache.length()) && Functions.isNum(num)) {
			llk.add(Frame.frame(num.toString(), ST.num));
			// add '*'
			if (!(i + 1 < cache.length() && Brackets.isCloseBrkt(cache.charAt(i + 1)))) {
				llk.add(multiplyOp);
			}
			return new StringBuilder();// renew num
		}
		// if num is not a valid number but the next ch is not a number either
		else if (!Functions.isNum(num) && (isNextNotInNum || i + 1 >= cache.length())) {
			System.out.println(ErrorCodes.get("05") + ": invalid number");
			System.exit(0);
		}
		return num;
	}

	private void processVars(Object val, HashMapS<Object> varMap) {
		String var = currCh.toString();
		// if is a variable
		if (val != null) {
			// validate the data:
			// if is matrix:
			if (val.getClass() == Double[][].class) {
				CheckFormat.checkMtrx((Double[][]) val);
			}

			// hashVal(ch) and the value(var)
			llk.add(Frame.frame(var, Frame.ST.var));
			// if the element is a closeBracket
			if (!(i + 1 < cache.length() && Brackets.isCloseBrkt(cache.charAt(i + 1)))) {
				llk.add(multiplyOp);
			}
		}
	}

	// this method returns whether addbrkt is true
	private boolean processBrkt() {
		String var = currCh.toString();
		if (Brackets.isCloseBrkt(currCh)) {
			// if it is * sign
			if (llk.get().op.equals("*")) {
				llk.remove();
			}
			llk.add(Frame.frame(var, ST.brkt));
			llk.add(multiplyOp);
			return false;
		}
		// if is a open bracket, not * sign added
		else {
			llk.add(Frame.frame(var, ST.brkt));
			return true;
		}
	}

	@Override
	public void update(CalcModeType data) {
		type = data;
	}
}
