package calculator.main.originators;

import java.util.Optional;

import calculator.CmdType;
import calculator.Command;
import calculator.SnapShot;
import calculator.SnapShotData;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.OprandFunc;

public class CalcOriginator extends Command {
	private CalcCmdInvoker invoker;
	// stores the calculation result last time
	protected static Object ans;

	public CalcOriginator(HashMapS<Object> varMap) {
		this.invoker = new CalcCmdInvoker(Optional.ofNullable(varMap).orElse(OprandFunc.initiateVarMap()));
	}

	@Override
	public SnapShot createSnapShot() {
		return new SnapShotData<Object>(ans, CmdType.calc, this);
	}

	// set funcOpManager
	public CalcOriginator setOthers(FuncOpManager manager) {
		CalcCmd.rpnObj.setOthers(manager);
		return this;
	}
	
	@Override
	public void setState(String key, Object data) {
		ans = data;
		//this is the command string that gives the data 'ans'
		currKey = key;
	}

	// these two methods are not implemented as they are not needed
	@Override
	public void doRestore() {
	}

	@Override
	public void doUndo() {
	}
	
	@Override
	public String createMeta() {
		return currKey;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object executeInner(String cmd) {
		Object[] results=invoker.calc(cmd);//return the key and value of the calculation result
		setState((String)results[0],results[1]);
		return results[1];
	}
}
