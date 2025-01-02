package calculator.opHandlers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;

import calculator.funcTools.HashMapS;
import calculator.operation.Arithmetics;
import calculator.operation.UnaryOperation;
import calculator.operation.UnaryOperationAng;

public interface AssembleOpObjs {
	@SuppressWarnings("unchecked")
	private static <E> void loadeClasses(List<File> classes, String scan, List<E> clazzes) {
		for (File file : classes) {
			String fPath = file.getAbsolutePath().replaceAll("\\\\", "/");
			String packageName = fPath.substring(fPath.lastIndexOf(scan));
			packageName = packageName.replace(".class", "").replaceAll("/", ".");
			try {
				clazzes.add((E) Class.forName(packageName).getDeclaredConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static Boolean func(Class<?> cls) {
		if (cls == UnaryOperationAng.class) {
			return true;
		}
		return false;
	}

	private static Function<Class<?>, Boolean> isCond() {
		return (cls) -> func(cls);
	}

	// set condition for the superclass type of the loaded classes
	@SuppressWarnings("unchecked")
	private static <E> void loadeClassesConditional(List<File> classes, String scan, Function<Class<?>, Boolean> isCond,
			List<E> clazzes) {
		for (File file : classes) {
			String fPath = file.getAbsolutePath().replaceAll("\\\\", "/");
			String packageName = fPath.substring(fPath.lastIndexOf(scan));
			packageName = packageName.replace(".class", "").replaceAll("/", ".");
			try {
				Object obj = Class.forName(packageName).getDeclaredConstructor().newInstance();
				if (isCond.apply(obj.getClass().getSuperclass())) {
					clazzes.add((E) obj);
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
//				e.printStackTrace();
			}
		}
	}

	private static void listFiles(File dir, List<File> fileList) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				listFiles(f, fileList);
			}
		} else {
			if (dir.getName().endsWith(".class")) {
				fileList.add(dir);
			}
		}
	}

	public static <E> void getOpObjs(String packagePath, boolean iscond, List<E> result) {
		String scan = packagePath;
		// convert '.' to '/' because this is a file operation
		scan = scan.replaceAll("\\.", "/");
		Enumeration<URL> dirs = null;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(scan);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (dirs.hasMoreElements()) {
			URL url = dirs.nextElement();
			if (url.getProtocol().equals("file")) {
				List<File> classes = new ArrayList<File>();
				// recursion to get all the files in the folders
				listFiles(new File(url.getFile()), classes);
				// load all the classes
				if (iscond) {
					loadeClassesConditional(classes, scan, isCond(), result);
				} else {
					loadeClasses(classes, scan, result);
				}
			}
		}
	}

	public static <E> void getOpObjs(String packagePath, List<E> list) {
		getOpObjs(packagePath, false, list);
	}

	public static <E> List<E> getOpObjs(String packagePath) {
		List<E> list = new ArrayList<>();
		getOpObjs(packagePath, false, list);
		return list;
	}

	public static HashMapS<Arithmetics> getOps(String packagePath) {
		HashMapS<Arithmetics> varMap = new HashMapS<>();
		if (packagePath.equals("") || packagePath == null) {
			return varMap;
		}
		List<Arithmetics> result = getOpObjs(packagePath);
		// if condition: result!=null considers the case where the package is empty
		if (result != null) {
			for (Arithmetics obj : result) {
				varMap.put(obj.operator, obj);
			}
		}
		return varMap;
	}

	public static void main(String[] args) {
		System.out.println("hello world");
		List<UnaryOperation> list = new ArrayList<>();
		getOpObjs("calculator.varMap.binary", list);
		System.out.println(list.size());
	}
}
