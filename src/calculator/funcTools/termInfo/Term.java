package calculator.funcTools.termInfo;

import calculator.funcTools.Entry;
import calculator.funcTools.HashMapVar;

public class Term {
	public Entry<HashMapVar<Double>, Double> cont;

	public Term(Entry<HashMapVar<Double>, Double> term) {
		cont = term;
	}
	
	public Term() {
		cont=new Entry<>();
	}
}
