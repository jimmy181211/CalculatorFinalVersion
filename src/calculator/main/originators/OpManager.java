package calculator.main.originators;

import calculator.funcTools.HashMapS;

public abstract class OpManager<E> {
	public HashMapS<E> opMap = new HashMapS<>();

	public OpManager() {
		this.opMap.ROM(true);
	}

	public void put(String key, E val) {
		opMap.ROM(false);
		opMap.put(key, val);
		opMap.ROM(true);
	}

	public E remove(String key) {
		opMap.ROM(false);
		E result = opMap.remove(key);
		opMap.ROM(true);
		return result;
	}
}
