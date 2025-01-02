package calculator.main.originators;

import calculator.CmdType;
import calculator.Command;
import calculator.SnapShot;
import calculator.SnapShotData;
import calculator.opHandlers.FuncOp;

public class FuncOpOriginator extends Command {
	// stores the current funcOp object, for snapshot creation
	private FuncOp currFuncOp;
	protected FuncOpManager manager = new FuncOpManager();

	public FuncOpOriginator() {
	}

	@Override
	public SnapShot createSnapShot() {
		return new SnapShotData<FuncOp>(currFuncOp, CmdType.assign, this);
	}

	@Override
	public void setState(String key, Object data) {
		this.currFuncOp = (FuncOp) data;
		this.currKey = key;
	}

	// assume that the currFuncOpNode attribute has been updated before the method
	// is called
	@Override
	public void doRestore() {
		manager.put(currKey, currFuncOp);
	}

	@Override
	public void doUndo() {
		manager.removeOp(currKey);
	}

	@Override
	public String createMeta() {
		return currKey;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Boolean executeInner(String cmd) {
		Object[] result = manager.addOp(cmd);
		setState((String) result[0], result[1]);
		return true;
	}
}
