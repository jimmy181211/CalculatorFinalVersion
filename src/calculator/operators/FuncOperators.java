package calculator.operators;

import java.util.List;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.AssembleOpObjs;
import calculator.operation.UnaryOperation;

public final class FuncOperators extends StaticOperators {

	protected static String[] setUnOps(String path) {
		List<UnaryOperation> objs = AssembleOpObjs.getOpObjs(path);
		String[] unOps = new String[objs.size()];
		//get operator attribute of the objects
		for (int i = 0; i < objs.size(); i++) {
			unOps[i] = objs.get(i).operator;
		}
		return unOps;
	}

	public FuncOperators() {
		super(setUnOps("calculator.varMap.unary"), createPrios(setUnOps("calculator.varMap.unary").length, 6));
	}

	public static void main(String[] args){
		FuncOperators funcOp = new FuncOperators();
		HashMapS<Integer> hashMap = funcOp.ops;
		hashMap.traverse(values -> {
			System.out.println(values.key + " " + values.val);
		});
	}
}
