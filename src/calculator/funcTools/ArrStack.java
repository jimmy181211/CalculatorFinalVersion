package calculator.funcTools;

public class ArrStack<E> extends Stack<E>{
	private E[] stk;
	@SuppressWarnings("unchecked")
	public ArrStack(int capacity) {
		this.stk=(E[])new Object[capacity];
		this.capacity=capacity;
	}
	
	@Override
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		return this.stk[--size];
	}
	@Override
	public boolean push(E val) {
		if(isFull()) {
			return false;
		}
		stk[size++]=val;
		return true;
	}
	@Override
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		return this.stk[size-1];
	}
	
	public void print() {
		for(int i=0;i<size;i++) {
			System.out.print(stk[i]+" ");
		}
	}
	
	public static void main(String[] args) {
		Integer[] arr= {};
		ArrStack<Integer> stk=new ArrStack<>(10);
		for(Integer e:arr) {
			stk.push(e);
		}
	}
}
