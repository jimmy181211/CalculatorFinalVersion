package calculator.funcTools.toArrComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;
import calculator.funcTools.HashMapS;
import calculator.funcTools.AbstrMaxHeap;
import calculator.funcTools.toArrComponents.concreteComponents.FunctionComponent;
import calculator.opHandlers.AssembleOpObjs;
import calculator.operators.FunctionOperators;


public class ComponentManager implements VarInitialiser<ComponentManager> {
	// share fly-weight
	private ToArrComp head = null;
	private AbstrMaxHeap maxhp = new AbstrMaxHeap(10) {
		@Override
		public Double compare(Object a, Object b) {
			return (double) (((ToArrComp) a).getPrio() - ((ToArrComp) b).getPrio());
		}

		@Override
		public void traverse(Consumer<Object> rcpt) {
			return;
		}
	};

	// functionComponent is quite special because it is the only one who needs
	// parameters to initialise
	// so this methods distinguishes the treatment of FunctionComponent from the other classes
	private ToArrComp getHandler(FunctionOperators funcOpObj) {
		if (maxhp.get().getClass() == FunctionComponent.class) {
			return ((FunctionComponent) maxhp.remove()).setFunctionOperators(funcOpObj);
		} else {
			return (ToArrComp) maxhp.remove();
		}
	}

	/*
	 * after the initialisation, the chain is established. However, the required
	 * parameters are not passed into the objects
	 */
	private void initialise(FunctionOperators funcOpObj) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
		if (funcOpObj == null) {
			new Exception("the instance of class FunctionOperators which passes into ComponentManager class can't be null!").printStackTrace();;
		}
		List<ToArrComp> list = AssembleOpObjs.getOpObjs("calculator.funcTools.toArrComponents.concreteComponents");
		// maxHeap order the components in the order of priority. Based on this to make
		// responsibility chain
		for (ToArrComp comp : list) {
			maxhp.add(comp);
		}
		head = getHandler(funcOpObj);
		ToArrComp curr = head;
		while (!maxhp.isEmpty()) {
			curr.setNext(getHandler(funcOpObj));
			curr = curr.nextHandler;
		}
	}

	public ComponentManager(FunctionOperators funcOpObj) throws Exception {
		initialise(funcOpObj);
	}

	/*
	 * 'i' is the current looping variable in ToArray, determining what character it
	 * is pointing to although returning DataInfo is unnecessary technically, it
	 * reminds me to update i and cache in ToArray Class
	 */
	public DataInfo work(Integer i, StringBuilder cache) {
		if (!ToArrComp.data.isInitialised()) {
			new Exception("the parameters needed for ToArrayComponent are not filled, can't call work() method").printStackTrace();
		}
		DataInfo.setI(i);
		head.submit(cache);
		return new DataInfo();
	}

	// method chaining
	@Override
	public ComponentManager setVarMap(HashMapS<Object> varMap) {
		ToArrComp.data.setVarMap(varMap);
		return this;
	}

	@Override
	public ComponentManager setExpr(String expr) {
		ToArrComp.data.setExpr(expr);
		return this;
	}

	public ComponentManager initFuncOpObj(FunctionOperators funcOpObj) throws Exception {
		if (head == null) {
			synchronized (ComponentManager.class) {
				if (head == null) {
					initialise(funcOpObj);
				}
			}
		}
		return this;
	}
}
