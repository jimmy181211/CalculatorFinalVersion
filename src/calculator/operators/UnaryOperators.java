package calculator.operators;

public class UnaryOperators extends StaticOperators{
	private static String[] unOps={
			"sin","cos","log","~","ln","!","tan","sec","cot",
			"csc","sqrt","abs","norm","det","t","inv","¬","not",
			"sinh","cosh","tanh","asin","acos","atan","arsinh","arcosh","artanh",
			"isPrime"
	};
	
	public UnaryOperators() {
		super(unOps,createPrios(unOps.length,6));
	}
}
