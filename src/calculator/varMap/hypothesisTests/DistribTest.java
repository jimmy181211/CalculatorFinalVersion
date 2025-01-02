package calculator.varMap.hypothesisTests;

import java.util.Optional;

import calculator.func.FuncOperation;
import calculator.opHandlers.FuncOpInfo;

public class DistribTest implements Test {
	private FuncOperation func;
	private Double observed;

	public DistribTest(FuncOperation func, Double observed) {
		this.func = Optional.ofNullable(func).orElseThrow();
		this.observed = Optional.ofNullable(observed).orElseThrow();
	}

	@Override // the 'func' is chosen outside the class
	public Boolean test(Double sfLevel) {
		Double[] result = func.operate(new FuncOpInfo(func.getVars()[0], sfLevel));
		// reject h0
		if (observed < result[0]) {
			return false;
		} else if (observed > result[1]) {
			return false;
		}
		// accept h0
		else {
			return true;
		}
	}

}
