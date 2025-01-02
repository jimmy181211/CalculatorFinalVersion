package calculator.funcTools;

import java.lang.reflect.Array;

public class GenericArr<T> {
	protected T[] arr;
	protected int furthestPos=-1;
	protected int size=0;
	
	@SuppressWarnings("unchecked")
	public GenericArr(Class<T> type,int len) {
		this.arr=(T[])Array.newInstance(type, len);
	}
	
	public int size() {
		return this.size;
	}
	
	protected boolean isValidIdx(int idx) {
		return idx<arr.length && idx>=0;
	}
	
	protected void updatePos(int idx) {
		if(furthestPos<idx) {
			furthestPos=idx;
		}
	}
	
	public void add(int idx,T val) {
		if(isValidIdx(idx)) {
			arr[idx]=val;
			updatePos(idx);
			size++;
		}
	}
	
	public void add(T val) {
		arr[size]=val;
		updatePos(size);
		size++;
	}
	
	public int len() {
		return arr.length;
	}
	
	public T get(int idx) {
		if(isValidIdx(idx)) {
			return arr[idx];
		}
		return null;
	}
}
