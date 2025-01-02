package calculator;

/*
 * @Description: CalcMode interface offers any Operation Class two ways to operate
 * 1. return one value at a time
 * 2. return multiple values
 */
public interface CalcReturnMode<E> {
	Object singleCalc(E params);
	Object multiCalc(E params);
}
