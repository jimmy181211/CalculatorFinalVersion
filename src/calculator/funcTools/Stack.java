package calculator.funcTools;

public abstract class Stack<E> {
	protected int size;
	protected int capacity;

	public abstract E pop();

	public abstract boolean push(E val);

	public abstract E peek();

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == capacity;
	}
}
