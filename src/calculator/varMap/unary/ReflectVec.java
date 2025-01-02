package calculator.varMap.unary;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperationAng;

public class ReflectVec extends UnaryOperationAng {

	public ReflectVec() {
		super("reflect");
	}

	// reflection about the straight line through the origin, having gradient tanθ
	public static Double[] reflect2D(Double[] vec, Double angle) {

		Double x = vec[0], y = vec[1];
		Double row1 = x * Math.cos(2 * angle) + y * Math.sin(2 * angle);
		Double row2 = x * Math.sin(2 * angle) - y * Math.cos(2 * angle);
		return new Double[] { row1, row2 };
	}

	public static Double[] reflect3DX(Double[] vec) {
		vec[0] = -vec[0];
		return vec;
	}

	public static Double[] reflect3DY(Double[] vec) {
		vec[1] = -vec[1];
		return vec;
	}

	public static Double[] reflect3DZ(Double[] vec) {
		vec[2] = -vec[2];
		return vec;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		Double[] vec = (Double[]) dynarr.get(0);
		// if is 2D vector
		if (vec.length == 2) {
			Double ang = (Double) dynarr.get(1);
			return (E) reflect2D(vec, changeAngInMode(ang));
		}
		// if is 3D vector
		else if (vec.length == 3) {
			String reflectAxis = (String) dynarr.get(1);
			switch (reflectAxis) {
			case "x":
				return (E) reflect3DX(vec);
			case "y":
				return (E) reflect3DY(vec);
			case "z":
				return (E) reflect3DZ(vec);
			}
		}
		System.out.println(ErrorCodes.get("16") + ": the vector can only be of length 2 or 3 for reflection!");
		System.exit(0);
		return null;
	}
}
