package calculator.varMap.unary.funcary.binary;

import calculator.CalcReturnMode;
import calculator.func.funcObjs.UDFunc;
import calculator.func.FuncOperation;
import calculator.vector.binary.Add;
import calculator.vector.binary.Divide;
import calculator.funcTools.DynArray;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperationAng;

//the oprand is FuncOpInfo
public class Integrate extends UnaryOperationAng implements CalcReturnMode<Object[]> {
	// hence the initialisation of this needs the container of funcOperators

	public Integrate() {
		super("∫d");
	}

	/*
	 * trapezium rule; only integrate for the numeric module TODO: but it is not
	 * efficient because each time when it goes through operate() method, it needs
	 * to re-translate the string into the samepostfixExpr and then calculate, which
	 * is a redundant move
	 */
	public Object integrate(FuncOperation glbFunc, FuncOpInfo params, Boolean isPolar) {
		/*
		 * consider that the attribute 'expr' of glbFunc may be modified, a copied is
		 * needed so that glbFunc itself is not affected by the change
		 */
		UDFunc func = (UDFunc)glbFunc.clone();
		func.update(params);
		if (isPolar) {
			func.modifyExpr("^2/2");
		} 
		// delta must be a power of 10
		double delta;
		Double[] pair = (Double[]) params.get();
		Double lowerVal = pair[0];
		Double upperVal = pair[1];

		if (Math.abs(upperVal - lowerVal) <= 10) {
			delta = 0.0001;
		} else if (Math.abs(upperVal - lowerVal) > 10 && Math.abs(upperVal - lowerVal) <= 100) {
			delta = 0.01;
		} else {
			delta = 0.1;
		}
		
		// lower boundary val:
		params.set(lowerVal);
		// this might modify the private attribute 'expr' in UDFunc class
		Object lower = func.operate(null);
		// upper boundary val
		params.set(upperVal);
		Object upper = func.operate(null);

		int length = (int) ((upperVal - lowerVal) / delta);
		Object[] vals = new Object[length - 1];
		for (int i = 1; i < length; i++) {
			params.set(lowerVal + delta * i);
			vals[i - 1] = func.operate(null);
		}
		
		// exclude lower and upper
		Object[] funcParam = { vals, lower, upper, delta, isPolar };
		return func.getIsMulti() ? multiCalc(funcParam) : singleCalc(funcParam);
	}

	// params[0] = sum; params[1]= lower; params[2]=upper; params[3]=delta
	@Override
	public Object singleCalc(Object[] params) {
		Object[] arr = (Object[]) params[0];

		Double sum = 0.0;
		for (Object e : arr) {
			sum += (Double) e;
		}
		Double result = (sum + ((Double) params[1] + (Double) params[2]) / 2) * (Double) params[3];

		// if is polar, the integral needs to be divided by 2
		if ((Boolean) params[4]) {
			return result / 2;
		} else {
			return result;
		}
	}

	@Override
	public Object multiCalc(Object[] params) {
		Object[] arr = (Object[]) params[0];
		Double[] sum = (Double[]) arr[0];

		for (int i = 1; i < arr.length; i++) {
			sum = Add.add(sum, (Double[]) arr[i]);
		}
		Double[] tail = Add.add((Double[]) params[1], (Double[]) params[2]);
		tail = Divide.divide(tail, 2.0);

		Double divider = (Double) params[3];
		// if is polar, the integral needs to be divided by 2
		if ((Boolean) params[4]) {
			divider *= 2;
		}
		return Divide.divide(Add.add(sum, tail), (Double) params[3]);
	}

	@SuppressWarnings("unchecked")
	@Override // numObj is an oprand of type: dynarr:{FuncOp,FuncOpInfo}
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		FuncOperation funcObj = (FuncOperation) dynarr.get(0);

		Boolean isPolar = ((UDFunc) funcObj).isPolar;
		FuncOpInfo param = (FuncOpInfo) dynarr.get(1);
		if (isPolar) {
			Double[] boundaries = (Double[]) param.get();
			for (int i = 0; i < boundaries.length; i++) {
				boundaries[i] = changeAngInMode(boundaries[i]);
			}
			param.set(boundaries);
		}
		return (E) integrate(funcObj, param, isPolar);
	}
}
