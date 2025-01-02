package calculator.main.originators;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.main.requests.CalcRequest2;
import calculator.opHandlers.ParamValHandler;

/*
 * @Description: it encapsulates the subclasses of BaseCalcCmd -> applying strategy design pattern
 * 1. it processes the input command string so that it can be determined whether to use singleCalc or multiCalc
 * 2. it is the originator in the memento design pattern
 */

public class CalcCmdInvoker {
	protected CalcCmd calcObj;
	// this varMap is read only and acts in accordance with the VarManager's varMap
	private HashMapS<Object> varMapROM;
	// this varMap is used in this class. It will be altered
	private HashMapS<Object> varMap;

	public CalcCmdInvoker(HashMapS<Object> varMap) {
		this.varMapROM = varMap.ROM(true);
		this.varMap = varMapROM.clone().ROM(false);
		this.calcObj = new CalcCmd(varMap);
	}

	// the sub-invoker
	public Object[] calc(String cmd) {
//		this.varMap=varMapROM.clone().ROM(false);
		// this separate the expression from assignments of the unknown variables, and
		// considers to different formats :',' and ';'
		String[] oprs1 = Functions.split(cmd, ",");
		String[] oprs2 = Functions.split(cmd, ";");
		
		// oprs[0] is the expression to be evaluated; oprs[1] contains all variables
		// needed for the calculation
		String[] oprs = oprs1.length == 1 ? oprs2 : oprs1; // choose the one that is not null
		CalcRequest2 req = new CalcRequest2(oprs[0]);
		Object result;

		// it has no unknown variables, use SingleCalc
		if (oprs1.length == 1 && oprs2.length == 1) {
			result = calcObj.calc(req);
		}
		/*
		 * this section of the code creates a calcRequest2 object using cmd: string
		 */
		else {
			DynArray<String> pairs = Functions.splits(oprs[1], ","); // get individual variable pair

			DynArray<Object[]> data = new DynArray<>(pairs.size());
			String[] vars = new String[pairs.size()];

			// add the variables that the expression contains to 'vars' and add it to
			// CalcRequest2 object so that it is ready to be calculated by CalcRPN
			for (int i = 0; i < pairs.size(); i++) {
				String[] dataPair = Functions.split(pairs.get(i), "=");
				// add value
				Object val = ParamValHandler.handle(dataPair[1], varMap);
				if (val.getClass().isArray()) {
					data.add((Object[]) val);
				} else {
					data.add(new Object[] { val });
				}
				// add variable
				vars[i] = dataPair[0];
			}
			req.content(vars, data);

			if (ParamValHandler.isMulti()) {
				result = calcObj.calcMultiVars(req);
			} else {
				result = calcObj.calcVar(req);
			}
		}
		// reassign the varMap object
		this.varMap = varMapROM.clone().ROM(false);
		this.calcObj.setVarMap(this.varMap);
		// return the key and value for history log/ command stack
		return new Object[] { result.toString(), result };
	}
}
