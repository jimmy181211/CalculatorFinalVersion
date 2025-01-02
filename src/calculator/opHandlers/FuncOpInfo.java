package calculator.opHandlers;

import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.HashMapS;

public final class FuncOpInfo extends TempParam {
	// =; <; >; <=; >=
	public String comparator = "=";
	private HashMapS<Object> varMap = OprandFunc.initiateVarMap();
	private String var = "x";
	private boolean isInv = false;

	public FuncOpInfo(HashMapS<Object> givenVarMap, String comparator, String var, Boolean isInv) {
		if (givenVarMap != null) {
			this.varMap = givenVarMap;
		}
		this.comparator = comparator;
		this.isInv = isInv;
		this.var = var;
	}

	public FuncOpInfo(String var, Object val) {
		this.varMap.put(var, val);
	}

	public FuncOpInfo() {
	}

	/*
	 * these facilitates initialisation, solve the issue that there are too many
	 * parameters in the constructor that the programmer might not know which is
	 * which when creating an instance
	 */
	public FuncOpInfo varMap(HashMapS<Object> givenVarMap) {
		if (givenVarMap != null) {
			this.varMap = varMap.clone();
		}
		return this;
	}

	public void setVar(String newVar, Class<?> cls) {
		if (cls == ParamFuncHandler.class) {
			this.var = newVar;
		} else {
			new Exception("setVar() method can't be called by classes unless it's ParamFuncHandler!").printStackTrace();
		}
	}

	public FuncOpInfo comparator(String comparator) {
		this.comparator = comparator;
		return this;
	}

	public FuncOpInfo isInv(Boolean isInv) {
		this.isInv = isInv;
		return this;
	}

	public boolean isInv() {
		return this.isInv;
	}

	// set restriction that only UDFunc class can access this method
	public HashMapS<Object> getVarMap(Class<?> cls) {
		if (cls == UDFunc.class) {
			this.isVisited = true;
			return this.varMap;
		} else {
			new Exception("getVarMap method can't be called unless it's UDFunc class").printStackTrace();
			return null;
		}
	}

	// usually to retrieve an element from vars:String[] attribute in funcOp class
	public Object get(String key) {
		return this.varMap.get(key);
	}
	
	/*
	 *  the methods below are for calculus
	 */
	public String getVar() {
		return this.var;
	}
	
	//the two methods below are in pairs
	public Object get() {
		this.isVisited = true;
		return this.varMap.get(var);
	}
	
	public void set(Object val) {
		this.varMap.put(var, val);
	}
}
