package calculator.funcTools.termInfo;

import calculator.funcTools.HashMapVars;

public class Poly extends HashMapVars<Double,Double>{
	public Poly clone() {
		return super.clone(new Poly());
	}
}
