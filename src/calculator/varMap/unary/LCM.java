package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class LCM extends UnaryOperation {
	public LCM() {
		super("lcm");
	}
	
	public static Double lcm(Double a,Double b) {
		return a*b/HCF.hcf(a,b);
	}
	
	// calculate the lcm of multiple whole numbers
	public static Double lcm(DynArray<Double> nums) {
		return HCF.numberFunc(nums,false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> temp=(DynArray<Object>)numObj;
		//create a dynamic array of type Double
		DynArray<Double> nums=new DynArray<>(4);
		for(int i=0;i<temp.size();i++) {
			nums.add((Double)temp.get(i));
		}
		if(nums.size()==2) {
			return (E)lcm(nums.get(0),nums.get(1));
		}
		else if(nums.size()>2) {
			return (E) lcm(nums);
		}
		//if the number of elements in the array is incorrectly smaller than 2, it becomes invalid!
		else {
			System.out.println(errors.getErr("22")+" for HCF");
			System.exit(0);
			return null;
		}
	}

}
