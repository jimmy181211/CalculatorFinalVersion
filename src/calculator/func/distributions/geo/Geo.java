package calculator.func.distributions.geo;

import calculator.func.distributions.Distribution;

public class Geo extends Distribution{
	// only one parameter: p
	public Geo(Double[] params) {
		super("GEO");
		isValid(params);
		this.addObjs(new OrigGeo(params), new InvGeo(params));
	}
	
	public Geo() {
		super("GEO");
	}

	@Override
	public void isValid(Double[] params) {
		Distribution.isPValid(params[0]);
	}
}
