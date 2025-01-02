package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class IsParallel extends UnaryOperation {

	public IsParallel() {
		super("isparallel");
		// TODO Auto-generated constructor stub
	}
	
	public static Boolean isParallel(Double[] vec,Double[] vec2) {
		Double ratio=Functions.sf(vec[0]/vec2[0],10);
		for(int i=1;i<vec.length;i++) {
			if(Functions.sf(vec[i]/vec2[i],10)!=ratio) {
				return false;
			}
		}return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,2,"isParallel");
		Double[] vec1=(Double[])dynarr.get(0),vec2=(Double[])dynarr.get(1);
		checkLengths(vec1,vec2);
		return (E)isParallel(vec1,vec2);
	}

}
