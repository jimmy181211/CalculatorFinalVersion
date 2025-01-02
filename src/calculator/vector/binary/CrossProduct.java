package calculator.vector.binary;

import calculator.operation.BinaryOperation;

public class CrossProduct extends BinaryOperation {

	public CrossProduct() {
		super("×",4);
		// TODO Auto-generated constructor stub
	}
	
	//three dimension vector only
	public static Double[] crossProduct(Double[] vec, Double[] vec2) {
		//assume that the lengths have been checked
		if(vec.length!=3) {
			System.out.println(errors.getErr("16")+" of a vector in crossProduct operation");
			System.exit(0);
		}
		Double[] normal=new Double[3];
		normal[0]=vec[1]*vec2[2]-vec[2]*vec2[1];
		normal[1]=vec[2]*vec2[0]-vec[0]*vec2[2];
		normal[2]=vec[0]*vec2[1]-vec2[0]*vec[1];
		return normal;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double[] vec1=(Double[])numObj1;
		Double[] vec2=(Double[])numObj2;
		checkLengths(vec1,vec2);
		return (E)crossProduct(vec1,vec2);
	}

}
