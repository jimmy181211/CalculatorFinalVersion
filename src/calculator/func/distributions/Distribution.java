package calculator.func.distributions;

import calculator.ErrorCodes;

public abstract class Distribution implements DistribCalc {
	protected DistribType distrib;
	protected InvDistribType invDistrib;
	protected String distribName;
	
	protected final static ErrorCodes errors = new ErrorCodes();
	public final static int prio = 6;
	
	protected Boolean isInv;
	protected boolean continuity = false;

	public Distribution(String name) {
		this.distribName = name;
	}
	
	public String getName() {
		return this.distribName;
	}
	
	public Distribution addObjs(DistribType distrib, InvDistribType invDistrib) {
		this.distrib = distrib;
		this.invDistrib = invDistrib;
		return this;
	}

	public Distribution check() throws Exception {
		if (this.distrib != null && this.invDistrib != null) {
			return this;
		}
		throw new Exception("distribution object can't be null");
	}

	public Distribution operateCheck() throws Exception {
		if (isInv == null || Type.isEqual == null) {
			throw new Exception("both isInv and isEqual have to be initialised");
		}
		return this;
	}

	public Distribution setIsInv(boolean isInv) {
		this.isInv = isInv;
		return this;
	}

	public Distribution setEqual(boolean isEqual) {
		// prevent asynchronized access to the sub-classes of type abstract class,
		// causing problems
		synchronized (Type.class) {
			Type.isEqual = isEqual;
		}
		return this;
	}

	// by default: P(X<x)
	@Override
	public Object cFunc(Double x) {
		return isInv ? invDistrib.cFunc(x) : distrib.cFunc(x);
	}

	@Override
	public Object pFunc(Double x) {
		return isInv ? invDistrib.pFunc(x) : distrib.pFunc(x);
	}

	public static void isPValid(Double pX) {
		if (pX < 0 || pX > 1) {
			System.out.println(errors.getErr("14") + " !p∈[0,1]");
			System.exit(0);
		}
	}

	protected abstract void isValid(Double[] params);

	public boolean isContinuity() {
		return continuity;
	}
	
	public DistribType getDistrib() {
		return this.distrib;
	}
}