package calculator.func.distributions;

import calculator.ErrorCodes;
import calculator.func.distributions.binomial.Binomial;
import calculator.func.distributions.geo.Geo;
import calculator.func.distributions.negbinomial.NegBinomial;
import calculator.func.distributions.normal.Normal;
import calculator.func.distributions.poisson.Poisson;

public class DistribFactory {
	//because it is dynamically initialised, it can't make use of extended abstract factory
	public static Distribution work(String distribName,Double[] params) {
		switch(distribName) {
		case "B":
			return new Binomial(params);
		case "N":
			return new Normal(params);
		case "NEGB":
			return new NegBinomial(params);
		case "GEO":
			return new Geo(params);
		case "PO":
			return new Poisson(params);
		default:
			//user-end error report
			System.out.println(ErrorCodes.get("13")+" for distribution");
			System.exit(0);
			return null;
		}
	}
}
