package calculator.varMap.unary;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperationAng;

public class RotateVec extends UnaryOperationAng {

	public RotateVec() {
		super("rotate");
	}

	public static Double[] rotate2D(Double[] vec, Double angle) {
		Double x = vec[0];
		Double y = vec[1];
		Double row1 = Math.cos(angle) * x + y * Math.sin(angle);
		Double row2 = -Math.sin(angle) * x + y * Math.cos(angle);
		return new Double[] { row1, row2 };
	}

	private static Double[] func(Double a, Double b, Double c, Double angle) {
		return new Double[] { a, Math.sin(angle) * b + c * Math.cos(angle), b * Math.cos(angle) - c * Math.sin(angle) };
	}

	// vec[0]: x; vec[1]: y; vec[2]: z
	public static Double[] rotate3DX(Double[] vec, Double angle) {
		Double[] temp = func(vec[0], vec[1], vec[2], angle);
		return new Double[] { temp[0], temp[2], temp[1] };
	}

	public static Double[] rotate3DY(Double[] vec, Double angle) {
		Double[] temp = func(vec[1], vec[2], vec[0], angle);
		return new Double[] { temp[1], temp[0], temp[2] };
	}

	public static Double[] rotate3DZ(Double[] vec, Double angle) {
		Double[] temp = func(vec[2], vec[0], vec[1], angle);
		return new Double[] { temp[2], temp[1], temp[0] };
	}

	@SuppressWarnings("unchecked")
	@Override // DynArray<>(vec, radian)
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		Double[] vec = (Double[]) dynarr.get(0);
		Double angle = changeAngInMode((Double) dynarr.get(1));

		// if vec is a two dimensional vector
		if (vec.length == 2) {
			return (E) rotate2D(vec, angle);
		}
		// if vec is a three dimensional vector
		else if (vec.length == 3) {
			String rotateAxis = (String) dynarr.get(2);
			switch (rotateAxis) {
			case "x":
				// rotate about x-axis
				return (E) rotate3DX(vec, angle);
			case "y":
				// rotate about y-axis
				return (E) rotate3DY(vec, angle);
			case "z":
				// rotate about z-axis
				return (E) rotate3DZ(vec, angle);
			default:
				System.out.println(ErrorCodes.get("16") + ": the entered rotational axis is undefined!");
				System.exit(0);
				return null;
			}
		}
		System.out.println(ErrorCodes.get("16") + ": the vector can only be of length 2 or 3 for rotation!");
		System.exit(0);
		return null;
	}
}
