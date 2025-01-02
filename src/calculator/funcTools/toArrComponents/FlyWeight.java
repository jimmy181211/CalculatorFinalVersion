package calculator.funcTools.toArrComponents;

import calculator.funcTools.CircLinkList;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;

public class FlyWeight implements VarInitialiser<FlyWeight> {

	private HashMapS<Object> varMap = new HashMapS<>(16);
	private CircLinkList<Frame> result=new CircLinkList<>();
	private String expr;
	//this attribute controls the number of time that the varMap and expr can be set
	private int[] cnts = new int[2];
	private int sum=0;

	// method chaining: the programmer can only call the methods once (at
	// initialisation stage)
	@Override
	public FlyWeight setVarMap(HashMapS<Object> varMap) {
		if (cnts[0]++ == 0) {
			this.varMap = varMap;
			sum++;
		}
		return this;
	}

	@Override
	public FlyWeight setExpr(String expr) {
		if (cnts[1]++ == 0) {
			this.expr = expr;
			sum++;
		}
		return this;
	}

	// end
	public HashMapS<Object> getVarMap() {
		return this.varMap;
	}

	public CircLinkList<Frame> getResult() {
		return this.result;
	}

	public String getExpr() {
		return this.expr;
	}

	// if all parameters are initialised, the programmer can use ToArrayComponent class
	public boolean isInitialised() {
		return sum==2;
	}
}
