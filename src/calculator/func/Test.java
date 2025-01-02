package calculator.func;

import calculator.funcTools.DynArray;

public class Test{
	public static void main(String[] args) {
		DynArray<Double> dynarr=new DynArray<>(10);
		DynArray<Double> dynarr2=dynarr;
		dynarr2.add(0.2);
		System.out.println(dynarr.get(0));
	}
}
