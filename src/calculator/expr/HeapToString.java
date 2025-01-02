package calculator.expr;

import calculator.expr.PolyOrder.Pair;
import calculator.funcTools.AbstrMaxHeap;

class HeapToString {
	// the value Object has a type Entry<HashMapS<Double>,Double>
	private static StringBuilder heapToString(AbstrMaxHeap heap) {
		StringBuilder expr = new StringBuilder();
		while (heap.size() != 0) {
			expr.append(ExprToString.sSentryToString(((Pair) heap.remove()).term));
		}
		return ExprToString.fixStrFront(expr);
	}

	public static String heapToString(AbstrMaxHeap[] heaps) {
		StringBuilder numerator = heapToString(heaps[0]);
		StringBuilder denominator = heapToString(heaps[1]);
		return numerator.append(" / ").append(denominator).toString();
	}

}
