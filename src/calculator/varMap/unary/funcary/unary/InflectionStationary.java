package calculator.varMap.unary.funcary.unary;

import calculator.func.funcObjs.UDFunc;
import calculator.funcTools.DynArray;
import calculator.operation.UnaryOperation;

public class InflectionStationary extends UnaryOperation{

	public InflectionStationary() {
		super("inflectedStationary");
	}
	public static DynArray<DataPack<StyType>> findInflect(UDFunc func,Double[] range){
		return FindExtremum.findStationaries(func,range,false);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		DynArray<Object> dynarr=(DynArray<Object>)numObj;
		return (E)findInflect((UDFunc)dynarr.get(0),(Double[])dynarr.get(1));
	}
}
