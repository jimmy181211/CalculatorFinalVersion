package calculator.funcTools;

public interface Clone<E> {
	public static Double[] clone(Double[] arr) {
		Double[] newArr=new Double[arr.length];
		for(int i=0;i<arr.length;i++) {
			newArr[i]=arr[i];
		}
		return newArr;
	}
	
	public static Double[][] clone(Double[][] arr) {
		Double[][] newArr=new Double[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++) {
			newArr[i]=clone(arr[i]);
		}
		return newArr;
	}
	
	public static String[] clone(String[] arr) {
		String[] newArr=new String[arr.length];
		for(int i=0;i<arr.length;i++) {
			newArr[i]=arr[i];
		}
		return newArr;
	}
	
}
