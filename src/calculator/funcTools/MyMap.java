package calculator.funcTools;

import java.util.function.Consumer;

public class MyMap <T,E>{
	public static class Entry<T,E>{
		public T key;
		public E val;
		public Entry(T key,E val) {
			this.key=key;
			this.val=val;
		}
	}
	
	public DynArray<Entry<T,E>> map=new DynArray<>(5);
	
	public MyMap(int capacity) {}

	public MyMap(T[] keys,E[] values) {
		if(keys.length!=values.length) {
			return;
		}
		for(int i=0;i<keys.length;i++) {
			this.add(keys[i],values[i]);
		}
	}
	
	public MyMap(T key,E value) {
		this.add(key, value);
	}
	
	public DynArray<Entry<T,E>> getArray(){
		return this.map;
	}

	public void add(T givenKey,E value) {
		for(int i=0;i<this.map.size();i++) {
			Entry<T,E> curr=this.map.get(i);
			if(curr.key!=null && curr.key.equals(givenKey)) {
				curr.val=value;
				return;
			}
		}
		map.add(new Entry<>(givenKey,value));
	}
	
	public E get(T key) {
		for(int i=0;i<map.size();i++) {
			Entry<T,E> element=map.get(i);
			if(element!=null && element.key.equals(key)) {
				return  element.val;
			}
		}return null;
	}
	
	public E remove(T key) {
		for(int i=0;i<map.size();i++) {
			Entry<T,E> element=map.get(i);
			if(element!=null && element.key.equals(key)) {
				map.removeAt(i);
				return element.val;
			}
		}return null;
	}
	
	public void traverse(Consumer<Entry<T,E>> rcpt) {
		for(int i=0;i<map.size();i++) {
			rcpt.accept(map.get(i));
		}
	}
	
	public static void main(String[] args) {
		String[] strs= {"a","b","c","d"};
		Double[] vals= {1.0,2.4,3.3,4.1};
		MyMap<String,DynArray<Double>> map=new MyMap<>(2);
		for(int i=0;i<strs.length;i++) {
			DynArray<Double> dynarr=new DynArray<>(2);
			dynarr.add(vals[i]);
			map.add(strs[i], dynarr);
		}
		map.traverse(values->{
			System.out.print(values.val.get(0)+" ");
		});
	}
}
