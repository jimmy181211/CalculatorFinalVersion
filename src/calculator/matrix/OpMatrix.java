package calculator.matrix;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import calculator.operation.Operation;

public final class OpMatrix extends Operation{
	public OpMatrix() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
		super("calculator.matrix");
	}
}
