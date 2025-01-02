package calculator.func.distributions.normal;

import calculator.StatsTable;
import calculator.func.distributions.InvDistribType;
import calculator.funcTools.Functions;
import calculator.funcTools.TriFunction;

public class InvNormal extends InvDistribType {
	// μ, σ
	public InvNormal(Double[] params) {
		super(params);
	}

	// find the element smaller than the target one
	private static Integer sndSearchInv(Double p, Double[] arr) {
		int low = 0, high = arr.length - 1, mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] > p) {
				if (low == high && arr[mid - 1] < p || high == mid) {
					return mid - 1;
				}
				high = mid;
			} else if (arr[mid] < p) {
				if (low == high && arr[mid + 1] > p || low == mid) {
					return mid;
				}
				low = mid;
			} else {
				return mid;
			}
		}
		System.out.println("can't find p in the sndTable array for invNormal distribution");
		System.exit(0);
		return null;
	}

	// find the rough solution quickly time complexity O(1) per searching
	private static Double invCd(double μ, double σ, double p) {
		Integer num1 = 0, num2 = 0;
		boolean isLeft = false;
		if (p < 0.5) {
			isLeft = true;
			p = 1 - p;
		}
		p = Functions.sf(p, 4);
		Double[] rows = new Double[StatsTable.getNormalTable().length];
		for (int i = 0; i < StatsTable.getNormalTable().length; i++) {
			rows[i] = StatsTable.getNormalTable()[i][0];
		}
		// time complexity O(logn)
		num1 = sndSearchInv(p, rows);
		num2 = sndSearchInv(p, StatsTable.getNormalTable()[num1]);

		double sndBound = (double) (num1 * 10 + num2) / 100.0;
		if (isLeft) {
			sndBound = -sndBound;
		}
		return sndBound * σ + μ;
	}

	// P(x<X)=p
	public static Double invCdPrecise(double μ, double σ, double p) {
		Double result = invCd(μ, σ, p);// the last digit is always incorrect
		int dp = Functions.dp(result) - 1;
		result = result - result % Math.pow(10, -dp);// get rid of the last digit
		return binarySearch(μ, σ, p, result, result + Math.pow(10, -dp), 0, distribCalc(true));
	}

	// P(X=x)=p
	public static Double invPdPrecise(double μ, double σ, double p) {
		return binarySearch(μ, σ, p, 0.0, 100.0, 0, distribCalc(false));
	}

	private static Double binarySearch(Double μ, Double σ, Double targetP, double lower, double upper, int cnt,
			TriFunction<Double, Double, Double, Double> distribCalc) {
		double mid = (lower + upper) / 2;
		Double resultP = distribCalc.apply(μ, σ, mid);
		if (resultP == targetP || cnt++ > 12) {
			return mid;
		} else if (resultP < targetP) {
			return binarySearch(μ, σ, targetP, mid, upper, cnt, distribCalc);
		} else if (resultP > targetP) {
			return binarySearch(μ, σ, targetP, lower, mid, cnt, distribCalc);
		} else {
			System.out.println("what's wrong with you?");
			return null;
		}
	}

	private static TriFunction<Double, Double, Double, Double> distribCalc(boolean opt) {
		return opt ? (a, b, c) -> OrigNormal.cd(a, b, c) : (a, b, c) -> OrigNormal.pd(a, b, c);
	}

	@Override
	public Double cFunc(Double x) {
		isValid(x);
		return invCdPrecise(params[0], params[1], x);
	}

	@Override
	public Double pFunc(Double x) {
		isValid(x);
		return invPdPrecise(params[0], params[1], x);
	}

	public static void main(String[] args) {
		InvNormal normalObj = new InvNormal(new Double[] { 34.5, 1.3 });
		System.out.println(normalObj.pFunc(0.65));

	}
}
