package calculator;

import java.util.Arrays;

public class StatsTableFunc{
	private int[] freqs;
	double[][] groups;
	private int totalFreqs;
	
	public StatsTableFunc(double[][] groups,int[] frequencies) throws Exception {
		if(groups.length!=freqs.length) {
			throw new Exception("the number of groups!=number of frequencies!");
		}
		//input sanitation
		for(int i=0;i<groups.length;i++) {
			if(groups[i].length!=2) {
				throw new Exception("each group array must have two elements!");
			}
		}
		this.freqs=frequencies;
		this.groups=groups;
		this.totalFreqs=Arrays.stream(freqs).sum();
	}
	
	public Double mean() {
		double sum=0;
		for(int i=0;i<groups.length;i++) {
			sum+=(groups[i][0]+groups[i][1])/2*freqs[i];
		}
		return sum/totalFreqs;
	}
	
	public double variant() {
		double sum=0;
		for(int i=0;i<groups.length;i++) {
			sum+=Math.pow((groups[i][0]+groups[i][1])/2,2)*freqs[i];
		}
		return sum/totalFreqs-Math.pow(mean(), 2);
	}
	
	public double percentile(double percent) {
		//assume the data are evenly distributed among the groups
		double pos=totalFreqs*percent/100;
		int i=0;
		while(pos>0) {
			pos-=freqs[i++];
		}
		//the group which the percentile number is in
		double[] group=groups[--i];
		double freq=freqs[i];
		return (freq+pos)/freq*(group[1]-group[0])+group[0];
	}
	
	public double median() {
		return percentile(50);
	}
	
	public double[] mode() {//find the group that has the most frequency
		int max=0;
		for(int i=0;i<freqs.length;i++) {
			if(freqs[i]>freqs[max]) {
				max=i;
			}
		}
		return groups[max];
	}
}
