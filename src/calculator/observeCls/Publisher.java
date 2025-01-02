package calculator.observeCls;

import calculator.funcTools.DynArray;

public abstract class Publisher<E extends Subscriber<K>, K extends Enum<K>> {
	protected DynArray<E> subscrs;
	protected K currState;

	public Publisher(K mainState) {
		this.currState = mainState;
		this.subscrs = new DynArray<>(10);
	}

	public void subscribe(E subscr) {
		subscrs.add(subscr);
	}

	public void unsubscribe(E subscr) {
		subscrs.removeVal(subscr);
	}

	public void notifySubscriber(K state) {
		for (int i = 0; i < subscrs.size(); i++) {
			subscrs.get(i).update(state);
		}
		currState=state;
	}
	
	public K getCurrState() {
		return currState;
	}
}
