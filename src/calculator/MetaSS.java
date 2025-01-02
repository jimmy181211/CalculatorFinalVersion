package calculator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MetaSS {
	// is executed
	protected boolean isExe = false;
	protected String cmd;
	protected String time;
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

	public MetaSS(String command) {
		this.time = df.format(new Date());
		this.cmd = command;
	}

	public void setExecuted() {
		this.isExe = true;
	}
}
