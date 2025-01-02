package calculator.funcTools;

public class Split {
	public static String[] split(String str, String splitter) {
		if (!contains(str, splitter)) {
			return new String[] { str };
		}
		int start = idxOf(str, splitter);
		return new String[] { str.substring(0, start), str.substring(start + splitter.length()) };
	}

	public static DynArray<String> splits(String str, String splitter) {
		DynArray<String> result = new DynArray<>(5);
		splitsInner(str, splitter, result);
		return result;
	}

	// this split preserves the content inside a bracket
	public static void splitsProtectedInner(String str, String splitter, Integer brktIdx, DynArray<String> result) {
		String[] temp;
		int idxStart = idxOf(str, Brackets.openBrkts[brktIdx], 0);
		int idxEnd;
		// if it contains bracket
		if (idxStart != -1) {
			splitsInner(str.substring(0, idxStart), splitter, result);
			idxEnd = idxOf(str, Brackets.closedBrkts[brktIdx], idxStart);
			temp = split(str.substring(idxEnd + 1), splitter);
			result.replace(result.get() + str.substring(idxStart, idxEnd + 1) + temp[0], result.size() - 1);
			if (temp.length == 1) {
				return;
			}
			splitsProtectedInner(temp[1], splitter, brktIdx, result);
		} else {
			splitsInner(str, splitter, result);
		}
	}

	public static DynArray<String> splitsProtected(String str, String splitter, char protect) {
		DynArray<String> result = new DynArray<>(5);
		// find the idx of the bracket
		Integer brktIdx = null;
		for (int i = 0; i < Brackets.openBrkts.length; i++) {
			if (protect == Brackets.openBrkts[i] || protect == Brackets.closedBrkts[i]) {
				brktIdx = i;
			}
		}
		// if protect is not in the BrktsList
		if (brktIdx == null) {
			new Exception("the symbol \"" + protect + "\" is not protective").printStackTrace();
		}
		splitsProtectedInner(str, splitter, brktIdx, result);
		return result;
	}

	public static boolean contains(String str, String target) {
		return idxOf(str, target) == -1 ? false : true;
	}

	public static boolean contains(String str, char target) {
		return idxOf(str, target, 0) == -1 ? false : true;
	}

	public static int idxOf(String str, char ch, int startIdx) {
		for (int i = startIdx; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				return i;
			}
		}
		return -1;
	}

	// overloaded method of idxOf(str, target,i)
	public static int idxOf(String str, String target) {
		return idxOf(str, target, 0);
	}

	public static int idxOf(String str, String target, int i) {
		int k = 0;
		int startPos = 0 + i;
		while (k < target.length()) {
			if (i >= str.length()) {
				return -1;
			}
			if (str.charAt(i) == target.charAt(k)) {
				k++;
			} else if (k != 0) {
				i--;
				k = 0;
			}
			if (k == 0) {
				startPos = i + 1;
			}
			i++;
		}
		return startPos;
	}

	private static void splitsInner(String temp, String splitter, DynArray<String> result) {
		String[] temp1 = split(temp, splitter);
		// if there is only one element in the array after being split
		if (temp1.length <= 1) {
			result.add(temp);
			return;
		}
		result.add(temp1[0]);
		splitsInner(temp1[1], splitter, result);
	}

	public static void main(String[] args) {
		String str = "a(x+b,a),c,d,(a,2,4,3,7)";
		System.out.println(contains(str, "a"));
	}
}
