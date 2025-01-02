package calculator.complex;

import calculator.complex.binary.Add;
import calculator.complex.binary.Divide;
import calculator.complex.binary.Subtract;
import calculator.complex.unary.Ln;
import calculator.funcTools.BiFunction;

public class Func {
	
	public static BiFunction<ComplexNum,ComplexNum,ComplexNum> operType(boolean isadd){
		return isadd?(c1,c2)->Add.add(c1, c2):(c1,c2)->Subtract.subtract(c1, c2);
	}
	
	public static ComplexNum invTrigfunc(ComplexNum a) {
		return ComplexNum.multiply(Ln.ln(a), new ComplexNum(0.0,-1.0));
	}
	
	public static ComplexNum trigPart(ComplexNum cangle,boolean opt) {
		BiFunction<ComplexNum,ComplexNum,ComplexNum> operFunc=operType(opt);
		ComplexNum c1=new ComplexNum(Math.pow(Math.E,-cangle.im()),cangle.re(),true);
		ComplexNum c2=new ComplexNum(Math.pow(Math.E, cangle.im()),-cangle.re(),true);
		return operFunc.apply(c1,c2);
	}
	
	public static ComplexNum trig(ComplexNum cangle,boolean opt) {
		return Divide.divide(trigPart(cangle,opt),new ComplexNum(0.0,2.0));
	}
	
	public static ComplexNum hyperPart(ComplexNum cangle,boolean opt) {
		BiFunction<ComplexNum,ComplexNum,ComplexNum> operFunc=operType(opt);
		ComplexNum c1=new ComplexNum(Math.pow(Math.E,cangle.re()),cangle.im(),true);
		ComplexNum c2=new ComplexNum(Math.pow(-Math.E, cangle.re()),-cangle.im(),true);
		return operFunc.apply(c1, c2);
	}
}
