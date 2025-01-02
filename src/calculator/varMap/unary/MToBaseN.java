package calculator.varMap.unary;

import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class MToBaseN extends UnaryOperation{
	private DynArray<Object> dynarr;
	public MToBaseN() {
		super("basecvt");
	}
	
	public static String convert(String num, int base, int targetBase,int precision) {
		Double denary=ToBase10.convert(num,base);
		return ToBaseN.convert(denary,targetBase,precision);
	}
	
	private Integer intVal(int idx) {
		return ((Double)this.dynarr.get(idx)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		this.dynarr=(DynArray<Object>)numObj;
		checkLength(dynarr,4,"MToBaseN");
		return (E)convert((String)dynarr.get(0),intVal(1),intVal(2),intVal(3));
	}
	
}
