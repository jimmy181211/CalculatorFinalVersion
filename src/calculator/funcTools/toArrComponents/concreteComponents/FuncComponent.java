package calculator.funcTools.toArrComponents.concreteComponents;

import calculator.SymbolType.ST;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.funcTools.Frame;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.funcTools.toArrComponents.DataInfo;
import calculator.funcTools.toArrComponents.FuncNameGenerator;
import calculator.funcTools.toArrComponents.UnaryComponent;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.ParamFuncHandler;
import calculator.opHandlers.ParamValHandler;
import calculator.operators.FuncOperators;

/*
 * @Description:
 * in ToArray stage, we only use FuncOp class, which encapsulates FuncOperation
 */
public final class FuncComponent extends UnaryComponent {

	public FuncComponent(HashMapS<Object> varMap, String expr) {
		super(varMap, expr, new FuncOperators(), 2);
	}

	public FuncComponent() {
	}

	// node contains the operator and the priority
	@Override
	public void processParams(Entry<String, Object> node) {
		// add the operator into the result dynamic array
		data.getResult().add(new Frame().op(node.key).type(ST.un).prio((Integer) node.val));

		int i = DataInfo.i();

		// update new i after extracting the parameters
		while (data.getExpr().charAt(i) != ')') {
			i++;
		}
		// DataInfo.i()+1 filters out the '(' bracket
		String rawParams = data.getExpr().substring(DataInfo.i() + 2, i);
		// this container has the parameters
		DynArray<String> paramStr = Functions.splits(rawParams, ",");

		// this is the encapsulating container that takes in all the parameter and make
		// the operation Unary
		DynArray<Object> params = new DynArray<>(paramStr.size());

		boolean isParamFuncUn = false;
		// eg.integration, differentiation, these are all isParamFunc: need the function
		// parameter either created by user or needed to be analysed
		if (ParamFuncHandler.isBinaryParamFunc(node.key)) {
			ParamFuncHandler.funcOpInitiate(node.key, data.getExpr().substring(DataInfo.i() + 1, DataInfo.i() + 2));
		}
		// a bit low efficient because after the rfind() function, the complier needs to
		// search through the special operator array again
		else if (ParamFuncHandler.isUnaryParamFunc(node.key)) {
			isParamFuncUn = true;
		}

		Object val;
		// convert the string variables in paramStr into real parameters, and store
		// which in 'params' array
		for (int j = 0; j < paramStr.size(); j++) {
			val = ParamValHandler.handle(paramStr.get(j), data.getVarMap().ROM(true), isParamFuncUn);
			// if it is a function in string type
			if (val.getClass() == String.class) {
				params.add(new FuncOp(new UDFunc(new String[] { "x" }, (String) val, false)));
			} else {
				params.add(val);
			}
			((FuncOp) node.val).setIsMulti(ParamValHandler.isMulti());
		}

		data.getVarMap().ROM(false);
		// add the varMap into the frame list
		String opr = FuncNameGenerator.generate("varMap");
		data.getResult().add(new Frame().op(opr).type(ST.var));
		// add the varMap into the mapping container so its value can be found via the
		// name 'opr'
		data.getVarMap().put(opr, params);
		DataInfo.setI(i);
	}
}
