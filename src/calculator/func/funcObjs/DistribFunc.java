package calculator.func.funcObjs;

import calculator.func.FuncOperation;
import calculator.func.distributions.Distribution;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.FuncOpInfo;
import calculator.varMap.unary.funcary.unary.Expectation;
import calculator.varMap.unary.funcary.unary.Variant;

public final class DistribFunc extends FuncOperation {
	private Distribution distribObj;

	// vars is eg: P(X=x), X is var
	public DistribFunc(String[] vars, Distribution distribObj) {
		// the last argument is an Info Object
		super(distribObj.getName(), vars, Distribution.prio, Type.Distrib);
		this.distribObj = distribObj;
	}

	public DistribFunc() {
	}

	@SuppressWarnings("unchecked")
	@Override // oprand type: funcOpInfo or : Class (for distribution property)
	public <E, T> E operate(T numObj) {
		// if the operation is to find the property of the distribution:
		// expectation
		if ((Class<?>) numObj == Expectation.class) {
			return (E) distribObj.getDistrib().getExp();
		}
		// variant
		else if ((Class<?>) numObj == Variant.class) {
			return (E) distribObj.getDistrib().getVariant();
		}
		FuncOpInfo param = (FuncOpInfo) numObj;
		boolean isReverse = false, isEqual = false, isPd = false;
		Object answer = null;

		switch (param.comparator) {
		case ">":
			isReverse = true;
			isEqual = true;
			break;
		case "<":
			break;
		case "=":
			isEqual = true;
			isPd = true;
			break;
		case "<=":
			isEqual = true;
			break;
		case ">=":
			isReverse = true;
			break;
		default:
			System.out.println(errors.getErr("13") + " for comparator in distribution");
			System.exit(0);
		}

		// set isInv and isEqual attribute
		try {
			distribObj = distribObj.setIsInv(param.isInv()).setEqual(isEqual).operateCheck();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isPd) {
			answer = distribObj.pFunc((Double) param.get(this.vars[0]));
		} else {
			answer = distribObj.cFunc((Double) param.get(this.vars[0]));
		}
		// if it is calculating probability then:
		if (isReverse && !param.isInv()) {
			answer = (Object) (1.0 - (Double) answer);
		}
		// for discrete inv-distributions
		else if (isReverse && param.isInv() && !distribObj.isContinuity()) {
			Double[] darr = (Double[]) answer;
			darr[0]++;
			darr[1]--;
			answer = (Object) darr;
		}
		return (E) answer;
	}

	@Override
	public FuncOperation clone() {
		FuncOperation funcOp = new FuncOp().build(type, distribObj.getName(), vars, vars);
		if (getIsMulti()) {
			funcOp.setIsMulti(funcOp.getIsMulti());
		}
		return funcOp;
	}
}
