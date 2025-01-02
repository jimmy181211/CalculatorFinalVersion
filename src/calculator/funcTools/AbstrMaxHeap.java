package calculator.funcTools;

import java.util.function.Consumer;

public abstract class AbstrMaxHeap {
	protected Object[] hp;
	protected int capacity, size = 0;

	public AbstrMaxHeap(int capacity) {
		this.hp = new Object[capacity];
		this.capacity = capacity;
	}

	public boolean isFull() {
		return this.size == this.capacity;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public abstract Double compare(Object a, Object b);

	public boolean add(Object obj) {
		if (isFull()) {
			return false;
		}
		size++;
		this.up(obj);
		return true;
	}

	public int size() {
		return this.size;
	}

	private void up(Object obj) {
		int child = size - 1;
		int parent = (child - 1) / 2;
		while (child > 0 && compare(obj, hp[parent]) > 0) {
			hp[child] = hp[parent];
			child = parent;
			parent = (child - 1) / 2;
		}
		hp[child] = obj;
	}

	private void down(int parent) {
		int left = parent * 2 + 1;
		int right = left + 1;
		int max = parent;
		if (left < size && compare(hp[left], hp[max]) > 0) {
			max = left;
		}
		if (right < size && compare(hp[right], hp[max]) > 0) {
			max = right;
		}
		if (parent != max) {
			swap(max, parent);
			down(parent);
		}
	}

	private void swap(int idx1, int idx2) {
		Object temp = this.hp[idx1];
		hp[idx1] = hp[idx2];
		hp[idx2] = temp;
	}

	// remove the largest value
	public Object remove() {
		if (isEmpty()) {
			return null;
		}
		swap(0, --size);
		Object removed = hp[size];
		down(0);
		return removed;
	}

	public Object get() {
		if (isEmpty()) {
			return null;
		}
		return hp[0];
	}

	public abstract void traverse(Consumer<Object> rcpt);

	static class MaxHeap extends AbstrMaxHeap {
		public MaxHeap() {
			super(16);
		}

		// low performance method, often used for testing purposes
		public void traverse(Consumer<Object> rcpt) {
			MaxHeap temp = clone();
			while (!temp.isEmpty()) {
				rcpt.accept(temp.remove());
			}
		}

		@Override
		public Double compare(Object a, Object b) {
			return (Double) b - (Double) a;
		}

		public MaxHeap clone() {
			MaxHeap cloned = new MaxHeap();
			cloned.hp = this.hp.clone();
			cloned.capacity = this.capacity;
			cloned.size = this.size;
			return cloned;
		}
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Double[] arr = { 4.0, 3.1, 2.2, 7.1, 1.9 };
		MaxHeap maxhp = new MaxHeap();
		for (Double e : arr) {
			maxhp.add(e);
		}
		maxhp.traverse(values -> {
			System.out.println(values);
		});
	}
}
