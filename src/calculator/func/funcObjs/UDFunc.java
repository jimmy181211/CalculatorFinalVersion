package calculator.func.funcObjs;

import calculator.CalcReturnMode;
import calculator.func.FuncOperation;
import calculator.funcTools.Clone;
import calculator.funcTools.DynArray;
import calculator.funcTools.Frame;
import calculator.main.RPNCalc;
import calculator.opHandlers.FuncOp;
import calculator.opHandlers.FuncOpInfo;
import calculator.operation.Arithmetics;

public final class UDFunc extends FuncOperation implements CalcReturnMode<FuncOpInfo> {
	private String expr;
	// all set by UDFBuilder
	private DynArray<Frame> postfixExpr;
	private RPNCalc rpnCalcObj=new RPNCalc(null);
	public boolean isPolar;

	public UDFunc(String[] vars, String expr, boolean isPolar) {
		super("udfunc", vars, 6, Type.UDF);
		this.expr=expr;
		postfixExpr = rpnCalcObj.checked().translate(expr);
		this.isPolar = isPolar;
	}
	
	public UDFunc() {

	}
	
	public String expr() {
		return this.expr;
	}

	public void modifyExpr(Arithmetics op, Double val) {
		expr = '(' + expr + ')';
		// if it is a unary operator:
		if (op.prio == 6) {
			expr = op.operator + expr;
		}
		// if it is a binary operator, with val!=null
		else if (val != null) {
			expr += op.operator + val.toString();
		}
		postfixExpr = rpnCalcObj.checked().translate(expr);
	}

	public void modifyExpr(String addedAtEnd) {
		expr = '(' + expr + ')';
		expr += addedAtEnd;
		postfixExpr = rpnCalcObj.checked().translate(expr);
	}

	@SuppressWarnings("unchecked")
	@Override // this is of type: FuncOpInfo
	public <E, T> E operate(T numObj) {
		return isMulti ? (E) multiCalc((FuncOpInfo) numObj) : (E) singleCalc((FuncOpInfo) numObj);
	}

	public void update(FuncOpInfo param) {
		rpnCalcObj.setVarMap(param.getVarMap(this.getClass()));
	}

	@Override
	public Object singleCalc(FuncOpInfo params) {
		if (params != null) {
			update(params);
		}
		return rpnCalcObj.calc(postfixExpr);
	}

	@Override
	public Object multiCalc(FuncOpInfo params) {
		// add elements into the dynamic array
		DynArray<Object[]> dynarr = new DynArray<>(this.vars.length);
		Object[] objArr;
		for (int i = 0; i < dynarr.size(); i++) {
			Object obj = params.get(this.vars[i]);
			// if it is not an array, then do the conversion
			if (!obj.getClass().isArray()) {
				objArr = new Object[] { obj };
			} else {
				objArr = (Object[]) obj;
			}
			dynarr.add(objArr);
		}
		if (params != null) {
			update(params);
		}
		return rpnCalcObj.checked().calcMulti(expr, vars, dynarr);
	}

	@Override
	public FuncOperation clone() {
		FuncOperation func = new FuncOp().build(Type.UDF, null, Clone.clone(vars), new Object[] { expr, isPolar });
		if (getIsMulti()) {
			func.setIsMulti(func.getIsMulti());
		}
		return func;
	}
}
