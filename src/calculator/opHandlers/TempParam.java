package calculator.opHandlers;

/*
 * @version 1.0
 * @Description: TempParam signifies that the class is a temporary variable stored in varMap of RPN. 
 * If isVisited is set, RPN can execute 'remove var' operation, removing the temporary variable from 
 * thevarMap after doing one calculation to save memory
 */
public class TempParam {
	protected boolean isVisited;

	public boolean isVisited() {
		return this.isVisited;
	}

	public void isVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
}
