package calculator.numeric.unary.trigs;

import calculator.numeric.unary.Factorial;
import calculator.operation.UnaryOperationAng;

public class Sin extends UnaryOperationAng{
	public Sin() {
		super("sin");
	}
	
	//angle in radian
	public static Double sin(double angle){
		//reduce the angle
		angle%=(Math.PI*2);
		//reduce the angle to make error smaller
		boolean isneg=false;
		if(angle<0) {
			angle=-angle;
			isneg=!isneg;
		}
		if(angle>Math.PI) {
			isneg=!isneg;
			angle-=Math.PI;
		}
		if(angle>Math.PI/2) {
			angle=Math.PI-angle;
		}
		double result=0;
		if(angle>Math.PI/4) {
			result=Cos.cos(Math.PI/2-angle);
		}
		else {
			int coeff;
			for(int i=0;i<10;i++) {
				coeff=2*i+1;
				result+=Math.pow(-1,i)*Math.pow(angle, coeff)/Factorial.factorial(coeff);
			}
		}
		return isneg?-result:result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sin(changeAngInMode((Double)numObj));
	}
}
