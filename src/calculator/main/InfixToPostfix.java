package calculator.main;

import calculator.funcTools.ArrStack;
import calculator.funcTools.Brackets;
import calculator.funcTools.CircLinkList;
import calculator.funcTools.DynArray;
import calculator.funcTools.Frame;
import calculator.opHandlers.OprandFunc;

public class InfixToPostfix {
// the second version where operators can be either binary or unary!
	public static DynArray<Frame> infixToPostfix2(CircLinkList<Frame> llk) {
		ArrStack<Frame> stkOp = new ArrStack<>(llk.size() / 2);// holds the operators temporarily
		DynArray<Frame> result = new DynArray<>(llk.size());// store the result: post-fix string

		for (int i = 0; i < llk.size(); i++) {
			Frame fr = llk.get(i);

			// if the element is a number, var, or funcVar
			if (OprandFunc.isOprand(fr.sT)) {
				result.add(fr);
			} else if (stkOp.isEmpty()) {
				stkOp.push(fr);
			} else {// if the fr is an operator
				while (!stkOp.isEmpty() && stkOp.peek().prio >= fr.prio && !Brackets.isOpenBrkt(fr.op)) {
					if (Brackets.isOpenBrkt(stkOp.peek().op)) {
						stkOp.pop();
						break;
					}
					result.add(stkOp.pop());
				}
				if (!Brackets.isCloseBrkt(fr.op)) {
					stkOp.push(fr);
				}
			}
		} // move the rest of operators to the result string
		while (!stkOp.isEmpty()) {
			result.add(stkOp.pop());
		}
		return result;
	}
}
