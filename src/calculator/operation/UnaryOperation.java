package calculator.operation;

public abstract class UnaryOperation extends Arithmetics{
	public UnaryOperation(String operator) {
		super(operator,6);
	}
	/* although extending a super-class that implements an interface
	 * one of whose method is not needed by the subclass, breaks the interface segregation principle,
	 * it is the only way where polymorphism can be utilised. Also the negative effect will be minimised
	 * by overriding the unneeded method as a "uncallable function"
	 */
	public <E, T, C> E operate(T numObj1, C numObj2){
		new Exception("the method is not callable").printStackTrace();
		return null;
	}
}
