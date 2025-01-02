package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperationAng;

public class DihedralAngle extends UnaryOperationAng {

	public DihedralAngle() {
		super("dihedralangle");
		// TODO Auto-generated constructor stub
	}
	
	//angle is from 0-90degree. use 180 minus it if needed
	public static Double dihedralAngle(Double[] vec, Double[] vec2,Double[] vec1Pl2, Double[] vec2Pl2) {
		Double[] normalVec=CrossProduct.crossProduct(vec,vec2);
		return Math.acos(Math.abs(VecAngle.angle(normalVec, CrossProduct.crossProduct(vec1Pl2,vec2Pl2))));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,4,"dihedralAngle");
		Double[] vec=(Double[])dynarr.get(0),vec2=(Double[])dynarr.get(1);
		Double[] vec1Pl2=(Double[])dynarr.get(2),vec2Pl2=(Double[])dynarr.get(3);
		checkLengths(vec,vec2);
		checkLengths(vec1Pl2,vec2Pl2);
		checkLengths(vec1Pl2,vec);
		return (E) changeAngOutMode(dihedralAngle(vec,vec2,vec1Pl2,vec2Pl2));
	}

}
