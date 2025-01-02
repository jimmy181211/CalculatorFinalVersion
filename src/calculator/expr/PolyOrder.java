package calculator.expr;

import java.util.function.Consumer;

import calculator.ErrorCodes;
import calculator.funcTools.AbstrMaxHeap;
import calculator.funcTools.BiFunction;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.HashMapVars;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.HashMapVar;

public class PolyOrder {
	public static final int maxOrderSize = 32;

	static class Pair {
		public Term term;
		public Double pow;

		public Pair(Term term, Double pow) {
			this.term = term;
			this.pow = pow;
		}
	}

	// this function affects compare method in heap. It determines whether the heap
	// is maxHeap or minHeap
	private static BiFunction<Double, Double, Double> operate(boolean isAsc) {
		return isAsc ? (a, b) -> (a - b) : (a, b) -> (b - a);
	}

	// this method shows a general ordering, which can be reused by both
	// PolyAscOrder and PolyDesOrder class
	public static AbstrMaxHeap order(HashMapVars<Double, Double> exprPart, boolean isAsc) {

		BiFunction<Double, Double, Double> compare = operate(isAsc);
		AbstrMaxHeap heap = new AbstrMaxHeap(maxOrderSize) {
			// determine whether the heap is maximum or minimum
			@Override
			public Double compare(Object a, Object b) {
				return compare.apply(((Pair) a).pow, ((Pair) b).pow);
			}

			@Override
			public void traverse(Consumer<Object> rcpt) {
			}
		};
		
		DynArray<Entry<HashMapVar<Double>,Double>> dynarr= exprPart.traverse();
		DynArray<Entry<Var,Double>> term;
		Double max,curr;
		for(int i=0;i<dynarr.size();i++) {
			// get the max pow in each term
			max = Double.MIN_VALUE;
			term = dynarr.get(i).key.traverse();
			for(int j=0;j<term.size();j++) {
				curr=term.get(j).val;
				if (curr > max) {
					max = curr;
				}
			}
			// maxheap add the element in
			heap.add(new Pair(new Term(dynarr.get(i)), max));
		}
		return heap;
	}

	public static AbstrMaxHeap[] order(Expr expr, boolean isAsc) {
		// the first position holds the numerator while the second one holds the
		// denominator
		AbstrMaxHeap[] heapList = new AbstrMaxHeap[2];
		heapList[0] = order(expr.getNum(), isAsc);
		heapList[1] = order(expr.getDenom(), isAsc);
		return heapList;
	}

	public static String heapToString(AbstrMaxHeap[] heaps) {
		return HeapToString.heapToString(heaps);
	}

	public static void checkSortSize(Expr expr) {
		// decide whether it is bigger than the maximum sorting size
		int max = expr.getNum().size();
		int size2 = expr.getDenom().size();
		if (size2 > max) {
			max = size2;
		}
		if (max > PolyOrder.maxOrderSize) {
			System.out.println(ErrorCodes.get("07") + ": the number of terms to order is: " + max
					+ ", which is bigger than the maximum order size " + PolyOrder.maxOrderSize);
			System.exit(0);
		}
	}
}
