package calculator.funcTools;

public class AccessMode {
	// by default this property states that the class is read-only
	boolean isROM = false;

	public AccessMode(boolean initialState) {
		this.isROM = initialState;
	}

	public AccessMode() {
	}

	public void ROM(boolean isROM) {
		this.isROM = isROM;
	}

	public void ROMcheck() {
		if (isROM) {
			new Exception("read only mode can't make use of put function").printStackTrace();
		}
	}
}
