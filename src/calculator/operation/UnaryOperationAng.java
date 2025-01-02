package calculator.operation;

import calculator.observeCls.AngType;
import calculator.observeCls.Subscriber;

public abstract class UnaryOperationAng extends UnaryOperation implements Subscriber<AngType> {
	public UnaryOperationAng(String operator) {
		super(operator);
	}

	protected AngType type = AngType.rad;

	// angle out
	public Double changeAngOutMode(Double ang) {
		if (type == AngType.degree) {
			ang = ang / Math.PI * 180;
		}
		return ang;
	}

	// angle in
	public Double changeAngInMode(Double ang) {
		if (type == AngType.degree) {
			ang = ang / 180 * Math.PI;
		}
		return ang;
	}

	@Override
	public void update(AngType type) {
		this.type = type;
	}
}
