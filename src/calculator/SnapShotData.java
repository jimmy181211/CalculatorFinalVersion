package calculator;

public class SnapShotData<E> implements SnapShot {
	protected E data;
	private CmdType type;
	private Originator originator;

	public SnapShotData(E data, CmdType type, Originator originator) {
		this.data = data;
		this.type = type;
		this.originator = originator;
	}

	@Override
	public CmdType getType() {
		return type;
	}

	@Override
	public void restore(String key, CmdType type) {
		originator.setState(key, data);
		switch (type) {
		case restore:
			originator.doRestore();
			break;
		case undo:
			originator.doUndo();
			break;
		default:
			break;
		}
	}
}
