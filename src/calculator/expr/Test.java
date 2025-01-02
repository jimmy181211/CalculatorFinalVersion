package calculator.expr;

public class Test {
	public static void main(String[] args) {
		Var var=new Var("2*x");
		Object obj=var;
		System.out.println(obj.toString());
	}
}
