package calculator;

public interface SnapShot {
	public CmdType getType();

	public void restore(String key, CmdType type);
}
