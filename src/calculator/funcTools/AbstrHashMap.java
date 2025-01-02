package calculator.funcTools;

import java.util.function.Consumer;
import calculator.ErrorCodes;

public abstract class AbstrHashMap<E, T> {
	protected int capacity = 64;
	public Entry<E, T>[] table;
	protected int size = 0;
	protected double loadFactor = 0.75;
	protected double threshold = loadFactor * capacity;
	// is ReadOnlyMode
	protected AccessMode mode = new AccessMode();

	@SuppressWarnings("unchecked")
	public AbstrHashMap(int capacity) {
		table = new Entry[capacity];
		init();
	}

	private void init() {
		this.capacity = table.length;
		threshold = capacity * loadFactor;
	}

	@SuppressWarnings("unchecked")
	public <K extends AbstrHashMap<E, T>> K ROM(boolean isROM) {
		mode.ROM(isROM);
		return (K) this;
	}

	@SuppressWarnings("unchecked")
	public AbstrHashMap() {
		this.table = new Entry[this.capacity];
		init();
	}

	protected abstract int hash(E key);

	protected int hashToIdx(Integer hashObj) {
		return hashObj % table.length;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public Entry<E, T> getE(E key) {
		int hashObj = hash(key);
		int idx = hashToIdx(hashObj);
		if (table[idx] == null) {
			return null;
		}
		Entry<E, T> p = table[idx];
		while (p != null) {
			if (p.key.equals(key)) {
				return p;
			}
			p = p.nextNode;
		}
		return null;
	}

	public T get(E key) {
		Entry<E, T> entry = getE(key);
		return entry != null ? entry.val : null;
	}

	// appending a potential linkedlist
	public void put(Entry<E, T> entry) {
		mode.ROMcheck();
		if (entry == null) {
			System.out.println(ErrorCodes.get("23") + ": the entry object put in the hashMap object can't be null!");
			System.exit(0);
		}
		int idx = hashToIdx(entry.hashVal);
		// calculate the number of elements that will be added
		Entry<E, T> curr = entry;
		while (curr != null) {
			size++;
			curr = curr.nextNode;
		}
		if (table[idx] == null) {
			table[idx] = entry;
		} else {
			Entry<E, T> p = table[idx];
			while (true) {
				if (entry == null) {
					return;
				}
				if (p.key.equals(entry.key)) {
					p.val = entry.val;
					entry = entry.nextNode;
					size--;
				}
				if (p.nextNode == null) {
					break;
				}
				p = p.nextNode;
			}
			p.nextNode = entry;
		}
		// resize the hashMap if necessary
		while (size > threshold) {
			resize();
		}
	}

	public void put(E key, T val) {
		mode.ROMcheck();
		if (key == null) {
			System.out.println(ErrorCodes.get("23") + ": the key put in the hashMap object can't be null!");
			System.exit(0);
		}
		int hashObj = hash(key);
		int idx = hashToIdx(hashObj);
		if (table[idx] == null) {
			table[idx] = new Entry<>(hashObj, key, val);
		} else {
			Entry<E, T> p = table[idx];
			while (true) {
				if (p.key.equals(key)) {
					p.val = val;
					return;
				}
				if (p.nextNode == null) {
					break;
				}
				p = p.nextNode;
			}
			p.nextNode = new Entry<>(hashObj, key, val);
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
		Entry<E, T>[] newTable = new Entry[capacity];
		for (int i = 0; i < table.length; i++) {
			Entry<E, T> p = table[i];
			Entry<E, T> a = null, b = null, ahead = null, bhead = null;
			while (p != null) {
				if ((p.hashVal & table.length) == 0) {
					if (a != null) {
						a.nextNode = p;
					}
					// when it's the first element, use ahead to mark it
					else {
						ahead = p;
					}
					a = p;
				} else {
					if (b != null) {
						b.nextNode = p;
					} else {
						bhead = p;
					}
					b = p;
				}
				p = p.nextNode;
			}
			if (a != null) {
				// set a.next=null in case incorrect nodes are linked
				a.nextNode = null;
				newTable[i] = ahead;
			}
			if (b != null) {
				b.nextNode = null;
				newTable[i + table.length] = bhead;
			}
		}
		this.table = newTable;
		threshold = capacity * loadFactor;
	}

	public Entry<E, T> removeE(E key) {
		mode.ROMcheck();
		if (key == null) {
			System.out.println(
					ErrorCodes.get("23") + ": key parameter of remove function of the HashMap object can't be null!");
			System.exit(0);
		}
		int hashObj = hash(key);
		int idx = hashToIdx(hashObj);
		if (table[idx] == null) {
			return null;
		}
		Entry<E, T> p = table[idx];
		Entry<E, T> prev = null;
		while (p != null) {
			if (p.key.equals(key)) {
				// delete the first element
				if (prev == null) {
					table[idx] = p.nextNode;
				}
				// delete the other other elements
				else {
					prev.nextNode = p.nextNode;
				}
				size--;
				return p;
			}
			prev = p;
			p = p.nextNode;
		}
		// when the element is not found
		return null;
	}

	public T remove(E key) {
		Entry<E, T> entry = removeE(key);
		return entry != null ? entry.val : null;
	}

	public <K extends AbstrHashMap<E, T>> K clone(K newMap) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				// adding Entry object directly
				newMap.put(table[i]);
			}
		}
		return newMap;
	}

	//copy-pasta code that I can't avoid!
	public void traverse(Consumer<Entry<E, T>> rcpt) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				continue;
			}
			for (Entry<E, T> p = table[i]; p != null; p = p.nextNode) {
				rcpt.accept(p);
			}
		}
	}

	public DynArray<Entry<E, T>> traverse(int len) {
		int cnt = 0;
		DynArray<Entry<E, T>> dynarr = new DynArray<>(10);
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				continue;
			}
			for (Entry<E, T> p = table[i]; p != null; p = p.nextNode, cnt++) {
				if (cnt >= len) {
					return dynarr;
				}
				dynarr.add(p);
			}
		}
		return dynarr;
	}

	public DynArray<T> traverseVal(int len) {
		int cnt = 0;
		DynArray<T> dynarr = new DynArray<>(10);
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				continue;
			}
			for (Entry<E, T> p = table[i]; p != null; p = p.nextNode, cnt++) {
				if (cnt >= len) {
					return dynarr;
				}
				dynarr.add(p.val);
			}
		}
		return dynarr;
	}

	public DynArray<Entry<E, T>> traverse() {
		return traverse(this.size());
	}

	public DynArray<T> traverseVal() {
		return traverseVal(this.size());
	}

	public static void main(String[] args) {

	}
}
