package calculator.expr.binary;

import java.util.function.Function;
import calculator.expr.Expr;
import calculator.expr.Operate;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.operation.BinaryOperation;

public class Add extends BinaryOperation {

	public Add() {
		super("+", 3);
	}

	public static Poly operate(Poly polyA, Poly polyB, boolean isneg) {
		Function<Double, Double> toggle = Operate.toggle(isneg);
		polyB.traverse(termB -> {
			// if component is not null, it means that the terms are the same, so they can
			// be combined
			Term termA = new Term(polyA.getE(termB.key));
			if (termA.cont != null) {
				//doing the term merging
				termA.cont.val += toggle.apply(termB.val);
				//if the coefficient becomes 0
				if(termA.cont.val==0.0) {
					polyA.remove(termA.cont.key);
				}
			} 
			//if the terms are different, simply add it to the expr list to make it a new term
			else {
				polyA.put(termB.key, toggle.apply(termB.val));
			}
		});
		return polyA;
	}

	public static Expr operate(Expr expr1, Expr expr2, boolean isSubtract) {
		Poly num1=Multiply.multiply(expr1.getNum(),expr2.getDenom());
		Poly num2=Multiply.multiply(expr1.getDenom(),expr2.getNum());
		Poly denom=Multiply.multiply(expr1.getDenom(),expr2.getDenom());
		return new Expr(operate(num1,num2,isSubtract),denom);
	}
	
	public static Expr add(Expr expr1, Expr expr2) {
		return operate(expr1,expr2,false);
	}
	
	public static Expr add(Expr expr, Double num) {
		//multiply the constant by the denominator to make common denominator
		Poly num2=Multiply.multiply(expr.getDenom(), num);
		//add the two numerators
		return new Expr(operate(expr.getNum(),num2,false),expr.getDenom());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Expr expr=(Expr)numObj1;
		if(numObj2.getClass()==Expr.class) {
			return (E)add(expr,(Expr)numObj2);
		}
		else if(numObj2.getClass()==Double.class) {
			return (E)add(expr,(Double)numObj2);
		}
		else {
			return (E) Operate.invalidOpr();
		}
	}

}
