package calculator.funcTools;

import calculator.expr.Var;

public class HashMapVar<E> extends AbstrHashMap<Var, E> {

	public HashMapVar() {
		super();
	}

	@Override
	protected int hash(Var key) {
		String val = key.content().toString();
		int len = val.length() > 4 ? 4 : val.length();
		int hashCode = 0;
		for (int i = 0; i < len; i++) {
			hashCode += val.charAt(i);
		}
		return hashCode;
	}

	public void put(String key, E val) {
		super.put(new Var(key), val);
	}

	public E get(String key) {
		return super.get(new Var(key));
	}

	public HashMapVar<E> clone() {
		return super.clone(new HashMapVar<>());
	}
	
	public <H extends HashMapVar<E>>H clone(H hashmap){
		return super.clone(hashmap);
	}
}
