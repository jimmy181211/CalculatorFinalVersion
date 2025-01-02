package calculator;

/*
 * @Description: using Wrapper design pattern. This the implementation classes connects the client and the actual calculation object, translating the string cmd into the form that the object can interpret and execute
 * it is also an Originator, who holds the data state and communicates with the care-taker
 */
public abstract class Command implements Originator{
	private static HistoryLog history = new HistoryLog();
	protected String currKey;
	
	protected abstract <E> E executeInner(String cmd);
	
	public Object execute(String cmd) {
		MetaSS meta = history.store(cmd);//receiver1
		Object val=executeInner(cmd);
		meta.setExecuted(); 
		save();//save it to the CommandStack class
		return val;
	}
}
