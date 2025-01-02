package calculator.bool;

import calculator.operation.Operation;

public final class OpBool extends Operation {
	public OpBool() {
		super("calculator.bool");

		// this indicates that multiple operator symbols can map to one operation object
		String[][] bin = { { "*", "&&", "and" }, { "+", "||", "or" }, { "^", "xor", "⊕" } };
		String[] un = { "!", "¬", "not", "~" };
		for (int i = 0; i < bin.length; i++) {
			for (int j = 1; j < bin[i].length; j++) {
				binOps.put(bin[i][j], binOps.get(bin[i][0]));
			}
		}
		for (int i = 1; i < un.length; i++) {
			unOps.put(un[i], unOps.get(un[0]));
		}
	}
}
