package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;
import calculator.funcTools.AccNumHashMap;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;

public class Mode extends UnaryOperation {

	public Mode() {
		super("mode");
		// TODO Auto-generated constructor stub
	}

	public static Double mode(Double[] data) {
		// find min value in data array
		AccNumHashMap hashmap = new AccNumHashMap();
		for (Double e : data) {
			hashmap.put(e, 1);
		}
		int max = 0, currVal;
		Double maxNum = 0.0;
		/*
		 * comparison between extracting elements from a hashmap to dynarr and traverse
		 * which (i), and direct traversal to hashmap(ii)
		 * 
		 * 1. (i) makes the code simpler
		 * 
		 * 2. but compared to (ii), (i) increase the time complexity by the size of the
		 * hashmap because it needs to add all elements from the hashmap to the dynarr
		 * first
		 */
		DynArray<Entry<Double, Integer>> dynarr = hashmap.traverse();

		for (int i = 0; i < dynarr.size(); i++) {
			currVal = dynarr.get(i).val;
			if (currVal > max) {
				max = currVal;
				maxNum = dynarr.get(i).key;
			}
		}
		return maxNum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) mode((Double[]) numObj);
	}

}
