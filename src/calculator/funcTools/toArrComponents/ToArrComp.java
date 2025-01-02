package calculator.funcTools.toArrComponents;

import calculator.AbstrHandler;
import calculator.funcTools.HashMapS;
import calculator.funcTools.MyMap.Entry;
import calculator.operators.AbstrOperators;

/*
 * @version v2.0
 * @ClassName: ToArrComponent
 * @Description: this class is an abstract class. Its relationship with ToArray is composition
 * for each type of operator (eg. binary, unyar, function, and func operator), it has an independent class 
 * that extends ToArrayComponent, so that the related operator identifier appeared in the string expression can be 
 * identified and processed
 * It uses behavioural design pattern: Chain Of Responsibility
 * the order of components in the chain will be determined by the number of times they are called. 
 * The greater the number of times they are called, the higher the priority and hence move up the chain
 * so the chain will change structure dynamically, the priority algorithm is not yet decided. 
 * Also, the chain will be maintained inside a class called ComponentManager.
 */
public abstract class ToArrComp extends AbstrHandler<StringBuilder> {
	public static FlyWeight data;
	protected AbstrOperators opObj;
	protected Integer prio;
	/*
	 * set it static to prevent unnecessary initialisation of the class when using
	 * recursion (UDFunc)
	 */
	protected ToArrComp nextHandler;

	public ToArrComp(HashMapS<Object> varMap, final String expr, AbstrOperators opObj, Integer prio) {
		// in the main program it will only be initialised once unless ToArray is
		// executed again in function operation
		data = new FlyWeight().setExpr(expr).setVarMap(varMap);
		this.opObj = opObj;
		this.prio = prio;
	}

	// this empty constructor is for the ComponentManager to loop through the
	// toArrComponents.concreteComponents package without the need for a parameter
	public ToArrComp() {
	}

	public Integer getPrio() {
		return this.prio;
	}

	// find operator
	protected Entry<String, Object> rfind(StringBuilder expr) {
		if (opObj == null) {
			new Exception("the opObj attribute of a sub-class of ToArrComp is not initialised").printStackTrace();
		}
		String temp;
		Object val;
		int len = expr.length();
		int startpos = len - opObj.opMaxSz;
		// consider when maxsize is bigger than len
		startpos = startpos < 0 ? 0 : startpos;

		for (int i = len - 1; i >= startpos; i--) {
			temp = expr.substring(i, len);
			val = opObj.get(temp);// determine if the operator is found
			if (val != null) {
				// if is valid, return the node
				return new Entry<String, Object>(temp, val);
			}
		}
		return null;
	}

	public abstract Boolean handle(Entry<String, Object> node, StringBuilder cache);

	@Override
	public Boolean handle(StringBuilder cache) {
		Entry<String, Object> node = rfind(cache);
		if (node != null) {
			// this will always return true
			return handle(node, cache);
		} else {
			return false;
		}
	}

	@Override
	public Boolean submit(StringBuilder cache) {
		Boolean result = handle(cache);
		if (result) {
			DataInfo.setCacheStatus(true);
			return true;
		} else if (!result && nextHandler != null) {
			return nextHandler.handle(cache);
		} else {
			DataInfo.setCacheStatus(false);
			return false;
		}
	}
}
