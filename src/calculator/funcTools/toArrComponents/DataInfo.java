package calculator.funcTools.toArrComponents;

/*
 * @ClassName: DataInfo
 * @version v1.0
 * @Description: this class manages the variables created and used in ToArray class but modified in the Component classes
 */

public class DataInfo {
	private static Integer i = 0;
	private static Boolean isclearCache = false;

	public static Integer i() {
		return DataInfo.i;
	}

	public static Boolean isclearCache() {
		return DataInfo.isclearCache;
	}

	public synchronized static void setCacheStatus(Boolean newStatus) {
		DataInfo.isclearCache = newStatus;
	}

	public synchronized static void setI(Integer newVal) {
		DataInfo.i = newVal;
	}
}
