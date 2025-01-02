package calculator.funcTools;

import java.util.function.Consumer;

public class CircLinkList<E> extends List<E> {
	static class Node<E> {
		public E val;
		public Node<E> next;

		public Node(E val, Node<E> next) {
			this.val = val;
			this.next = next;
		}

		public Node(E val) {
			this.val = val;
		}

		public Node() {
		}
	}

	public Node<E> sentinel = new Node<>();
	public Node<E> lastNode;

	private int size;

	public CircLinkList() {
		this.size = 0;
		sentinel.next = sentinel;
		lastNode = sentinel;
	}

	public int size() {
		return this.size;
	}

	public E get(int i) {
		if (i < 0) {
			return null;
		}
		return toIdxFront(i).next.val;
	}

	public E get() {
		return get(size - 1);
	}

	private Node<E> toIdxFront(int idx) {
		Node<E> temp = sentinel;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public boolean add(E val) {
		if (val == null) {
			return false;
		}
		Node<E> newNode = new Node<>(val);
		newNode.next = sentinel;
		lastNode.next = newNode;
		this.lastNode = newNode;
		size++;
		return true;
	}

	public void addAt(E val, int i) {
		if (val == null) {
			return;
		}
		Node<E> temp = toIdxFront(i);
		Node<E> newNode = new Node<>(val);
		newNode.next = temp.next;
		temp.next = newNode;
		if (i == size++) {
			lastNode = newNode;
		}
	}

	public void addAt(CircLinkList<E> llk, int i) {
		if (llk == null) {
			return;
		}
		Node<E> temp = toIdxFront(i);
		Node<E> added = llk.sentinel;
		Node<E> node = added.next;
		while (node.next != added) {
			node = node.next;
		}
		node.next = temp.next;
		temp.next = added.next;
		if (i == size) {
			lastNode = node;
		}
		size += llk.size;
	}

	public void add(CircLinkList<E> llk) {
		if (llk == null) {
			return;
		}
		llk.lastNode.next = sentinel;
		this.lastNode.next = llk.sentinel.next;
		this.lastNode = llk.lastNode;
		size += llk.size;
	}

	public void addAtHead(E val) {
		addAt(val, 0);
	}

	public boolean insert(E val, int i) {
		addAt(val, i);
		return true;
	}

	public boolean insert(CircLinkList<E> llk, int i) {
		addAt(llk, i);
		return true;
	}

	public void traverse(Consumer<E> rcpt) {
		for (Node<E> temp = sentinel.next; temp != sentinel; temp = temp.next) {
			rcpt.accept(temp.val);
		}
	}

	public E removeAt(int i) {
		if (size <= 0) {
			return null;
		}
		Node<E> temp = toIdxFront(i);
		Node<E> removed = temp.next;
		temp.next = temp.next.next;
		if (i == --size) {
			lastNode = temp;
		}
		return removed.val;
	}

	public E remove() {
		return removeAt(size - 1);
	}

	public void clear() {
		this.sentinel.next = sentinel;
		this.size = 0;
		lastNode = sentinel;
	}

	public CircLinkList<E> clone() {
		CircLinkList<E> llk = new CircLinkList<>();
		for (Node<E> node = sentinel.next; node != sentinel; node = node.next) {
			llk.add(node.val);
		}
		return llk;
	}

	@Override
	public String toString() {
		Node<E> node = this.sentinel.next;
		// for the method to be executed, the type E has to be either string or
		// character
		if (node.val == null || !(node.val instanceof String || node.val instanceof Character)) {
			return null;
		}
		StringBuffer bf = new StringBuffer();
		for (; node != sentinel; node = node.next) {
			bf.append(node.val);
		}
		return bf.toString();
	}

	public static void main(String[] args) {
		String[] strs = { "ab", "bc", "er", "qw" };
//		String[] strs2 = { "k", "w", "q" };
		CircLinkList<String> llk = new CircLinkList<>();
//		CircLinkList<String> llk2=new CircLinkList<>();
//		for(String str:strs2) {
//			llk2.add(str);
//		}
		for (String str : strs) {
			llk.add(str);
		}
//		llk.add(llk2);
//		llk.remove();
//		System.out.println(llk.toString());
		CircLinkList<String> copy = llk.clone();
		llk.remove();
		System.out.println(copy.toString());
	}
}
