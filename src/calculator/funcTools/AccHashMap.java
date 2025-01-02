package calculator.funcTools;

public abstract class AccHashMap<E, T> extends AbstrHashMap<E, T> {

	public AccHashMap() {
		super();
	}

	@Override
	public void put(E key, T val) {
		int hashVal = this.hash(key);
		int idx = hashVal % this.table.length;

		if (table[idx] == null) {
			table[idx] = new Entry<>(hashVal, key, val);
		} else {
			Entry<E, T> p = table[idx];
			while (true) {
				if (p.key.equals(key)) {
					// this time it is increment instead of replacement
					p.val = add(p.val, val);
					return;
				}
				if (p.nextNode == null) {
					break;
				}
				p = p.nextNode;
			}
			p.nextNode = new Entry<>(hashVal, key, val);
		}
		size += 1;
		// resize the hashMap if necessary
		if (size > threshold) {
			resize();
		}
	}

	protected abstract T add(T val1, T val2);
}
