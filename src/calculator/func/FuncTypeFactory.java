package calculator.func;

import java.util.List;

import calculator.func.FuncType.Type;
import calculator.func.funcTypes.InfoBuilder;
import calculator.funcTools.MyMap;
import calculator.opHandlers.AssembleOpObjs;

public final class FuncTypeFactory {
	private static MyMap<Type,InfoBuilder> builders;

	public static void init() {
		if (builders == null) {
			synchronized (FuncTypeFactory.class) {
				if (builders == null) {
					// form a hashMap containing all the funcObjs in funcObjs package
					List<InfoBuilder> builderList= AssembleOpObjs.getOpObjs("func.funcTypes");
					builders=new MyMap<>(3);
					for(int i=0;i<builderList.size();i++) {
						builders.add(builderList.get(i).getType(),builderList.get(i));
					}
				}
			}
		}
	}

	public static InfoBuilder get(Type type) {
		init();
		return builders.get(type);
	}
}
