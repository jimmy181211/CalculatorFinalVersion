package calculator.varMap.unary;

import calculator.complex.ComplexNum;
import calculator.complex.binary.Divide;
import calculator.complex.unary.Ln;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;


public class Logarithm extends UnaryOperation {

	public Logarithm() {
		super("log");
	}

	//parameters: a,b, where: log(a,b), a is the base
	public static Double log(Double base,Double num) {
		return calculator.numeric.unary.Ln.ln(num)/calculator.numeric.unary.Ln.ln(base);
	}
	
	public static ComplexNum logComplex(ComplexNum base,ComplexNum num) {
		return Divide.divide(Ln.ln(num),Ln.ln(base));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,2,"logarithem");
		Object base=dynarr.get(0);
		Object num=dynarr.get(1);
		
		if(base.getClass()==ComplexNum.class || num.getClass()==ComplexNum.class) {
			if(base.getClass()==Double.class) {
				return (E)logComplex(new ComplexNum((Double)base,0.0),(ComplexNum)num);
			}
			if(num.getClass()==Double.class) {
				return (E)logComplex((ComplexNum)base,new ComplexNum((Double)num,0.0));
			}
			return (E)logComplex((ComplexNum)base,(ComplexNum)num);
		}
		Double baseD=(Double)base;
		if(baseD<=0.0 || baseD==1.0 || (Double)num<=0.0) {
			System.out.println(errors.getErr("00")+" for log");
			System.exit(0);
		}
		return (E)log(baseD,(Double)num);
	}
	
	public static void main(String[] args) {
		System.out.println(log(1.0,5.0));
	}
}
