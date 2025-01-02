package calculator.operators;

import java.util.Optional;

import calculator.ErrorCodes;
import calculator.funcTools.HashMapS;

public abstract class AbstrOperators implements DSInfo{
	//for searching
	protected final HashMapS<Integer> ops;
	public int opMaxSz=0;
	
	public AbstrOperators(HashMapS<Integer> ops,int opMaxSz) {
		this.ops=Optional.ofNullable(ops).orElse(new HashMapS<>());
		this.opMaxSz=opMaxSz;
	}
	
	protected static HashMapS<Integer> initiate(String[] opKeys,Integer[] vals){
		if(opKeys.length!=vals.length) {
			System.out.println(ErrorCodes.get("21")+" for operator");
			System.exit(0);
		}
		return HashMapS.createHashMap(opKeys, vals);
	}
	
	public static int findMaxSize(String[] strs) {
		int max=0;
		for(String str:strs) {
			if(str.length()>max) {
				max=str.length();
			}
		}
		return max;
	}
	
	protected static Integer[] createPrios(int len,int prio) {
		Integer[] arr=new Integer[len];
		for(int i=0;i<len;i++) {
			arr[i]=prio;
		}
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object get(String key) {
		return ops.get(key);
	}

	@Override
	public int size() {
		return ops.size();
	}
}
