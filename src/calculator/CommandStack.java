package calculator;

import calculator.funcTools.Entry;
import calculator.funcTools.HashMapS;
import calculator.funcTools.HashStack;

//notice: there is a isExe attribute in MetaSS class. Those that are false won't be added into any of the hashstack

public class CommandStack {
	static class HashStackS<E> extends HashStack<String, E> {
		public HashStackS(int capacity) {
			super(capacity);
		}

		public HashStackS() {
			super();
		}

		@Override
		protected int hash(String key) {
			return HashMapS.hashS(key);
		}
	}

	// stores the commands deleted
	private HashStackS<SnapShot> delCmdStk;
	// stores the commands executed and make an effect
	private HashStackS<SnapShot> exeCmdStk;

	public CommandStack() {
		delCmdStk = new HashStackS<>();
		exeCmdStk = new HashStackS<>();
	}

	public void add(String meta, SnapShot data) {
		// update funcOpManager
		exeCmdStk.put(meta, data);
	}

	// remove the element from exeCmdStk and transfer it to delCmdStk
	public void undo(String meta) {
		Entry<String, SnapShot> ss;
		if (meta == null) {
			exeCmdStk.removeE();
			ss = exeCmdStk.getE();// obtain the last state
			CmdType ssType = ss.val.getType();
			
			exeCmdStk.traverseIter(values -> {
				if (values.val.getType() == ssType) {
					// update the state of the originator. This logic is for the calculation
					// instruction
					values.val.restore(values.key, CmdType.undo);
				}
			});
		} else {
			// usually for assignment
			ss = exeCmdStk.removeE(meta);
			ss.val.restore(ss.key, CmdType.undo);
		}
		delCmdStk.put(ss.key, ss.val);
	}

	// restore a deleted element from delCmdStk and transfer it to exeCmdStk
	public void restore(String meta) {
		Entry<String, SnapShot> ss;
		if (meta == null) {
			ss = delCmdStk.removeE();
		} else {
			// usually for assignment
			ss = delCmdStk.removeE(meta);
		}
		exeCmdStk.put(ss.key, ss.val);
		// renew the state of the originator
		ss.val.restore(ss.key, CmdType.restore);
	}

	public static void main(String[] args) {
		String[] strs = { "a", "b", "c", "d" };
		Integer[] vals = { 1, 2, 3, 4 };
		HashStackS<Integer> hashStk = new HashStackS<>(2);
		for (int i = 0; i < strs.length; i++) {
			hashStk.put(strs[i], vals[i]);
		}
		hashStk.remove("a");
		hashStk.traverseIter(values -> {
			System.out.println(values.key + " " + values.val);
		});
	}
}
