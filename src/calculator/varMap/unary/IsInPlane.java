package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class IsInPlane extends UnaryOperation {

	public IsInPlane() {
		super("isinplane");
	}
	
	public static Boolean isInPlane(Double[] vec, Double[] a, Double[] b) {
		return IsNormal.isNormal(vec,CrossProduct.crossProduct(a,b));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,3,"isInPlane");
		Double[] vec1=(Double[])dynarr.get(0),a=(Double[])dynarr.get(1),b=(Double[])dynarr.get(2);
		checkLengths(vec1,a);
		checkLengths(a,b);
		return (E) isInPlane(vec1,a,b);
	}
}
