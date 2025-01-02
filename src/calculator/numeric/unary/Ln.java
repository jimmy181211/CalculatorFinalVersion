package calculator.numeric.unary;

import calculator.ErrorCodes;
import calculator.operation.UnaryOperation;

public class Ln extends UnaryOperation {

	public Ln() {
		super("ln");
		// TODO Auto-generated constructor stub
	}
	
	//base e, accurate to 6 dp usually
	public static Double ln(double num){
		if(num<=0) {
			System.out.println(ErrorCodes.get("00")+" for logarithm");
			System.exit(0);
		}
		int exp;//the exponent
		for(exp=0;num>=2;exp++) {
			num/=2;
		}
		//if num is like 1.4, 1.5, it can be further reduced to improve the precision as:
		// 1.4/2 =0.7, taylorX=1-0.7=-0.3, abs(-0.3)<0.4
		// however, for 1.2: 1.2/2=0.6, 0.6-1=-0.4, abs(-0.4)>0.2
		// the boundary for x where num has to be further divided by two is the root x1 of the equation:
		// (1+x1)/2=1-x1, and all 1<x<x1 doesn't need to be divided by two, showcased above
		if(num>4/3) {
			num/=2;
			exp+=1;
		}
		double result=(double)exp*Math.log(2);
		for(int i=1;i<20;i++) {
			result+=Math.pow(-1,i-1)*Math.pow(num-1, i)/i;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)ln((Double)numObj);
	}

}
