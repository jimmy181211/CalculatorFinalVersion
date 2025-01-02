package calculator.varMap.unary.funcary.binary;

import calculator.CalcReturnMode;
import calculator.func.funcObjs.UDFunc;
import calculator.func.FuncOperation;
import calculator.funcTools.DynArray;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperationAng;
import calculator.vector.binary.Divide;
import calculator.vector.binary.Subtract;

public final class Differentiate extends UnaryOperationAng implements CalcReturnMode<Object[]> {
	// this method is purely for name registration for Operator class
	public Differentiate() {
		super("d/d");
	}

	public Object differentiate(FuncOperation func, FuncOpInfo param, int order) {
		if (order == 0) {
			return func.operate(param);
		}
		Double delta = Math.pow(10, -4);
		Double val = (Double) param.get();
		// for multi mode, it is of type Double[][], else, it's Double[]
		Object[] yVals = new Object[order + 1];
		
		((UDFunc)func).update(param);
		// calculate many Ys (y+i*deltaX)
		for (int i = 1; i <= order; i++) {
			// the result can be a Double value or a Double[] array
			yVals[i - 1] = func.operate(null);
			param.set(val + delta * i);
		}
		// differentiate in respect to the variable 'var'
		// deal with the y values and convert them into derivatives
		Object[] funcParams = { yVals, order, delta };
		return func.getIsMulti() ? multiCalc(funcParams) : singleCalc(funcParams);
	}

	public Object polarDifferentiate(FuncOperation func, FuncOpInfo param, int order) {
		// polar differentiation
		FuncOperation xFunc = func.clone();
		FuncOperation yFunc = func.clone();
		// modify the expr so that: dr/dθ -> dy/dx. rsinθ=y, rcosθ=x. so: drsinθ/dθ ÷ drcosθ/dθ
		((UDFunc) xFunc).modifyExpr("*cos" + param.getVar());
		((UDFunc) yFunc).modifyExpr("*sin" + param.getVar());

		Object denominator = differentiate(xFunc, param, order);
		Object numerator = differentiate(yFunc, param, order);
		if (func.getIsMulti()) {
			Object[] d = (Object[]) denominator;
			Object[] n = (Object[]) numerator;
			Double[] result = new Double[d.length];
			for (int i = 0; i < d.length; i++) {
				result[i] = (Double) n[i] / (Double) d[i];
			}
			return result;
		} else {
			return (Double) numerator / (Double) denominator;
		}
	}

	@Override
	public Object singleCalc(Object[] funcParams) {
		Object[] yVals = (Object[]) funcParams[0];
		Integer order = (Integer) funcParams[1];
		Double delta = (Double) funcParams[2];

		for (int i = 1; i <= order; i++) {
			for (int j = 0; j < yVals.length - i; j++) {
				yVals[j] = ((Double) yVals[j + 1] - (Double) yVals[j]) / delta;
			}
		}
		return yVals[0];
	}

	@Override
	public Object multiCalc(Object[] funcParams) {
		Object[] yVals = (Object[]) funcParams[0];
		Integer order = (Integer) funcParams[1];
		Double delta = (Double) funcParams[2];

		for (int i = 1; i <= order; i++) {
			for (int j = 0; j < yVals.length - i; j++) {
				// vector operation
				yVals[j] = Subtract.subtract((Double[]) yVals[j + 1], (Double[]) yVals[j]);
				yVals[j] = Divide.divide((Double[]) yVals[j], delta);
			}
		}
		return yVals[0];
	}

	// overloaded method: with specified variables: the one used in operate()
	// function
	public Object differentiate(FuncOperation func, FuncOpInfo param) {
		return differentiate(func, param, 1);
	}

	@SuppressWarnings("unchecked")
	@Override // dynarr:FuncOp, FuncOpInfo
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		FuncOperation func = (FuncOperation) dynarr.get(0);
		FuncOpInfo param = (FuncOpInfo) dynarr.get(1);
		// is polar
		if (((UDFunc)func).isPolar) {
			// set angleMode
			param.set(changeAngInMode((Double) param.get()));
			return (E) polarDifferentiate(func, param, 1);
		}
		// if is normal
		else {
			return (E) differentiate(func, param);
		}
	}
}
