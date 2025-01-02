package calculator.varMap.unary;

import calculator.funcTools.BiFunction;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class HCF extends UnaryOperation {
	private static Integer a=0,b=0;
	
	public HCF() {
		super("hcf");
	}
	
	private static void swap() {
		int temp=b;
		b=a;
		a=temp;
	}
	
	public static Double hcf(Double num1,Double num2) {
		a=num1.intValue();
		b=num2.intValue();
		int div=0;
		
		//make 'a' the bigger value
		if(b>a) {
			swap();
		}
		while(b!=a) {
			div=b;
			b=a-b;
			a=div;
			if(a<b) {
				swap();
			}
		}
		return b.doubleValue();
	}
	
	public static Double numberFunc(DynArray<Double> nums,boolean ishcf) {
		BiFunction<Double,Double,Double> func=funcOpt(ishcf);
		for(int i=1;i<nums.size();i++) {
			//use the hcf of the previous two numbers and the next number to calculate the new hcf
			nums.replace(func.apply(nums.get(i-1),nums.get(i)),i);
		}
		return nums.get(nums.size()-1);
	}
	
	public static Double hcf(DynArray<Double> nums) {
		return numberFunc(nums,true);
	}
	
	public static BiFunction<Double,Double,Double> funcOpt(boolean bool){
		return bool?(a,b)->hcf(a,b):(a,b)->LCM.lcm(a,b);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> temp=(DynArray<Object>)numObj;
		DynArray<Double> nums=new DynArray<>(4);
		for(int i=0;i<temp.size();i++) {
			nums.add((Double)temp.get(i));
		}
		if(nums.size()==2) {
			return (E)hcf(nums.get(0),nums.get(1));
		}
		else if(nums.size()>2) {
			return (E) hcf(nums);
		}
		else {
			System.out.println(errors.getErr("22")+" for HCF");
			System.exit(0);
			return null;
		}
	}
}
