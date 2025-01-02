package calculator.opHandlers.handleVar;

import calculator.AbstrHandler;

/*
 * @version 1.0
 * @Description: this super-class uses responsibility chain design pattern 
 */

public abstract class VarHandler<E> extends AbstrHandler<String>{
	// only some of the subclasses of VarHandler will need to initiate the variable
	public VarHandler<?> prevHandler = null;
	
	@Override
	public abstract E handle(String str);

	@SuppressWarnings("unchecked")
	public E submit(String str) {
		return (E)super.submit(str);
	}

	@SuppressWarnings("unchecked")
	public <K extends VarHandler<E>> K setNext(VarHandler<?> next) {
		this.nextHandler = next;
		next.prevHandler=this;
		return (K) this;
	}
}
