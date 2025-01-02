package calculator.varMap.hypothesisTests;

import calculator.StatsTable;
import calculator.func.FuncOperation;
import calculator.varMap.unary.ChiSquare;

class ChiSquareTest implements Test {
	private Double chiSquare;
	// degree of freedom
	private Integer df;

	public ChiSquareTest(FuncOperation func, Double[] expected, Double[] xVals) {
		chiSquare = ChiSquare.chiSquare(func, expected, xVals);
		df = ChiSquare.getDegree();
	}

	@Override
	public Boolean test(Double sfLevel) {
		Double criticalVal = StatsTable.getCriticalVal(sfLevel, df);
		// accept h0
		if (chiSquare < criticalVal) {
			return true;
		}
		// reject h0
		else {
			return false;
		}
	}
}
