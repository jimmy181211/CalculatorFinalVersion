package calculator.expr;

import java.util.function.Function;

import calculator.expr.unary.Neg;
import calculator.funcTools.Functions;
import calculator.numeric.unary.Abs;
import calculator.numeric.unary.hypbolic.ArSinh;
import calculator.numeric.unary.hypbolic.ArTanh;
import calculator.numeric.unary.hypbolic.Cosh;
import calculator.numeric.unary.hypbolic.Sinh;
import calculator.numeric.unary.hypbolic.Tanh;
import calculator.numeric.unary.trigs.Asin;
import calculator.numeric.unary.trigs.Atan;
import calculator.numeric.unary.trigs.Cos;
import calculator.numeric.unary.trigs.Cosec;
import calculator.numeric.unary.trigs.Cot;
import calculator.numeric.unary.trigs.Sec;
import calculator.numeric.unary.trigs.Sin;
import calculator.numeric.unary.trigs.Tan;

public class Var {
	// the type can only be Expr or String
	private Object cont;
	private String unOp = "";
	protected boolean isPositive;
	private static String errMsg = "the content attribute in var can only hold String type or Expr type";

	private static String[] changeSignUnOps = { new Sin().operator, new Tan().operator, new Cot().operator,
			new Cosec().operator, new Asin().operator, new Atan().operator, new Sinh().operator, new Tanh().operator,
			new ArTanh().operator, new ArSinh().operator };

	private static String[] remainSignUnOps = { new Cos().operator, new Abs().operator, new Sec().operator,
			new Cosh().operator };

	private void setContent(Object content) {
		if (content.getClass() == Expr.class || content.getClass() == String.class) {
			this.cont = content;
			simplify();
		} else {
			new Exception(errMsg).printStackTrace();
		}
	}

	public Var(Object content, String unOp, boolean isPositive) {
		setContent(content);
		this.unOp = unOp;
		this.isPositive = isPositive;
	}

	public Var(Object content) {
		setContent(content);
		simplify();
		this.isPositive = true;
	}

	public Object content() {
		return this.cont;
	}

	//this method pass the inner negative symbol to the attribute 'isPositive'
	private void simplify() {
		if(cont.getClass() == String.class && !unOp.equals("") && ((String)cont).charAt(0)=='-' && neg()) {
			cont=negFunc(cont.getClass()==String.class?true:false).apply(cont);
		}
	}

	public boolean neg() {
		boolean shouldNeg=false;
		// if it needs to change the positivity of the Var
		if (Functions.search(unOp, changeSignUnOps) != -1) {
			isPositive = !isPositive;
			shouldNeg=true;
		} else if (Functions.search(unOp, remainSignUnOps) != -1) {
			shouldNeg=true;
		}
		return shouldNeg;
	}

	private Function<Object, Object> negFunc(boolean isStr) {
		return isStr ? (str) -> ((String) str).substring(1) : (expr) -> Neg.neg((Expr) expr);
	}

	public boolean equals(Var var) {
		return cont.equals(var.cont) && unOp.equals(var.unOp) && isPositive == var.isPositive;
	}

	public String toString() {
		String contStr = null;
		// if the type of content is String
		if (cont.getClass() == String.class) {
			contStr = (String) cont;
		}
		// if the type of content is Expr
		else if (cont.getClass() == Expr.class) {
			contStr = ((Expr) cont).toString();
		}
		// if is other types, throw error
		else {
			new Exception(errMsg).printStackTrace();
		}
		if (unOp.equals("")) {
			return contStr;
		}
		return isPositive ? "" : "-" + unOp + "(" + contStr + ")";
	}
}
