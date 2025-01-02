package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.operation.UnaryOperation;

public class ToBaseN extends UnaryOperation {
	public ToBaseN() {
		super("tobasen");
	}
	
	private static void formatVal(StringBuilder str,int temp) {
		//for any number >=10, it can only be represented by letters
		if(temp>=10) {
			str.append((char)(temp+55));
		}
		else {
			str.append(temp);
		}
	}
	
	public static String convert(double num,int radix,int precision) {
		//due to the limitation of the number of alphabets, it can only do radix convertion below 26
		if(radix>25 || precision>=15) {
			return null;
		}
		StringBuilder integer=new StringBuilder();
		StringBuilder dec=new StringBuilder();
		boolean isneg=false;
		
		//when num is negative
		if(num<0) {
			isneg=true;
			num=-num;
		}
		Integer intPart=(int)num;
		Double decPart=num%1.0;
		//deal with the integer part
		while(intPart>0) {
			formatVal(integer,intPart%radix);
			intPart/=radix;
		}
		//deal with the decimal part
		for(int i=0;i<=precision;i++) {
			Double temp=decPart*radix;
			formatVal(dec,temp.intValue());
			decPart=temp%1.0;
		}
		//use long type because if the precision is big, it will be out of the range of int, leading to overflow
		//use sf function because rounding up is needed
		String decStr=Long.toString((long)Functions.sf(dec.toString(), precision));
		//reverse the integer part
		if(isneg) {
			integer.append('-');
		}
		return integer.reverse().append('.').append(decStr).toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,3,"ToBaseN");
		return (E)convert((Double)dynarr.get(0),((Double)dynarr.get(1)).intValue(),((Double)dynarr.get(2)).intValue());
	}
}
