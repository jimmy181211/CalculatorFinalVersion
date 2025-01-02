package calculator.numeric;

import calculator.numeric.unary.trigs.Sin;
import calculator.operation.Arithmetics;
public class Test {
	public static class A{
	}
	public static class B extends A{
	}
	public static void main(String[] args) {
		B b=(B)new A();
		System.out.println(b);
	}
}
