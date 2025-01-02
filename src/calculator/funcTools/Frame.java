package calculator.funcTools;

import calculator.SymbolType;
import calculator.operators.Operators;

/*
 * @Description: this class applies the idea of builder construction design pattern 
 * it is the 'message' that ToArray made. Its priority attribute is used by infixToPostfix() method; 
 * its other attributes are used by postfixToInfix class
 */
public class Frame implements SymbolType {
	// ST T shows the type of the symbol: eg. if it is unary operator, binary
	// operator or a number
	public ST sT;
	// priority -1 is for oprand. oprand by default has a priority of -1
	public int prio = -1;
	public String op;
	public Object metaData = null;

	public Frame(String op, ST type, int prio) {
		this.sT = type;
		this.op = op;
		this.prio = prio;
	}

	// the constructor enabling method chaining
	public Frame() {
	}

	public static Frame frame(String op, ST type) {
		// this is a unary operator
		if (type == ST.un) {
			return new Frame(op, type, 6);
		}
		// this is a binary operator
		else if (type == ST.bin) {
			return new Frame(op, type, Operators.getBinary(op));
		}
		// this is a bracket
		else if (type == ST.brkt) {
			return new Frame(op, type, Brackets.getPrio(op));
		}
		// this is a functional operator
		else if (type == ST.func) {
			return new Frame(op, type, 6);
		} else {
			return null;
		}
	}

	// chaining method
	public Frame prio(int prio) {
		this.prio = prio;
		return this;
	}

	public Frame op(String op) {
		this.op = op;
		return this;
	}

	public Frame type(ST type) {
		this.sT = type;
		return this;
	}

	public Frame metaData(Object metaData) {
		this.metaData = metaData;
		return this;
	}
}
