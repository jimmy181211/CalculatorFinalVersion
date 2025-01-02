package calculator.opHandlers;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;

public interface CheckFormat {
	public static void checkMtrx(Double[][] mtrx) {
		checkNull(mtrx);
		for (int i = 1; i < mtrx.length; i++) {
			if (mtrx[i - 1].length != mtrx[i].length) {
				// incorrect format for matrices/matrix
				System.out.println(ErrorCodes.get("15"));
				System.exit(0);
			}
		}
	}

	/*
	 * this check is based on the assumption that the two matrices are valid because
	 * all the variables are checked at ToArray stage
	 */
	public static void checkMtrcSizes(Double[][] mtrx, Double[][] mtrx2) {
		if (mtrx.length != mtrx2.length || mtrx[0].length != mtrx2[0].length) {
			System.out.println(ErrorCodes.get("15"));
			System.exit(0);
		}
	}

	// check if the oprand is null
	public static void checkNull(Object obj) {
		if (obj != null) {
			return;
		}
		System.out.println(ErrorCodes.get("05") + " for an oprand");
		System.exit(0);
	}

	public static void checkLengths(Double[] vec, Double[] vec2) {
		if (vec.length != vec2.length) {
			System.out.println(String.format((String) ErrorCodes.get("04"), "vectors"));
			System.exit(0);
		}
	}

	public static void checkLength(DynArray<Object> dynarr, int num, String word) {
		if (dynarr.size() != num) {
			System.out.println(ErrorCodes.get("22") + " for " + word + " calculation");
			System.exit(0);
		}
	}
}
