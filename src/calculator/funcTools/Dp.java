package calculator.funcTools;

public class Dp {
	
	//determine how many decimal places there are
	public static int dp(String strNum) {
		//obtain the substring from the index of . +1
		//obtain the decimal
		String str=strNum.substring(strNum.indexOf('.')+1,strNum.length());
		return Long.parseLong(str)==0?0:str.length();
	}
	
	//change it to the number of decimal places
	public static double toDp(Double num, int d_p) {
		return (double)Math.round(num*Math.pow(10, d_p))/Math.pow(10, d_p);
	}
	
	public static void main(String[] args) {
		
	}
}
