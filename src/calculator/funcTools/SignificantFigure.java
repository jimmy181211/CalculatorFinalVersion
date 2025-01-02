package calculator.funcTools;

public class SignificantFigure {
	//sf can't be bigger than 20 as that is bigger than the maximum number that long type can hold
	public static double sf(double num, int sf) {
		if(sf<0 || num==0.0) {//doesn't allow the sf to be bigger than 20 or smaller than 0
			return 0.0;
		}
		else if(sf>=10) {
			sf=10;
		}
		double threshold=Math.pow(10.0, (double)sf-1.0);
		int cnt=0;
		while(Math.abs(num)<threshold) {
			num*=10;
			cnt++;
		}
		while(Math.abs(num)>threshold*10) {
			num/=10;
			cnt--;
		}
		return Math.round(num)/Math.pow(10, cnt);
	}
}
