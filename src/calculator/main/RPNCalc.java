package calculator.main;

import java.util.Optional;

import calculator.SymbolType;
import calculator.funcTools.DynArray;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;
import calculator.funcTools.ToArray;
import calculator.main.originators.FuncOpManager;
import calculator.opHandlers.OprandFunc;
import calculator.operators.FunctionOperators;

public class RPNCalc implements SymbolType {
	// the initiate varMap with special constants in
	private HashMapS<Object> varMap;
	/*
	 * no change needs to apply to it. It is singleTon and the change in the global
	 * variable whose value is passed into the class will affect the attribute
	 * because it didn't do deep clone Hence, if the user add extra functions to
	 * funcCreator, the attribute inside the class also changes
	 */
	private static FuncOpManager manager;
	private static FunctionOperators funcOpObj;
	private PostfixCalc calcObj;

	public HashMapS<Object> getVarMap() {
		return this.varMap;
	}

	public Object calc(DynArray<Frame> postfExpr) {
		return calcObj.postfixCalc(postfExpr);
	}

	public DynArray<Frame> translate(String expr) {
		return InfixToPostfix.infixToPostfix2(new ToArray().varMap(varMap).expr(expr).toArray(funcOpObj));
	}

	public RPNCalc(HashMapS<Object> varMap) {
		this.varMap = Optional.ofNullable(varMap).orElse(OprandFunc.initiateVarMap());
		this.calcObj = new PostfixCalc(this.varMap);
	}

	// this method check if the attributes used in setOthers() are initialised
	public RPNCalc checked() {
		if (manager == null || funcOpObj == null) {
			new Exception("either funcOpManager or funcOpObj is not initialised").printStackTrace();
		}
		return this;
	}

	public RPNCalc setOthers(FuncOpManager manager) {
		RPNCalc.funcOpObj = new FunctionOperators(manager);
		RPNCalc.manager = manager;
		return this;
	}

	// the inner method of calcMulti: type1: DynArray<>({a1,b1,c1},{a2,b2,c2}
	public Object[] calcMulti(String expr, String[] vars, DynArray<Object[]> valList) {
		Object[] results = new Object[valList.size()];// create the result array
		DynArray<Frame> postfixExpr=translate(expr);
		
		for (int i = 0; i < valList.size(); i++) {
			// update the values mapped to the variables
			for (int j = 0; j < vars.length; j++) {
				Object value = valList.get(i)[j];
				if (value != null) {
					varMap.put(vars[j], value);
				}
			}
			results[i] = calc(postfixExpr);
		}
		return results;
	}

	// this method is more useful than the one above
	// DynArray<>({a1,a2},{b1,b2},{c1,c2}) no calls!!!!!!!!!!!!!!!!!!!!!
	public Object[] calcMulti2(String expr, String[] vars, DynArray<Object[]> valList) {
		int maxLen = 0, idx = 0;
		for (int i = 0; i < valList.size(); i++) {
			if (valList.get(i).length > maxLen) {
				maxLen = valList.get(i).length;
			}
		}
		DynArray<Frame> postfixExpr=translate(expr);
		Object[] results = new Object[maxLen];
		for (int i = 0; i < maxLen; i++) {
			for (int j = 0; j < valList.size(); j++) {
				Object[] list = valList.get(j);
				if (list.length <= i) {
					idx = list.length - 1;
				} else {
					idx = i;
				}
				varMap.put(vars[j], valList.get(j)[idx]);
			}
			results[i] = calc(postfixExpr);
		}
		return results;
	}

	// calculation without the participation of a variable////////////
	public Object calcSingle(String expr) {
		return calc(translate(expr));
	}
	
	public void setVarMap(HashMapS<Object> varMap) {
		this.varMap=varMap;
	}
}
