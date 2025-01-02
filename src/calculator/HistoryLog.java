package calculator;

import calculator.funcTools.DynArray;

public class HistoryLog {
	private DynArray<MetaSS> snapShots;

	public HistoryLog() {
		this.snapShots = new DynArray<>(10);
	}

	public MetaSS store(String command) {
		MetaSS meta = new MetaSS(command);
		snapShots.add(meta);
		return meta;
	}

	public MetaSS get() {
		return this.snapShots.get();
	}

	public DynArray<MetaSS> getAll() {
		return this.snapShots.clone();
	}
}
