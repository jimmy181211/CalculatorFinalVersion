package calculator.funcTools;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class DynArray<E> extends List<E>{
	protected E[] dynarr;
	protected int len;// the increase in size every time addMemo is executed
	protected int size;
	protected int amount = 4;

	public DynArray(int len) {
		this.dynarr = (E[]) new Object[len];
		this.len = len;
	}
	
	protected void addMemo() {
		this.len += this.amount;
		E[] temp = (E[]) new Object[this.len];
		System.arraycopy(dynarr, 0, temp, 0, dynarr.length);
		this.dynarr = temp;
	}
	
	public boolean add(E val) {
		mode.ROMcheck();
		if (size >= len) {// when the array is full
			addMemo();
		}
		dynarr[size++] = val;
		return true;
	}

	public boolean replace(E val, int idx) {
		mode.ROMcheck();
		if (idx < 0 || idx >= size) {
			return false;
		}
		this.dynarr[idx] = val;
		return true;
	}

	public boolean insert(E val, int idx) {
		mode.ROMcheck();
		if (idx > size || idx < 0) {// when idx==size, it becomes the add function
			return false;
		}

		if (size >= len) {
			addMemo();
		}
		System.arraycopy(dynarr, idx, dynarr, idx + 1, size - idx);///////
		if (dynarr[idx] == null) {
			size++;
		}
		dynarr[idx] = val;
		return true;
	}

	public E removeAt(int idx) {
		mode.ROMcheck();
		if (idx >= size || idx < 0) {
			return null;
		}
		E removed = dynarr[idx];
		if (idx < size - 1) {
			System.arraycopy(dynarr, idx + 1, dynarr, idx, size - idx - 1);
		}
		size--;
		return removed;
	}

	public E removeVal(E val) {
		mode.ROMcheck();
		for (int idx = 0; idx < size; idx++) {
			if (dynarr[idx].equals(val)) {
				E removed = dynarr[idx];
				if (idx < size - 1) {
					System.arraycopy(dynarr, idx + 1, dynarr, idx, size - idx - 1);
				}
				size--;
				return removed;
			}
		}
		return null;
	}

	public boolean contains(E val) {
		for (int i = 0; i < size; i++) {
			if (dynarr[i].equals(val)) {
				return true;
			}
		}
		return false;
	}

	public E get(int idx) {
		if (idx >= size || idx < 0) {
			return null;
		}
		return this.dynarr[idx];
	}

	public void traverse(Consumer<E> rcpt) {
		for (int i = 0; i < size; i++) {
			rcpt.accept(dynarr[i]);
		}
	}

	public int size() {
		return this.size;
	}

	public int len() {
		return this.len;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < size; i++) {
			result.append(dynarr[i].toString());
		}
		return result.toString();
	}

	public String toString(String symbol) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < size; i++) {
			result.append(dynarr[i].toString() + symbol);
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	public DynArray<E> clone() {
		DynArray<E> newArr = new DynArray<>(this.len);
		for (int i = 0; i < size; i++) {
			newArr.add(dynarr[i]);
		}
		return newArr;
	}

	public DynArray<E> createDynArray(E[] arr) {
		DynArray<E> dynarr = new DynArray<>(arr.length);
		for (E e : arr) {
			dynarr.add(e);
		}
		return dynarr;
	}
	
	public DynArray<E> ROM(boolean isROM) {
		mode.ROM(isROM);
		return this;
	}

	public E get() {
		return get(this.size-1);
	}
}
