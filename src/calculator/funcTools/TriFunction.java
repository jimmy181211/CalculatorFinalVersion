package calculator.funcTools;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<A,B,C,R> {
	R apply(A num1,B num2,C num3);
	default <V>TriFunction<A,B,C,V> andThen(Function<? super R,? extends V> func){
		Objects.requireNonNull(func);
		return (num1,num2,num3)->func.apply(apply(num1,num2,num3));
	}
}
