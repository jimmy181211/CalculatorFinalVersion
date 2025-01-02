package calculator.varMap.hypothesisTests;

public interface Test {
	// the returned value is whether the h0 is accepted
	//only do one tail test for now
	public Boolean test(Double sfLevel);
}
