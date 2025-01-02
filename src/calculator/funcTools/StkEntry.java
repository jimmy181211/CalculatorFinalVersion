package calculator.funcTools;

public class StkEntry<E, T> extends Entry<E, T> {
	public StkEntry<E, T> nextNode;
	// previous element, for stack
	public StkEntry<E, T> prevEl;
	// next element for stack
	public StkEntry<E, T> nextEl;

	public StkEntry(int hashVal, E key, T val) {
		super(hashVal, key, val);
	}

	public StkEntry() {
	}
}
