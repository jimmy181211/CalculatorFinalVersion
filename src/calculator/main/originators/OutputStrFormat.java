package calculator.main.originators;

import calculator.complex.ComplexNum;
import calculator.expr.Expr;
import calculator.expr.PolyOrder;
import calculator.funcTools.AbstrMaxHeap;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;

public class OutputStrFormat {

	// simple factory design pattern
	public static String output(Object result) {
		// number or boolean
		Class<?> cls = result.getClass();
		if (cls == Double.class || cls == Boolean.class) {
			return result.toString();
		}
		// vector
		else if (cls == Double[].class) {
			return Functions.toString((Double[]) result);
		}
		// matrix
		else if (cls == Double[][].class) {
			return Functions.toString((Double[][]) result, true);
		}
		// a complex number
		else if (cls == ComplexNum.class) {
			ComplexNum cmplx = (ComplexNum) result;
			Double im = cmplx.im();
			return cmplx.re().toString() + " + " + (im == 1.0 ? "" : im.toString()) + "i";
		}
		// an expression
		else if (cls == String.class) {
			return (String) result;
		}
		//for the asc/desc ordering method in Expr class
		else if (cls==AbstrMaxHeap[].class) {
			return PolyOrder.heapToString((AbstrMaxHeap[])result);
		}
		//TODO: this part needs to be done...for the varMap ones
		else if(cls==DynArray.class) {
			
		}
		else if (cls==Expr.class) {
			return ((Expr)result).toString();
		}
		return null;
	}
}
