package calculator.funcTools.toArrComponents.concreteComponents;

import calculator.ErrorCodes;
import calculator.SymbolType.ST;
import calculator.funcTools.CircLinkList;
import calculator.funcTools.DynArray;
import calculator.funcTools.Frame;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.funcTools.toArrComponents.DataInfo;
import calculator.funcTools.toArrComponents.FuncNameGenerator;
import calculator.funcTools.toArrComponents.ToArrComp;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.FuncOpInfo;
import calculator.opHandlers.ParamFuncHandler;
import calculator.opHandlers.ParamValHandler;
import calculator.operators.FunctionOperators;

public class FunctionComponent extends ToArrComp {
	// this class needs FunctionOperator. The full initialisation is delayed, and
	// ComponentManager will pass the parameter when necessary
	public FunctionComponent(HashMapS<Object> varMap, String expr) {
		super(varMap, expr, null, 4);
	}

	public FunctionComponent setFunctionOperators(FunctionOperators funcOpObj) {
		this.opObj = funcOpObj;
		return this;
	}

	public FunctionComponent() {
	}

	@Override
	// the components interact with Operation class and toArrayVarInner doesn't
	public Boolean handle(Entry<String, Object> node, StringBuilder cache) {
		String varComponent;
		FuncOp func = (FuncOp) node.val;
		Boolean isInv = false;
		// adding the default varMap into the FuncOp (the varMap in FuncOp acts only in
		// the scope
		HashMapS<Object> varMap = data.getVarMap();
		String[] pair;
		String str;
		String comparator = null;

		// if the operator is found, we extract the entire expression away and add
		// variables into the FuncOp
		int i = DataInfo.i();
		int startIdx = i + 2;
		String expr = data.getExpr();
		while (expr.charAt(i) != ')') {
			i++;
		}

		/*
		 * it needs to be determined whether the expression is inverse distributions in
		 * form of: P(X<x)=a a if statement is applied below: first condition says that
		 * the expression has a comparator after closed bracket ')' the second condition
		 * says that there is at least an element after the comparator
		 */
		if (Functions.search(Character.toString(expr.charAt(i + 1)), ParamFuncHandler.comparators) != -1
				&& expr.length() > i + 2) {
			isInv = true;
			// substr is the part of string about inv distribution
			String substr = expr.substring(startIdx, expr.length());
			// in the example P(X<x)=a above, varComponent will be: X<a
			varComponent = substr.substring(startIdx, startIdx + 2) + " " + substr.substring(i + 2);
			DataInfo.setI(expr.length() - 1);
		}
		// if isn't inverse distributions
		else {
			// renew the current position of the pointer of expr
			DataInfo.setI(i);
			// add varMap; in form of: num or var:num
			varComponent = expr.substring(startIdx, i);
		}
		DynArray<String> pairs = Functions.splitsProtected(varComponent, ",", '(');

		// skip the part of expr that is processed
		// cover some of the values in varMap by pairs
		for (int k = 0; k < pairs.size(); k++) {
			str = pairs.get(k);
			// check if str is in form of: x=a
			for (String c : ParamFuncHandler.comparators) {
				if (str.contains(c)) {
					// pair[0]: x, pair[1]: a
					pair = Functions.split(str, c);
					// variable, variable map
					Object val = ParamValHandler.handle(pair[1], varMap.ROM(true));
					func.setIsMulti(ParamValHandler.isMulti());
					varMap.ROM(false).put(pair[0], val);
					// doesn't need to worry the for loop will cover this value again and again
					// because that's designed for distributions only, not for UDFunc
					comparator = c;
					break;
				}
			}
			boolean isPairNumInRange = k < func.getVars().length;
			// in form of: a
			if (Functions.isLetter(str) && isPairNumInRange) {
				// only when str not exist in the varMap, and str is a number, we add it into
				// the inner-scope varMap
				varMap.put(func.getVars()[k], ParamValHandler.handle(str, varMap));
				func.setIsMulti(ParamValHandler.isMulti());
				comparator = "=";
			}
			/*
			 * if the number of parameters the user inputs is larger than defined, report
			 * the error and show the operation that is wrongly used
			 */
			else if (!isPairNumInRange) {
				System.out.println(ErrorCodes.get("22") + " for function:" + func.operator());
				System.exit(0);
			}
		}
		CircLinkList<Frame> result = data.getResult();
		FuncOpInfo info = new FuncOpInfo().varMap(varMap).comparator(comparator).isInv(isInv);

		// if the function is a parameter
		if (ParamFuncHandler.isParam) {
			// generate a name for the parameter
			String opr = FuncNameGenerator.generate(node.key);
			result.add(new Frame().op(opr).type(ST.fopr));
			// the var will be added to info inside the ParamFuncHandler class
			ParamFuncHandler.addTo(opr, func, info, data.getVarMap());
		}
		// if the function is a operator
		else {
			// add the operator to the result list
			result.add(new Frame().op(node.key).prio(func.getPrio()).type(ST.func));
			// create the oprand of the functional operator
			String var = FuncNameGenerator.generate("varMap");
			result.add(new Frame().op(var).type(ST.fopr));
			data.getVarMap().put(var, info);
		}
		return true;
	}
}
