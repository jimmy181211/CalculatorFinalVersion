package calculator.operation;

import java.util.Optional;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.AssembleOpObjs;

/*
 * @version v4.0
 * @ClassName: Operation
 * @Description: This class has unOps and binOps, both of which is a HashMap of Arithmetics Objects, which offer operate method
 * 1. it aggregates the unary and binary operations of an appointed "calculate" package (eg: numeric, matrix, etc.)
 * 2. it overrides the Operates method, which is also overrided by OperationH (the proxy of this class).
 * So this can be interpreted as a proxy design pattern
 */
//SingleTon pattern

public class Operation implements Operates {
	protected HashMapS<Arithmetics> unOps;
	protected HashMapS<Arithmetics> binOps;

	public Operation(String path) {
		// this requires all engaged packages to be in the calculator package
		setAll("calculator." + path);
	}

	// double check in setOps ensures that the singleTon pattern is not broken
	public void setOps(HashMapS<Arithmetics> ops, String path) {
		if (ops != null) {
			return;
		}
		synchronized (Operation.class) {
			if (ops == null) {
				ops = AssembleOpObjs.getOps(Optional.ofNullable(path).orElse(""));
			}
		}
	}

	public void unaryOps(String path) {
		setOps(unOps, path);
	}

	public void binaryOps(String path) {
		setOps(binOps, path);
	}

	public void setAll(String path) {
		setOps(binOps, path + ".binary");
		setOps(unOps, path + ".unary");
	}

	// binary operation
	@Override
	public Object operate(String placeholder, String op, Object numObj1, Object numObj2) {
		return binOps.get(op).operate(numObj1, numObj2);
	}

	// unary operation
	@Override
	public Object operate(String placeholder, String op, Object numObj) {
		return unOps.get(op).operate(numObj);
	}
}
