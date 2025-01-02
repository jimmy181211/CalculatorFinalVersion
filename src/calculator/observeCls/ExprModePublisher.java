package calculator.observeCls;

/*
 * @Description: decorator design pattern
 */
public class ExprModePublisher extends Publisher<Subscriber<CalcModeType>, CalcModeType> {
	public ExprModePublisher() {
		super(CalcModeType.calc);
	}
}
