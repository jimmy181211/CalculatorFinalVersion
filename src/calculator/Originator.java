package calculator;

/*
 * @Description: this Originator links with SnapShot, MetaSS, and cmdStk, which is the caretaker
 * it adds elements into the caretaker. E represent the data type of the value
 */
public interface Originator {
	static CommandStack cmdStk = new CommandStack();

	public default void save() {
		cmdStk.add(createMeta(), createSnapShot());
	}

	// SnapShotData class will aggregate all the codes below to form a restore()
	// method
	abstract SnapShot createSnapShot();

	abstract String createMeta();

	public void setState(String key, Object data);

	public void doRestore();

	public void doUndo();
}
