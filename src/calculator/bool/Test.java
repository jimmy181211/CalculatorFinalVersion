package calculator.bool;

public class Test {
	public static <E>E operate(Object x){
		return (E)x;
	}
	public static void main(String[] args) {
		Integer x=operate(null);
		System.out.println(x);
	}
}
