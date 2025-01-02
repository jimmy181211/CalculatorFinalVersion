package calculator.funcTools;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BiFunction<A,B,R> {
	R apply(A num1,B num2);
	default <V>BiFunction<A,B,V> andThen(Function<? super R,?extends V> func){
		Objects.requireNonNull(func);
		return (num1,num2)->func.apply(apply(num1,num2));
	}
}
