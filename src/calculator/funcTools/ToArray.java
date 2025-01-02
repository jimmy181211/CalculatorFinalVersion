package calculator.funcTools;

import java.util.Optional;

import calculator.SymbolType;
import calculator.funcTools.toArrComponents.AddVars;
import calculator.funcTools.toArrComponents.ComponentManager;
import calculator.funcTools.toArrComponents.DataInfo;
import calculator.funcTools.toArrComponents.ToArrComp;
import calculator.opHandlers.OprandFunc;
import calculator.operators.FunctionOperators;

public class ToArray implements SymbolType{
	//fly-weight
	public HashMapS<Object> varMap;
	private String expr;

	public ToArray() {}
	
	public ToArray expr(String expr) {
		this.expr=Optional.ofNullable(expr).orElseThrow();
		return this;
	}
	
	public ToArray varMap(HashMapS<Object> varMap) {
		this.varMap=Optional.ofNullable(varMap).orElse(OprandFunc.initiateVarMap());
		return this;
	}

	// reducing the time complexity by using double direction linkedlist
	public CircLinkList<Frame> toArray(FunctionOperators funcOpObj) {
		StringBuilder cache = new StringBuilder();
		ComponentManager manager = null;
		try {
			manager = new ComponentManager(funcOpObj).setExpr(expr).setVarMap(this.varMap);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		for (int i = 0; i < expr.length(); i++) {
			// add the current char to the temp list
			cache.append(expr.charAt(i));
			try {
				manager.work(i, cache);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//updates
			i=DataInfo.i();
			if(DataInfo.isclearCache()) {
				cache=new StringBuilder();
			}
		}
		// add the rest of the oprand
		ToArrComp.data.getResult().add(new AddVars().addVars(cache.toString(), this.varMap));
		return ToArrComp.data.getResult();
	}
}
