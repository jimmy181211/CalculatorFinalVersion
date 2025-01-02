package calculator.funcTools.toArrComponents.concreteComponents;

import calculator.SymbolType.ST;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.funcTools.toArrComponents.UnaryComponent;
import calculator.operators.UnaryOperators;

public final class NormalUnaryComponent extends UnaryComponent {
	public NormalUnaryComponent(HashMapS<Object> varMap, final String expr) {
		super(varMap, expr, new UnaryOperators(), 3);
	}
	
	public NormalUnaryComponent(){}
	
	// df(x)/dx
	// d/dx(f(x)) ∫dx(f(x=(a->b))
	@Override
	public void processParams(Entry<String, Object> node) {
		data.getResult().add(new Frame().op(node.key).prio((Integer)node.val).type(ST.un));
	}
}