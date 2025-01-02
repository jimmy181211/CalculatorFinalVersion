package calculator.funcTools;

public interface ToString {

	public static <E> String toString(E[] arr) {
		if (arr == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		String elSep = ", ";
		for (E e : arr) {
			result.append(e.toString());
			result.append(elSep);
		}
		result.deleteCharAt(result.length() - elSep.length());
		return result.toString();
	}

	public static <E> String toString(E[][] arr, boolean isVertical) {
		if (arr == null) {
			return null;
		}
		String rowSep = isVertical ? "\n" : ";";
		String elSep = ", ";
		StringBuilder result = new StringBuilder();
		for (E[] es : arr) {
			for (E e : es) {
				result.append(e.toString());
				result.append(elSep);
			}
			result.deleteCharAt(result.length() - elSep.length());
			result.append(rowSep);
		}
		result.deleteCharAt(result.length() - elSep.length());
		return result.toString();
	}

	public static void main(String[] args) {
//		Integer[][] arr = { { 1, 2 }, { 3, 1 }, { 6, 5 }, { 9, 1 } };
		Integer[] arr = { 1, 2, 5, 3 };
		System.out.println(toString(arr));
	}
}
