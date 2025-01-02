package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;
import calculator.vector.binary.Multiply;

public class IsNormal extends UnaryOperation {

	public IsNormal() {
		super("isnormal");
		// TODO Auto-generated constructor stub
	}
	
	public static Boolean isNormal(Double[] vec,Double[] vec2) {
		return Multiply.dotProduct(vec,vec2)==0.0? true:false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,2,"isNormal");
		Double[] vec1=(Double[])dynarr.get(0),vec2=(Double[])dynarr.get(1);
		checkLengths(vec1,vec2);
		return (E)isNormal(vec1,vec2);
	}

}
