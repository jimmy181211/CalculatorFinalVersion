package calculator.main.originators;

import calculator.ErrorCodes;
import calculator.funcTools.Functions;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.handleOp.FuncHandler;
import calculator.opHandlers.handleOp.concreteHandlers.DistribHandler;
import calculator.opHandlers.handleOp.concreteHandlers.UDFHandler;

public class FuncOpManager extends OpManager<FuncOp>{
	private FuncHandler head;

	public FuncOpManager() {
		super();
		this.head = new UDFHandler().setNext(new DistribHandler());
		// by default it is read only mode
	}

	public FuncOp getOp(String key) {
		return this.opMap.get(key);
	}

	// renew opList, ops, and opMaxSz
	protected Object[] addOp(String expr) {
		expr = Functions.formatExpr(expr);
		/*
		 * for f(x,y,z,...)=ax+by+cz..., then data[0]: f(x,y,z...); data[1]: ax+by+cz...
		 * or : P(X)=X~B(a,b)
		 */
		String[] data = Functions.split(expr, "=");
		String[] part = Functions.split(data[0], "(");
		// data[1]: expression; part[0]: name; part[1]: params
		FuncOp funcOp = (FuncOp) head.submit(new String[] { data[1], part[0], part[1] });

		if (funcOp == null) {
			System.out.println(ErrorCodes.get("16"));
			System.exit(0);
		}
		put(part[0], funcOp);
		return new Object[] { part[0], funcOp };
	}

	protected Boolean removeOp(String name) {
		/*
		 * use function name to remove funcop stored in funcOps eg. f(x)=kx+b, there are
		 * two possible names that the user might input: 1. 'f' 2. 'f(x)' both of these
		 * needs to be considered
		 */
		// for 'f(x)'
		String identifier;
		if (name.contains("(")) {
			identifier = Functions.split(name, "(")[0];
		}
		// for 'f'
		else {
			identifier = name;
		}
		FuncOp result = remove(identifier);
		return result == null ? false : true;
	}
}
