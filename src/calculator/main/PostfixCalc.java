package calculator.main;

import java.util.Optional;
import calculator.SymbolType.ST;
import calculator.expr.Expr;
import calculator.funcTools.ArrStack;
import calculator.funcTools.DynArray;
import calculator.funcTools.Entry;
import calculator.funcTools.Frame;
import calculator.funcTools.HashMapS;
import calculator.opHandlers.TempParam;
import calculator.operation.OperationH;

public class PostfixCalc {
	private HashMapS<Object> varMap;

	public PostfixCalc(HashMapS<Object> hashmap) {
		this.varMap = Optional.ofNullable(hashmap).orElseThrow();
	}

	// for multiple variables and multiple values
	public Object postfixCalc(DynArray<Frame> postfExpr) {// receiving infix expression
		ArrStack<Object> stk = new ArrStack<>(100);
		Frame fr;
		for (int i = 0; i < postfExpr.size(); i++) {
			fr = postfExpr.get(i);
			// find the value of a variable/number using a map
			if (fr.sT == ST.num) {
				stk.push(Double.parseDouble(fr.op));
			} else if (fr.sT == ST.var) {
				stk.push(varMap.get(fr.op));
			} else if(fr.sT == ST.symbol) {
				stk.push(new Expr(fr.op));
			}
			// for binary operator
			else if (fr.sT == ST.bin) {
				Object num2 = stk.pop();
				Object num1 = stk.pop();
				stk.push(OperationH.operate(fr.op, num1, num2));
			}
			// for unary operator
			else if (fr.sT == ST.un) {
				stk.push(OperationH.operate(fr.op, stk.pop(), true));
			}
			// for functional operator
			else if (fr.sT == ST.func) {
				stk.push(OperationH.operate(fr.op, stk.pop()));
			}
		}
		//might not be necessary, we will see
		cleanVarMap();
		return stk.pop();
	}

	/*
	 * remove temporary variables in the map, because this map is static and all RPN
	 * objects will share this container and make changes on it (recursion logic is
	 * applied for RPN, so it's likely that multiple RPN objects exist
	 * simultaneously)
	 */
	private void cleanVarMap() {
		Entry<String, Object> curr;
		for (int i = 0; i < varMap.table.length; i++) {
			curr = this.varMap.table[i];
			while (curr != null) {
				// if this is a temporary variable and it is visited
				if (curr.val.getClass().getSuperclass() == TempParam.class && ((TempParam) curr.val).isVisited()) {
					this.varMap.remove(curr.key);
				}
			}
		}
	}
}
