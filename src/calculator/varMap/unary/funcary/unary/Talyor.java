package calculator.varMap.unary.funcary.unary;

import calculator.ErrorCodes;
import calculator.func.FuncOperation;
import calculator.funcTools.DynArray;
import calculator.funcTools.TriFunction;
import calculator.numeric.unary.Factorial;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.UnaryOperation;
import calculator.varMap.unary.funcary.binary.Differentiate;

/*
 * @Description: this class takes UDFunc as input, turning the expression in the object into a taylor seire of type String
 */
public class Talyor extends UnaryOperation {

	public Talyor() {
		super("taylor");
	}

	private static TriFunction<Double, Double, Integer, String> termStr(Double n) {
		String formatStr;
		if (n == 0.0) {
			formatStr = "%f * x ^ %d + ";
		} else {
			formatStr = "%f * (x - %f) ^ %d + ";
		}
		return (coeff, num, i) -> String.format(formatStr, coeff, num, i);
	}

	// f(x)=f(a)+f'(a)(x-a)/1!+f"(a)(x-a)^2/2!+.... 'a' is num here
	public static String taylor(FuncOperation func, double num, int terms) {
		Double coeff;
		FuncOpInfo param = new FuncOpInfo();
		param.set(num);
		StringBuilder result = new StringBuilder();
		Differentiate diffObj = new Differentiate();
		TriFunction<Double, Double, Integer, String> termStr = termStr(num);

		for (int i = 0; i < terms; i++) {
			coeff = (Double) diffObj.differentiate(func, param, i) / Factorial.factorial(i);
			result.append(termStr.apply(coeff, num, i));
		}
		// get rid of the unnecessary '+' sign at the end
		return result.toString().substring(0, result.length() - 2);
	}

	@SuppressWarnings("unchecked")
	@Override // numObj: DynArray<>[funcOperation, constant 'a']
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr = (DynArray<Object>) numObj;
		if (dynarr.size() != 3) {
			System.out.println(ErrorCodes.get("22") + " for taylor expansion - three parameters are needed");
		}
		return (E) taylor((FuncOperation) dynarr.get(0), (Double) dynarr.get(1), (Integer) dynarr.get(2));
	}

}
