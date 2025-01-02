package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Statistics {
	private double[] values;
	public Statistics(double[] values) {
		this.values=values;
	}
	public Statistics(double value) {
		this.values=new double[] {value};
	}
	
	public double mean() {
		return mean(values);
	}
	public static double mean(double[] values) {
		return (Arrays.stream(values)).sum()/values.length;
	}
	
	public static double percentile(double[] values,double percent) {
		Arrays.sort(values);
		return values[(int) Math.ceil(values.length*percent/100)-1];
	}
	public static double median(double[] values) {
		return percentile(values,50);
	}
	
	public double percentile(double percent) {
		return percentile(values,percent);
	}
	
	public static double variant(double[] values) {
		double mean=mean(values);
		double sum=0;
		for(double e:values) {
			sum+=Math.pow(e, 2);
		}
		return sum/values.length-Math.pow(mean, 2);
	}
	
	public double variant() {
		return variant(values);
	}
	
	public double mode() {
		Map<Double,Double> map=new HashMap<>();
		Set<Map.Entry<Double,Double>> set = map.entrySet();
		List<Double> list=new ArrayList<>();
		List<Double> res=new ArrayList<>();
		for(double i:values) {
			map.put(i, map.getOrDefault(i,0.0)+1);
		}
		map.forEach((k,v)->{
			list.add(v);
		});
		
		Collections.sort(list);
		double max=list.get(list.size()-1);
		for(Map.Entry<Double,Double> entry:set) {
			if(entry.getValue()==max) {
				res.add(entry.getKey());
			}
		}
		return res.get(0);
	}
	
	//least squares method
	public double LSM(double[] y) {
		double Sxy=0;
		double meanX=mean();
		double meanY=mean(y);
		for(int i=0;i<values.length;i++) {
			Sxy+=(values[i]-meanX)*(y[i]-meanY);
		}
		return Sxy/Math.sqrt(variant()*variant(y));
	}
	
	public void statistics(double[] x, double[] y) throws Exception{
		if(x.length!=y.length) {
			throw new Exception("expectation calc:random variables length not equals to probabilities length");
		}
	}
	
	public static void main(String[] args) {

	}
}
