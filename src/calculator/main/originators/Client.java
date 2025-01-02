package calculator.main.originators;

import calculator.CmdType;
import calculator.funcTools.toArrComponents.AddVars;
import calculator.CommandStack;
import calculator.ErrorCodes;
import calculator.SolveEquations;
import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.Functions;
import calculator.observeCls.AngType;
import calculator.observeCls.AngTypePublisher;
import calculator.observeCls.CalcModeType;
import calculator.observeCls.ExprModePublisher;

public class Client {
	private String[] declareID = { "declare", "init", "initialise", "new" };
	private String[] delID = { "undo", "delete", "remove", "pop", "del" };
	private String[] restoreID = { "restore", "recover", "rec" };
	private boolean isOrig;
	// this is only for the solve equation function
	private Double[] range;

	// all the originators
	FuncOpOriginator funcOr;
	VarOriginator varOr;
	CalcOriginator calcOr;
	CommandStack cmdStk;
	ExprModePublisher calcModePub;
	AngTypePublisher angTypePub;

	public Client() {
		funcOr = new FuncOpOriginator();
		varOr = new VarOriginator();
		calcOr = new CalcOriginator(varOr.manager.opMap).setOthers(funcOr.manager);
		cmdStk = new CommandStack();
		calcModePub = new ExprModePublisher();
		calcModePub.subscribe(new AddVars());
		/*
		 * add all the instance of classes that implement Subscriber<AngType>. All of
		 * which extends UnaryOperationAng. This appending has been done inside AngTypePUblisher class
		 */
		angTypePub = new AngTypePublisher();
	}

	private static Double[] createRange(String str) {
		String[] vals = Functions.split(Functions.replace(str.substring(1), ")", ""), ",");

		if (Functions.isNum(vals[0]) && Functions.isNum(vals[1])) {
			return new Double[] { Double.valueOf(vals[0]), Double.valueOf(vals[1]) };
		} else {
			System.out.println(ErrorCodes.get("05")
					+ String.format(": the oprands should be numbers but it is:%s, %s", vals[0], vals[1]));
			System.exit(0);
			return null;
		}
	}

	// the return value indicates whether the calculation is successful
	public boolean operate(String cmd) {
		CmdType type = null;
		// cmdPair eg: ["declare","f(x)=3*x+2"]
		String[] cmdPair = Functions.split(cmd, " ");

		// the command in it also has '=' sign, same as solveEq, but this type of
		// command has an identifier, eg 'declare
		if (Functions.search(cmdPair[0], declareID) != -1) {
			cmd = cmdPair[1];
			type = CmdType.assign;
		}
		// declaration of variables will include '='. It's not declaration but also
		// contains '=', then it's equation solving
		// TODO: but it hasn't been checked that the brackets have valid format
		else if (cmd.contains("=")) {
			boolean smallBrkt = Functions.contains(cmd, '(');
			boolean midBrkt = Functions.contains(cmd, '[');
			String[] comps = null;
			if (smallBrkt ^ midBrkt) {
				// consider that the input might be, eg: 4x+5=8x^2; (a,b) , or 4x+5=8x^2, (a,b).
				// Two valid format have to be included
				comps = Functions.split(cmd, ";");
				if (comps.length == 1) {
					comps = Functions.split(cmd, ",");
					if (comps.length == 1) {
						System.out.println(ErrorCodes.get("16") + ": the format of the equation is unindentifiable");
						System.exit(0);
					}
				}
				range = createRange(comps[1]);
				cmd = comps[0];
				type = CmdType.solveEq;
			} else {
				System.out.println(
						ErrorCodes.get("16") + ": the range of the root should be entered but there is no such information");
				System.exit(0);
			}
		}
		// if is to modify the data structure
		else if (Functions.search(cmdPair[0], delID) != -1) {
			cmd = cmdPair[1];
			type = CmdType.undo;
		}
		// if is to restore the values
		else if (Functions.search(cmdPair[0], restoreID) != -1) {
			cmd = cmdPair[1];
			type = CmdType.restore;
		}
		// mode change
		else if (Functions.contains(cmd, "expr") || Functions.contains(cmd, "expression")) {
			type = CmdType.changeCalcMode;
			isOrig = false;
		} else if (Functions.contains(cmd, "calc") || Functions.contains(cmd, "calculation")) {
			type = CmdType.changeCalcMode;
			isOrig = true;
		} else if (Functions.contains(cmd, "radian") || Functions.contains(cmd, "rad")) {
			type = CmdType.changeAngMode;
			isOrig = true;
		} else if (Functions.contains(cmd, "degree") || Functions.contains(cmd, "deg")) {
			type = CmdType.changeAngMode;
			isOrig = false;
		}
		// else it will be calculation/ evaluation of the string
		else {
			type = CmdType.calc;
		}
		Object result = operateInner(cmd, type);
		if (result != null) {
			System.out.println(OutputStrFormat.output(result));
		}
		return true;
	}

	public Object operateInner(String cleanCmd, CmdType type) {
		switch (type) {
		case assign:
			return assign(cleanCmd);
		case calc:
			return calcOr.execute(cleanCmd);
		//by default so far, the unknown to find is called 'x'
		case solveEq:
			return SolveEquations.newtonMethod(new UDFunc(null, cleanCmd, false), range, Functions.diff(0),
					Functions.diff(1));
		case undo:
			cmdStk.undo(cleanCmd);
			return true;
		case restore:
			cmdStk.restore(cleanCmd);
			return true;
		case changeAngMode:
			angTypePub.notifySubscriber(isOrig ? AngType.rad : AngType.degree);
			return null;
		case changeCalcMode:
			calcModePub.notifySubscriber(isOrig ? CalcModeType.calc : CalcModeType.expr);
			return null;
		}
		return null;
	}

	// it needs to be determined whether the string command assigns an operator or
	// oprand
	private Object assign(String cmd) {
		// if it is a function assignment
		if (cmd.contains("(") && cmd.contains(")")) {
			return funcOr.execute(cmd);
		} else {
			return varOr.execute(cmd);
		}
	}

	public static void main(String[] args) {
		Client client=new Client();
		System.out.println(client.operate("2+4*2"));
	}
}
