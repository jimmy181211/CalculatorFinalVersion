package calculator.funcTools.termInfo;

import calculator.funcTools.HashMapVar;

public class TermCore extends HashMapVar<Double> {
	public HashMapVar<Double> cont;

	public TermCore(HashMapVar<Double> core) {
		cont = core;
	}
	
	public TermCore() {
		cont=new HashMapVar<>();
	}
}
