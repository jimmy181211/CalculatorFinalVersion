package calculator;

public abstract class AbstrHandler<K> {
	public AbstrHandler<K> nextHandler;

	public abstract Object handle(K input);

	public Object submit(K input) {
		Object result = handle(input);
		if (result == null && nextHandler != null) {
			return nextHandler.handle(input);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <E extends AbstrHandler<K>> E setNext(AbstrHandler<K> handler) {
		this.nextHandler = handler;
		return (E)this;
	}
}
