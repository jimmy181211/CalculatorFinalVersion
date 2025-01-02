package calculator.funcTools;

import java.util.function.Consumer;

public abstract class HashStack<E, K> extends AbstrHashMap<E, K> {
	protected StkEntry<E, K> lastEl;
	protected StkEntry<E, K>[] table;

	@SuppressWarnings("unchecked")
	public HashStack(int capacity) {
		super(capacity);
		table = new StkEntry[capacity];
		lastEl = new StkEntry<>();
	}

	public HashStack() {
		super();
	}

	protected int hashToIdx(int hashVal) {
		return hashVal % capacity;
	}

	protected abstract int hash(E key);

	public int size() {
		return this.size;
	}

	public Entry<E, K> getE() {
		if (lastEl != null) {
			return lastEl;
		}
		return null;
	}

	public StkEntry<E, K> getE(E key) {
		int hashVal = hash(key);
		int idx = hashToIdx(hashVal);
		if (table[idx] == null) {
			return null;
		}
		StkEntry<E, K> p = table[idx];
		while (p != null) {
			if (p.key.equals(key)) {
				return p;
			}
			p = p.nextNode;
		}
		return null;
	}

	public K get(E key) {
		StkEntry<E, K> entry = getE(key);
		return entry != null ? entry.val : null;
	}

	public void put(E key, K val) {
		int hashVal = hash(key);
		int idx = hashToIdx(hashVal);
		// update the lastEl
		StkEntry<E, K> prev = lastEl;
		lastEl = new StkEntry<>(hashVal, key, val);
		lastEl.prevEl = prev;
		prev.nextEl = lastEl;

		if (table[idx] == null) {
			table[idx] = lastEl;
		} else {
			StkEntry<E, K> p = table[idx];
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
			p.nextNode = lastEl;
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
		StkEntry<E, K>[] newTable = new StkEntry[capacity];
		for (int i = 0; i < table.length; i++) {
			StkEntry<E, K> p = table[i];
			StkEntry<E, K> a = null, b = null, ahead = null, bhead = null;
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

	// this method can't delete the top level element
	public StkEntry<E, K> removeE(E key) {
		if (size <= 0) {
			return null;
		}
		int hashVal = hash(key);
		int idx = hashToIdx(hashVal);
		if (table[idx] == null) {
			return null;
		}
		StkEntry<E, K> p = table[idx];
		StkEntry<E, K> prev = null;
		while (p != null) {
			if (p.key.equals(key)) {
				// delete the first element
				if (prev == null) {
					deleteLinks(table[idx]);
					table[idx] = p.nextNode;
				}
				// delete the other other elements
				else {
					deleteLinks(p);
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

	public K remove(E key) {
		StkEntry<E, K> entry = removeE(key);
		return entry != null ? entry.val : null;
	}

	private void deleteLinks(StkEntry<E, K> deleted) {
		if (deleted.nextEl != null) {
			deleted.nextEl.prevEl = deleted.prevEl;
			deleted.prevEl.nextEl = deleted.nextEl;
		}
		// or the deleted node is lastEl
		else {
			lastEl = lastEl.prevEl;
			lastEl.nextEl = null;
		}
	}

	// a stack method
	public StkEntry<E, K> removeE() {
		return removeE(lastEl.key);
	}

	public K remove() {
		StkEntry<E, K> entry = removeE();
		return entry != null ? entry.val : null;
	}
	
	public void traverseIter(Consumer<StkEntry<E, K>> rcpt) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				continue;
			}
			for (StkEntry<E, K> p = table[i]; p != null; p = p.nextNode) {
				rcpt.accept(p);
			}
		}
	}
}
