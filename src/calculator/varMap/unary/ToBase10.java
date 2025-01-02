package calculator.varMap.unary;

import calculator.ErrorCodes;
import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class ToBase10 extends UnaryOperation{
	public ToBase10() {
		super("tobase10");
	}
	
	public static Double convert(String num,int radix) {
		if(num==null || radix>25) {
			System.out.println(ErrorCodes.get("16")+" for base conversion");
			System.exit(0);
		}
		//consider the negative numbers
		boolean isneg=false;
		if(num.charAt(0)=='-') {
			isneg=true;
			//get rid of the '-' sign
			num=num.substring(1);
		}
		//get rid of the '.' sign
		String[] parts=Functions.split(num, ".");
		Double denary=0.0;
		double coeff=(int)Math.pow(radix, parts[0].length()-1);
		
		//combine the two elements to one string
		if(parts.length==1) {
			num=parts[0];
		}
		else if(parts.length==2) {
			num=parts[0]+parts[1];//create another reference in the heap
		}
		else {
			//throw an error as the input string either has more than one dot or is null
			System.out.println(ErrorCodes.get("18")+" for base conversion");
			System.exit(0);
		}
		
		for(int i=0;i<num.length();i++) {
			char ch=num.charAt(i);
			//if is uppercase
			if(65<=ch && ch<=90) {
				denary+=(ch-55)*coeff;
			}
			//if is number
			else if(Functions.isNum(ch)){
				denary+=(ch-48)*coeff;
			}
			else {
				//throw an error that the input number contains non-alphanumeric characters
				return null;
			}
			coeff/=radix;
		}
		return isneg?-denary:denary;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,2,"ToBase10");
		return (E)convert((String)dynarr.get(0),((Double)dynarr.get(1)).intValue());
	}

}
