package calculator.funcTools;

import calculator.ErrorCodes;
import calculator.expr.Var;

public class HashMapVars<K, E> extends AbstrHashMap<HashMapVar<K>, E> {

	public HashMapVars(int capacity) {
		super(capacity);
	}

	public HashMapVars() {
		super();
	}
	
	//select the first four string keys, and sum up the ASCII code of the first letter of each string
	@Override
	protected int hash(HashMapVar<K> key) {
		int len=key.size()>4?4:key.size();
		DynArray<Entry<Var,K>> entries=key.traverse(len);
		// convert the characters into number by ASCII mapping
		Integer hashVal = 0;
		for(int i=0;i<entries.size();i++) {
			hashVal+=entries.get(i).hashVal;
		}
		return hashVal;
	}

	// only when all the keys in hashMap1 are equal to those at hashMap2, they are
	// equal
	private boolean isEqual(HashMapVar<K> key1, HashMapVar<K> key2) {
		if (key1.size() != key2.size()) {
			return false;
		}
		DynArray<Entry<Var,K>> dynarrKey=key1.traverse();
		for(int i=0;i<dynarrKey.size();i++) {
			if (key2.getE(dynarrKey.get(i).key) == null) {
				return false;
			}
		}
		return true;
	}

	public void put(HashMapVar<K> key, E val) {
		mode.ROMcheck();
		if (key == null) {
			System.out.println(ErrorCodes.get("23") + ": the key put in the hashMap object can't be null!");
			System.exit(0);
		}
		int hashObj = hash(key);
		int idx = hashToIdx(hashObj);
		if (table[idx] == null) {
			table[idx] = new Entry<>(hashObj, key, val);
		}
		// handle hash collision
		else {
			Entry<HashMapVar<K>, E> p = table[idx];
			while (true) {
				// determine isEqual part is different from its parent class
				// it states that if the hash values are the same, the keys are the same
				if (isEqual(p.key, key)) {
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

	/*
	 * the special cases where there is only one element in the key:HashMapVar
	 */
	public Entry<HashMapVar<K>, E> getE(String var, K val) {
		return super.getE(createKey(new Var(var), val));
	}

	private HashMapVar<K> createKey(Var var, K val) {
		HashMapVar<K> key = new HashMapVar<>();
		key.put(var, val);
		return key;
	}

	public Entry<HashMapVar<K>, E> removeE(String var, K val) {
		return super.removeE(createKey(new Var(var), val));
	}

	// clone the HashMap
	public HashMapVars<K, E> clone() {
		return super.clone(new HashMapVars<>());
	}
	
	public <H extends HashMapVars<K,E>>H clone(H hashmap){
		return super.clone(hashmap);
	}
}
