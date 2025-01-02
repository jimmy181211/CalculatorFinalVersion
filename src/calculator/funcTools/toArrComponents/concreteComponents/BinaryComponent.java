package calculator.funcTools.toArrComponents.concreteComponents;

import calculator.SymbolType.ST;
import calculator.funcTools.Brackets;
import calculator.funcTools.CircLinkList;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.funcTools.toArrComponents.AddVars;
import calculator.funcTools.toArrComponents.DataInfo;
import calculator.funcTools.toArrComponents.ToArrComp;
import calculator.operators.BinaryOperators;

public final class BinaryComponent extends ToArrComp {
	public BinaryComponent(HashMapS<Object> varMap, String expr) {
		super(varMap, expr, new BinaryOperators(), 2);
	}

	public BinaryComponent() {}
	
	@Override
	public Boolean handle(Entry<String, Object> node, StringBuilder cache) {
		// add the oprands
		CircLinkList<Frame> result=data.getResult();
		result.add(new AddVars().addVars(cache.substring(0, cache.length() - node.key.length()), data.getVarMap()));
		// for minus sign '-' to be a negative symbol
		if ((DataInfo.i() == 0 || (result.size() > 0 && Brackets.isOpenBrkt(result.get().op.charAt(0))))
				&& node.key.equals("-")) {
			result.add(Frame.frame("~", ST.un));
		} else {
			result.add(new Frame().op(node.key).type(ST.bin).prio((Integer)node.val));
		}
		return true;
	}
}
