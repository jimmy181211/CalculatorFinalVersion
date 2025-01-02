package calculator.operation;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;
import calculator.opHandlers.CheckFormat;
/*
 * @version 4.1
 * @className: Arithmetics
 * @Description: this class is the super-class of Unary- and BinaryOperation class
 * It is the abstract product in abstract factory pattern
 */
public abstract class Arithmetics implements CheckFormat, Operate {
	public String operator;
	public int prio;
	public final static ErrorCodes errors = new ErrorCodes();

	public Arithmetics(String operator, int prio) {
		this.operator = operator;
		this.prio = prio;
	}

	public String getOp() {
		return this.operator;
	}
	

	// data format check that will be used on operation sub classes (some used at
	// toArray stage so not needed here)
	public void checkMtrcSizes(Double[][] mtrx, Double[][] mtrx2) {
		CheckFormat.checkMtrcSizes(mtrx, mtrx2);
	}

	public void checkLengths(Double[] vec, Double[] vec2) {
		CheckFormat.checkLengths(vec, vec2);
	}

	public static void checkLength(DynArray<Object> dynarr, int num, String word) {
		CheckFormat.checkLength(dynarr, num, word);
	}
}
