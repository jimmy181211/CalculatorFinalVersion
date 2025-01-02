package calculator.opHandlers.handleVar.concreteHandlers;

import calculator.funcTools.DynArray;
import calculator.funcTools.Functions;
import calculator.opHandlers.handleVar.VarHandler;

public class MatrixHandler extends VarHandler<Double[][]>{
	@Override
	public Double[][] handle(String str){
		//if it is not a matrix
		if(!Functions.contains(str, '[')) {
			return null;
		}
		DynArray<String> dynarr=Functions.splitsProtected(str, ",",'[');
		if(dynarr.size()==1) {
			//remove the brackets
			str=str.substring(1,str.length()-1);
			dynarr=Functions.splitsProtected(str, ",",'[');
		}
		Double[][] result=new Double[dynarr.size()][];
		for(int i=0;i<dynarr.size();i++) {
			result[i]=(Double[]) prevHandler.handle(dynarr.get(i));
		}
		return result;
	}
}
