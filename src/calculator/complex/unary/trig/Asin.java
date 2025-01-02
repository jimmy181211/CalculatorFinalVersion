package calculator.complex.unary.trig;

import calculator.complex.ComplexNum;
import calculator.complex.Func;
import calculator.complex.binary.Add;
import calculator.complex.binary.Pow;
import calculator.complex.unary.Neg;
import calculator.operation.UnaryOperation;

public class Asin extends UnaryOperation {

	public Asin() {
		super("asin");
	}
	
	public static ComplexNum asin(ComplexNum cval) {
		ComplexNum term1=ComplexNum.multiply(cval, ComplexNum.i());
		ComplexNum term2=Pow.pow(Add.add(Neg.neg(Pow.pow(cval, 2.0)), 1.0), 0.5);
		return Func.invTrigfunc(Add.add(term1,term2));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)asin((ComplexNum)numObj);
	}
	
	public static void main(String[] args) {
		ComplexNum arr=asin(new ComplexNum(2.0,4.3));
		System.out.println(arr.re()+"+i"+arr.im());
	}
}
