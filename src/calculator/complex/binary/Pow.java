package calculator.complex.binary;

import calculator.complex.ComplexNum;
import calculator.complex.unary.Ln;
import calculator.numeric.unary.Factorial;
import calculator.operation.BinaryOperation;

public class Pow extends BinaryOperation {
	public Pow() {
		super("^",5);
	}
	
	public static ComplexNum pow(ComplexNum cbase,ComplexNum cexp) {
		Double r=cbase.r();
		Double ang=cbase.ang();
		Double rExp=cexp.re()*Math.log(r)-cexp.im()*ang;
		Double resultAng=cexp.re()*ang-cexp.im()*Math.log(r);
		return new ComplexNum(Math.pow(Math.E, rExp),resultAng,true);
	}
	
	public static ComplexNum powInt(ComplexNum cbase,Integer exp) {
		ComplexNum result=new ComplexNum();
		result.setTrig(Math.pow(cbase.r(),exp), cbase.ang()*exp);
		result.trigToNorm();
		return result;
	}
	
	//|exp|<1
	public static ComplexNum powDec(ComplexNum cbase, Double exp) {
		if(exp==0.0) {
			return new ComplexNum(1.0,0.0);
		}
		ComplexNum param=Multiply.multiply(Ln.ln(cbase),exp);
		ComplexNum sum=new ComplexNum(1.0,0.0);
		for(int k=1;k<7;k++) {
			sum.add(Multiply.multiply(powInt(param,k),1/Factorial.factorial(k)));
		}
		sum.normToTrig();
		return sum;
	}
	
	public static ComplexNum pow(ComplexNum cbase, Double exp) {
		Double decExp=exp%1.0;
		Integer intExp=exp.intValue();
		return ComplexNum.multiply(powInt(cbase,intExp),powDec(cbase,decExp));
	}
	
	@SuppressWarnings("unchecked")
	@Override // numObj1^numObj2
	public <E, T, C> E operate(T numObj1, C numObj2) {
		if(numObj2.getClass()==Double.class) {
			return (E)pow((ComplexNum)numObj1, (Double)numObj2);
		}
		return (E)pow((ComplexNum)numObj1,(ComplexNum)numObj2);
	}

	public static void main(String[] args) {
//		ComplexNum num=Pow.pow(new ComplexNum(2.0,4.3),-0.4);
////		System.out.println(Ln.ln(num2).re()+" "+Ln.ln(num2).im());
//		System.out.println(num.re()+"+"+num.im()+"i");
		
		ComplexNum ln=Ln.ln(new ComplexNum(-0.8,-1.72));
		for(int pow=1;pow<6;pow++) {
			System.out.println(Factorial.factorial(pow));
			ComplexNum result=Multiply.multiply(Pow.pow(ln, (double)pow),1/Factorial.factorial(pow));
			System.out.println(result.re()+" "+result.im());
			System.out.println();
		}
	}
}
