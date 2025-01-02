package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class CrossProduct extends UnaryOperation {

	public CrossProduct() {
		super("crossproduct");
	}
	
	//three dimension vector only
	public static Double[] crossProduct(Double[] vec, Double[] vec2) {
		if(vec.length!=3) {
			System.out.println(errors.getErr("16")+" of a vector in crossProduct operation");
			System.exit(0);
		}
		Double[] normal=new Double[3];
		normal[0]=vec[1]*vec2[2]-vec[2]*vec2[1];
		normal[1]=vec[2]*vec2[0]-vec[0]*vec2[2];
		normal[2]=vec[0]*vec2[1]-vec2[0]*vec[1];
		return normal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,2,"crossProduct");
		Double[] vec1=(Double[])dynarr.get(0),vec2=(Double[])dynarr.get(1);
		checkLengths(vec1,vec2);
		return (E)crossProduct(vec1,vec2);
	}

}
