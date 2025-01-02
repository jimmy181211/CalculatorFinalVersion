package calculator.observeCls;

public interface Subscriber<E extends Enum<E>> {
	public void update(E data);
}
