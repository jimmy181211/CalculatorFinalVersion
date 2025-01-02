package calculator.observeCls;

import java.util.ArrayList;
import java.util.List;

import calculator.opHandlers.AssembleOpObjs;
import calculator.operation.UnaryOperationAng;

/*
 * @Description: decorator design pattern
 */
public class AngTypePublisher extends Publisher<Subscriber<AngType>, AngType> {

	public AngTypePublisher() {
		super(AngType.rad);
		initAngTypeList();
	}

	private void initAngTypeList() {
		List<UnaryOperationAng> list = new ArrayList<>();
		AssembleOpObjs.getOpObjs("calculator.numeric.unary", true, list);
		AssembleOpObjs.getOpObjs("calculator.varMap", true, list);
		// sacrifice efficiency in return of readability..
		for (int i = 0; i < list.size(); i++) {
			subscribe(list.get(i));
		}
	}
}
