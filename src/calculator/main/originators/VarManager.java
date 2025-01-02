package calculator.main.originators;

import calculator.ErrorCodes;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.handleVar.VarHandler;
import calculator.opHandlers.handleVar.concreteHandlers.MatrixHandler;
import calculator.opHandlers.handleVar.concreteHandlers.NumHandler;
import calculator.opHandlers.handleVar.concreteHandlers.OperHandler;
import calculator.opHandlers.handleVar.concreteHandlers.VarMapHandler;
import calculator.opHandlers.handleVar.concreteHandlers.VecHandler;

public class VarManager extends OpManager<Object>{
	protected VarHandler<?> head;

	public VarManager() {
		super();
		// set next
		this.head = new NumHandler();
		this.head.setNext(new VecHandler().setNext(
				new VarMapHandler(getVarMap()).setNext(new MatrixHandler().setNext(new OperHandler(getVarMap())))));
	}

	public Object[] addVar(String var) {
		var = Functions.formatExpr(var);
		String[] parts = Functions.split(var, "=");
		Object val = this.head.submit(parts[1]);

		if (val == null) {
			System.out.println(ErrorCodes.get("16"));
			System.exit(0);
		}
		put(parts[0], val);
		return new Object[] { parts[0], val };
	}

	public Object getVar(String name) {
		return opMap.get(name);
	}

	// it will only return the copy of the varmap to the RPN object
	public HashMapS<Object> getVarMap() {
		return this.opMap;
	}
}
