package calculator;

import calculator.funcTools.DynArray;
import calculator.funcTools.termInfo.Poly;
import calculator.funcTools.termInfo.Term;
import calculator.funcTools.termInfo.TermCore;
import calculator.funcTools.Entry;
import calculator.funcTools.Functions;
import calculator.funcTools.HashMapS;
import calculator.funcTools.HashMapVar;
import calculator.main.originators.CalcCmdInvoker;
import calculator.matrix.binary.Multiply;
import calculator.matrix.unary.Inverse;
import calculator.expr.Expr;
import calculator.expr.binary.Subtract;
import calculator.expr.ExprToString;

/*
 * @Description: this class only calculates the simultaneous equations of highest power one
 */
public class SimultenousEquations {
	private static HashMapS<Object> varMap;
	private static Double[][] matrix;
	private static Double[][] RHS;
	private static DynArray<String> vars;

	public SimultenousEquations(HashMapS<Object> givenVarMap) {
		varMap = givenVarMap;
	}

	private static void createMatrix(String cmd) {
		cmd = Functions.formatExpr(cmd);
		DynArray<String> equations = Functions.splits(cmd, "\r");
		String[] exprs;
		Expr[] exprParts = new Expr[2];
		// the expr mode is turned on -> simplify the equations so they are in the
		// simplest form
		CalcCmdInvoker calcObj = new CalcCmdInvoker(varMap);
		Expr[] cleanEquations = new Expr[equations.size()];

		// because it is an equation, it has an equal sign. We take the side of
		// constants out to make RHS
		for (int i = 0; i < equations.size(); i++) {
			exprs = Functions.split(equations.get(i), "=");
			for (int j = 0; j < exprs.length; j++) {
				exprParts[j] = (Expr) calcObj.calc(exprs[0])[1];
				// detect if the denominator is a constant
				if (exprParts[j].isConst(false)) {
					System.out.println(ErrorCodes.get("05") + ": fraction, can't be solved");
					System.exit(0);
				}
			}
			Expr temp = Subtract.subtract(exprParts[0], exprParts[1]);
			RHS[i][0] = -temp.removeConst(true);// remove the constant term in the numerator
			cleanEquations[i] = temp;
		}
		int size = cleanEquations.length;
		vars = new DynArray<>(size);
		matrix = new Double[size][size];// the result matrix can have maximum 10 terms of different variables
		int i = 0;

		for (int k = 0; k < cleanEquations.length; k++) {
			Poly equation = cleanEquations[k].getNum();
			/*
			 * every term of the equation contains a variable. If the number of variables of
			 * any of the equations are bigger than the number of equations inputed, then it
			 * is unable to find specific solution for this system equations
			 */
			if (equation.size() > size) {
				System.out.println(ErrorCodes.get("25") + ": there are more variables than expected");
				System.exit(0);
			}
			for (i = 0; i < vars.size(); i++) {
				// see if the equation has variable vars.get(i)
				Term term = new Term(equation.getE(vars.get(i), 1.0));
				// if it doesn't have the variable, set its value in the matrix to 0.0
				if (term.cont == null) {
					matrix[k][i] = 0.0;
				} else {
					matrix[k][i] = term.cont.val;
					equation.removeE(vars.get(i), 1.0);
				}
			}
			// if the equation contains new variables that vars:dynarr doesn't have, add them in.
			DynArray<Entry<HashMapVar<Double>, Double>> Eq = equation.traverse();
			for (int j = 0; j < Eq.size(); j++) {
				// converts the variables in a term into a string eg: 2,a,b,a ->2*a*b*a
				String var = ExprToString.entryToString(new TermCore(Eq.get(j).key)).toString();
				if (var.length() > 1) {
					System.out.println(ErrorCodes.get("25")
							+ ": exists variables multiplying each other, which is unable to be coped with");
					System.exit(0);
				}
				vars.add(var);
				// also add the coefficient
				matrix[k][i++] = Eq.get(j).val;
			}
			// add zeroes to the null positions of the matrix
			for (int t = i; t < matrix[k].length; t++) {
				matrix[k][t] = 0.0;
			}
		}
	}

	// key being the variable name and val being the solution
	// output format: eg-> a:4, b:5, c:1...
	public static HashMapS<Double> solve(String cmd) {
		HashMapS<Double> resultHashMap = new HashMapS<>();
		createMatrix(cmd);
		Double[][] results = Multiply.product(Inverse.inverse(matrix), RHS);
		// create the hashMap
		for (int i = 0; i < results.length; i++) {
			resultHashMap.put(vars.get(i), results[0][i]);
		}
		return resultHashMap;
	}
}
