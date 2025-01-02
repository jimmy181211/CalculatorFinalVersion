package calculator.funcTools.toArrComponents;

import calculator.funcTools.Brackets;
import calculator.funcTools.CircLinkList;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.operators.AbstrOperators;

/*
 * @version v1.0
 * @Description: the classes that extends UnaryComponent all have the same variable extraction process but different parameter process
 */
public abstract class UnaryComponent extends ToArrComp {

	public UnaryComponent(HashMapS<Object> varMap, String expr, AbstrOperators opObj,
			Integer prio) {
		super(varMap, expr, opObj, prio);
		// TODO Auto-generated constructor stub
	}
	public UnaryComponent() {}

	public abstract void processParams(Entry<String,Object> node);

	@Override
	public Boolean handle(Entry<String, Object> node, StringBuilder cache) {
		CircLinkList<Frame> circllk =new AddVars().addVars(cache.substring(0, cache.length() - node.key.length()),
				data.getVarMap());
		CircLinkList<Frame> result=data.getResult();
		result.add(circllk);
		// for those not being open brkt eg (, '*' sign is needed
		if (circllk != null && result.size() > 0 && !Brackets.isOpenBrkt(result.get().op.charAt(0))) {
			result.add(AddVars.multiplyOp);
		}
		processParams(node);
		return true;
	}
}
