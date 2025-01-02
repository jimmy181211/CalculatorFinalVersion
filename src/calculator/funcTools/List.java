package calculator.funcTools;

import java.util.function.Consumer;

public abstract class List<E> {
	public abstract E get(int i);

	public abstract boolean add(E val);

	public abstract boolean insert(E val, int i);

	public abstract void traverse(Consumer<E> rcpt);

	public abstract E removeAt(int i);

	public abstract String toString();

	public abstract int size();

	protected AccessMode mode=new AccessMode();
}
