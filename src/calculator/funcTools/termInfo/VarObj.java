package calculator.funcTools.termInfo;

import calculator.expr.Var;
import calculator.funcTools.Entry;

public class VarObj {
	public Entry<Var, Double> cont;

	public VarObj(Entry<Var, Double> var) {
		this.cont = var;
	}
	
	public VarObj() {
		cont=new Entry<>();
	}
}
