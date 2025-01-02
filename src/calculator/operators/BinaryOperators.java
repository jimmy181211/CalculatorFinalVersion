
package calculator.operators;

public class BinaryOperators extends StaticOperators{
	private static String[] binOps={"+","-","/","*","^","%","A","C","×","&&","and","||","or","⊕","xor"};
	private static Integer[] prios=new Integer[] {3,3,4,4,5,4,4,4,4,4,4,3,3,5,5};
	
	public BinaryOperators() {
		super(binOps,prios);
	}
	
	public static void main(String[] args) {
		BinaryOperators binop=new BinaryOperators();
		binop.ops.traverse(values->{
			System.out.print(values.key+" ");
		});

	}
}
