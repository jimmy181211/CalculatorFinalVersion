package calculator.funcTools;

public class Entry<E, T> {
	int hashVal;
	public E key;
	public T val;
	// next linked node, to solve hash collision
	public Entry<E, T> nextNode;

	public Entry(int hashVal, E key, T val) {
		this.key = key;
		this.val = val;
		this.hashVal = hashVal;
	}

	public Entry() {
	}

	@Override
	public String toString() {
		return key.toString() + val.toString();
	}
}
