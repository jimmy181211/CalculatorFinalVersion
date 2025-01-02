package calculator.main.originators;

import calculator.CmdType;
import calculator.Command;
import calculator.SnapShot;
import calculator.SnapShotData;
import calculator.funcTools.HashMapS;

/*
 * @Description: this is the controller design pattern: 
 * it interacts with CommandStack and the external environment, and VarManager focuses on 
 * the base implementation of methods, reducing disordered dependencies between objects
 */
public class VarOriginator extends Command {
	protected VarManager manager=new VarManager();
	protected Object currVal;

	public VarOriginator() {
	}

	public Object getVar(String name) {
		return manager.opMap.get(name);
	}

	// it will only return the copy of the varmap to the RPN object
	public HashMapS<Object> getVarMap() {
		return manager.opMap;
	}

	@Override
	public SnapShot createSnapShot() {
		return new SnapShotData<Object>(currVal, CmdType.assign, this);
	}

	@Override
	public void doRestore() {
		manager.put(currKey, currVal);
	}

	@Override
	public void doUndo() {
		manager.remove(currKey);
	}

	@Override
	public String createMeta() {
		return this.currKey;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Boolean executeInner(String cmd) {
		Object[] result = manager.addVar(cmd);
		setState((String) result[0], result[1]);
		return true;
	}

	@Override
	public void setState(String key, Object val) {
		this.currKey = key;
		this.currVal = val;
	}
}
