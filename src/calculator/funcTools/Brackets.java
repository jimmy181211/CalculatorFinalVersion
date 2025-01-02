package calculator.funcTools;

public interface Brackets {
	public final static MyMap<String, Integer> prioBrkts=new MyMap<>(
		new String[]{"(",")","[","]","{","}"},
		new Integer[] {0,0,1,1,2,2});
	
	public final static Character[] brkts= {'(','[','{',')',']','}'};
	public final static Character[] closedBrkts={']',')','}'};
	public final static Character[] openBrkts= {'[','(','{'};
	
	public static Integer getPrio(String brkt) {
		return prioBrkts.get(brkt);
	}
	
	//overload
	public static boolean isBrkt(String str) {
		return prioBrkts.get(str)==null?false:true;
	}
	
	//overload
	public static boolean isBrkt(char ch) {
		return isCloseBrkt(ch) && isOpenBrkt(ch);
	}
	
	public static boolean isCloseBrkt(char ch) {
		return Functions.search(ch, closedBrkts)==-1?false:true;
	}
	
	public static boolean isCloseBrkt(String str) {
		if(str.length()>1) {
			return false;
		}
		return isCloseBrkt(str.charAt(0));
	}
	
	public static boolean isOpenBrkt(String str) {
		if(str.length()>1) {
			return false;
		}
		return isOpenBrkt(str.charAt(0));
	}
	
	public static boolean isOpenBrkt(char ch) {
		return Functions.search(ch, openBrkts)==-1?false:true;
	}
	
	public static boolean isValidBrkt(String txt) {
		ArrStack<Character> stk=new ArrStack<>(10);
		char[] txtCh=txt.toCharArray();
		for(char e:txtCh) {
			int idx=Functions.search(e,brkts);
			if(idx<brkts.length/2 && idx>=0) {//left brackets
				stk.push(brkts[idx+brkts.length/2]);
			}
			else if(idx>=brkts.length/2) {//right brackets
				if(stk.peek()!=null && e==stk.peek()) {
					stk.pop();
				}
				else {
					return false;
				}
			}
		}
		return stk.isEmpty()?true:false;
	}
	
	public static void main(String[] args) {
		char a='2';
		Object b=a;
		System.out.println(b);
	}
}
