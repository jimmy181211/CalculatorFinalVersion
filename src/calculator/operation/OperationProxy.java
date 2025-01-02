package calculator.operation;

import calculator.expr.Expr;
import calculator.expr.Var;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;

/*
 * @Description: this class has the same interface as OperationH, who is its proxy
 */
public class OperationProxy implements Operates {
	private static HashMapS<Operation> operations = null;
	private static String[] operationTypes = { "complex", "numeric", "matrix", "vector", "bool", "func", "varMap",
			"expr" };

	// singleTon pattern
	public static class DT {
		public final static String num = name(calculator.numeric.Test.class.getPackageName()),
				vec = name(calculator.vector.Test.class.getPackageName()),
				mtrx = name(calculator.matrix.Test.class.getPackageName()),
				bool = name(calculator.bool.Test.class.getPackageName()),
				func = name(calculator.func.funcObjs.UDFunc.class.getPackageName()),
				varMap = name(calculator.varMap.Test.class.getPackageName()),
				complx = name(calculator.complex.Test.class.getPackageName()), none = "",
				expr = name(calculator.expr.Test.class.getPackageName());

		public final static String[] dts = { num, vec, mtrx, bool, func, varMap, complx, none,expr };

		// input: calculator.packageName
		// output: packageName
		public static String name(String packageName) {
			//only get rid of the 'calculator.' common path
			return Functions.split(packageName, ".")[1];
		}

		// when the packages are not in the same folder/layer
		public static String findName(String packageName) throws Exception {
			DynArray<String> arr = Functions.splits(packageName, ".");
			for (int i = 0; i < arr.size(); i++) {
				for (String type : operationTypes) {
					if (arr.get(i).equals(type)) {
						return type;
					}
				}
			}
			throw new Exception("the package is not an operation type package");
		}
	}

	public static void initialiseOperation() {
		// double checks
		if (operations == null) {
			synchronized (OperationProxy.class) {
				if (operations == null) {
					operations = new HashMapS<>();
					for (String dt : DT.dts) {
						// instantiate the object, dt is the path
						operations.put(dt, new Operation(dt));
					}
				}
			}
		}
	}

	@Override
	public Object operate(String oprandType, String op, Object numObj1, Object numObj2) {
		initialiseOperation();
		return operations.get(oprandType).operate("", op, numObj1, numObj2);
	}
	
	@Override
	public Object operate(String oprandType, String op, Object numObj) {
		initialiseOperation();
		//special case is expr unary operation:
		if(oprandType.equals(DT.expr)) {
			new Var((Expr)numObj,op,true);
		}
		//the first parameter is simply a placeholder, having no concrete meaning, just to make sure that the syntax doesn't go wrong
		return operations.get(oprandType).operate("", op, numObj);
	}
}
