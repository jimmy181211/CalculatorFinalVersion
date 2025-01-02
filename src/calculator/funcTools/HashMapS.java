package calculator.funcTools;

import calculator.ErrorCodes;

public class HashMapS<E> extends AbstrHashMap<String, E> {

	public HashMapS(int capacity) {
		super(capacity);
	}

	public HashMapS() {
		super();
	}

	public static int hashS(String val) {
		int len = val.length() > 4 ? 4 : val.length();
		int hashCode = 0;
		for (int i = 0; i < len; i++) {
			hashCode += val.charAt(i);
		}
		return hashCode;
	}

	@Override
	protected int hash(String val) {
		return hashS(val);
	}

	public HashMapS<E> clone() {
		HashMapS<E> hashMap = new HashMapS<>(32);
		return super.clone(hashMap);
	}

	public static <E> HashMapS<E> createHashMap(String[] keys, E[] values) {
		if (keys.length != values.length) {
			System.out.println(ErrorCodes.get("17"));
			System.exit(0);
		}
		HashMapS<E> hashmap = new HashMapS<>(64);
		for (int i = 0; i < keys.length; i++) {
			hashmap.put(keys[i], values[i]);
		}
		return hashmap;
	}
}
