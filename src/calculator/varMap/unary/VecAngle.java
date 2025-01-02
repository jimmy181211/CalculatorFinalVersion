package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperationAng;
import calculator.vector.binary.Multiply;
import calculator.vector.unary.Norm;

public class VecAngle extends UnaryOperationAng {

	public VecAngle() {
		super("ang");
		// TODO Auto-generated constructor stub
	}

	// angle in radians
	public static Double angle(Double[] vec, Double[] vec2) {
		return Math.acos(Multiply.dotProduct(vec, vec2) / (Norm.norm(vec) * Norm.norm(vec2)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		checkLength(dynarr, 2, "angle");
		return (E) changeAngOutMode(angle((Double[]) dynarr.get(0), (Double[]) dynarr.get(1)));
	}

}
