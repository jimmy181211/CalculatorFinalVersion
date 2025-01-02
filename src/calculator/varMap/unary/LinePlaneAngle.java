package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperationAng;

public class LinePlaneAngle extends UnaryOperationAng {

	public LinePlaneAngle() {
		super("lineplaneangle");
		// TODO Auto-generated constructor stub
	}
	
	public static Double linePlaneAngle(Double[] vecX, Double[] vec,Double[] vec2) {
		Double cosVal=VecAngle.angle(vecX, CrossProduct.crossProduct(vec,vec2));
		boolean supplement=false;
		if(cosVal<0) {
			supplement=true;
		}
		Double origAngle=Math.asin(Math.abs(cosVal));
		return supplement?Math.PI-origAngle:origAngle;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,3,"LinePlaneAngle");
		Double[] vecX=(Double[])dynarr.get(0),a=(Double[])dynarr.get(1),b=(Double[])dynarr.get(2);
		checkLengths(vecX,a);
		checkLengths(a,b);
		return (E) changeAngOutMode(linePlaneAngle(vecX,a,b));
	}

}
