package calculator.numeric.unary;

import calculator.ErrorCodes;
import calculator.funcTools.AccNumHashMap;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.operation.UnaryOperation;

public class Factorize extends UnaryOperation {

	public Factorize() {
		super("factorize");
	}

	// every element in the dynarr is a double[], where double[0]: the prime number,
	// double[1]: the power
	// the size of dynarr denotes the number of prime numbers
	public static DynArray<Entry<Double, Integer>> factorise(Integer num) {
		AccNumHashMap factors = new AccNumHashMap();
		int start = 0;
		int len = IsPrime.primes.length;
		while (num != 1 && start < len) {
			for (int i = start; i < len; i++) {
				if (num % IsPrime.primes[i] != 0) {
					factors.put(num.doubleValue(), 1);
					num /= IsPrime.primes[i];
					break;
				}
				start++;
			}
		}
		if (num != 1) {
			System.out.println(ErrorCodes.get("07") + ": for a valid factorization");
			System.exit(0);
			return null;
		}
		return factors.traverse();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)factorise(((Double)numObj).intValue());
	}
}
