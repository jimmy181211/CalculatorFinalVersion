package calculator.funcTools;

import java.util.function.Consumer;

public class HashMapOrig<E> {
	public static class Entry<E> {
		int hashVal;
		public E key;
		public Object val;
		public Entry<E> next;

		public Entry(int hashVal, E key, Object val) {
			this.key = key;
			this.val = val;
			this.hashVal = hashVal;
		}

		public Entry() {
		}
	}

	protected int capacity;
	public Entry<E>[] table;
	protected int size;
	protected double loadFactor;
	protected double threshold;

	@SuppressWarnings("unchecked")
	public HashMapOrig(int capacity) {
		this.capacity = capacity;
		table = new Entry[capacity];
		size = 0;
		loadFactor = 0.75;
		threshold = capacity * loadFactor;
	}

	@SuppressWarnings("unchecked")
	public HashMapOrig() {
		this.capacity = 16;
		table = new Entry[capacity];
		size = 0;
		loadFactor = 0.75;
		threshold = capacity * loadFactor;
	}

	protected int hash(int hashVal) {
		return hashVal % capacity;
	}

	public int size() {
		return this.size;
	}

	public Object get(int hashVal, E key) {
		int idx = hash(hashVal);
		if (table[idx] == null) {
			return null;
		}
		Entry<E> p = table[idx];
		while (p != null) {
			if (p.key.equals(key)) {
				return p.val;
			}
			p = p.next;
		}
		return null;
	}

	// appending a potential linkedlist
	public void put(Entry<E> entry) {
		int idx = hash(entry.hashVal);
		if (table[idx] == null) {
			table[idx] = entry;
		} else {
			Entry<E> p = table[idx];
			while (true) {
				if (entry == null) {
					return;
				}
				if (p.key.equals(entry.key)) {
					p.val = entry.val;
					entry = entry.next;
				}
				if (p.next == null) {
					break;
				}
				p = p.next;
			}
			p.next = entry;
		}
		size += 1;
		// resize the hashMap if necessary
		if (size > threshold) {
			resize();
		}
	}

	public void put(int hashVal, E key, Object val) {
		int idx = hash(hashVal);
		if (table[idx] == null) {
			table[idx] = new Entry<>(hashVal, key, val);
		} else {
			Entry<E> p = table[idx];
			while (true) {
				if (p.key.equals(key)) {
					p.val = val;
					return;
				}
				if (p.next == null) {
					break;
				}
				p = p.next;
			}
			p.next = new Entry<>(hashVal, key, val);
		}
		size += 1;
		// resize the hashMap if necessary
		if (size > threshold) {
			resize();
		}
	}

	protected void resize() {
		capacity *= 2;
		@SuppressWarnings("unchecked")
		Entry<E>[] newTable = new Entry[capacity];
		for (int i = 0; i < table.length; i++) {
			Entry<E> p = table[i];
			Entry<E> a = null, b = null, ahead = null, bhead = null;
			while (p != null) {
				if ((p.hashVal & table.length) == 0) {
					if (a != null) {
						a.next = p;
					}
					// when it's the first element, use ahead to mark it
					else {
						ahead = p;
					}
					a = p;
				} else {
					if (b != null) {
						b.next = p;
					} else {
						bhead = p;
					}
					b = p;
				}
				p = p.next;
			}
			if (a != null) {
				// set a.next=null in case incorrect nodes are linked
				a.next = null;
				newTable[i] = ahead;
			}
			if (b != null) {
				b.next = null;
				newTable[i + table.length] = bhead;
			}
		}
		this.table = newTable;
		threshold = capacity * loadFactor;
	}

	public Object remove(int hashVal, E key) {
		int idx = hash(hashVal);
		if (table[idx] == null) {
			return null;
		}
		Entry<E> p = table[idx];
		Entry<E> prev = null;
		while (p != null) {
			if (p.key.equals(key)) {
				// delete the first element
				if (prev == null) {
					table[idx] = p.next;
				}
				// delete the other other elements
				else {
					prev.next = p.next;
				}
				size--;
				return p.val;
			}
			prev = p;
			p = p.next;
		}
		// when the element is not found
		return null;
	}

	public HashMapOrig<E> clone() {
		HashMapOrig<E> newMap = new HashMapOrig<>(capacity);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				// adding Entry object directly
				newMap.put(table[i]);
			}
		}
		return newMap;
	}

	public void traverse(Consumer<Entry<E>> rcpt) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				continue;
			}
			for (Entry<E> p = table[i]; p != null; p = p.next) {
				rcpt.accept(p);
			}
		}
	}

	public static void main(String[] args) {
		HashMapOrig<String> hashMap = new HashMapOrig<>(4);
		String[] keys = { "a", "b", "c", "d" };
		Integer[] vals = { 2, 3, 1, 4 };
		for (int i = 0; i < keys.length; i++) {
			hashMap.put(2, keys[i], vals[i]);
		}
		hashMap.traverse(values->{
			System.out.println(values.hashVal+" "+values.key+" "+values.val);
		});
//		System.out.println(hashMap.capacity);
	}
}
