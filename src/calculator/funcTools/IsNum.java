package calculator.funcTools;

public class IsNum {
	public static boolean isNum(char chr) {
		if(chr<=57 && chr>=48) {
			return true;
		}
		return false;
	}
	
	public static <E extends CharSequence>boolean isNum(E str) {
		int cnt=0;
		if(str.charAt(0)=='.' || str.charAt(str.length()-1)=='.') {
			return false;
		}
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			if(ch=='.') {
				cnt++;
			}
			else if(!isNum(ch)) {
				return false;
			}
		}
		return cnt<=1?true:false;
	}
}
