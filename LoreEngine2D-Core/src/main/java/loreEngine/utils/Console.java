package loreEngine.utils;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
	
	private static PrintStream consoleOut = System.out;
	private static PrintStream consoleErr = System.err;
	
	private static String globalPrefix = "CONSOLE";
	private static String globalSuffix = "CONSOLE";
	
	private static String dateNotation   = "[]";
	private static String prefixNotation = "[]";
	private static String suffixNotation = "[]";
	
	private static boolean dateEnabled	 = true;
	private static boolean prefixEnabled = true;
	private static boolean suffixEnabled = false;
	
	private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static void printRaw(String text) {
		consoleOut.print(text);
		consoleOut.flush();
	}
	
	public static void printErrRaw(String text) {
		consoleErr.print(text);
		consoleErr.flush();
	}
	
	private static String compileText(String prefix, String text, String suffix) {
		String temp = text;
		if(prefixEnabled)
			temp = prefixNotation.charAt(0) + prefix + prefixNotation.charAt(1) + temp;
		if(dateEnabled)
			temp = dateNotation.charAt(0) + dateFormat.format(new Date()).toString() + dateNotation.charAt(1) + temp;
		if(suffixEnabled)
			temp = suffixNotation.charAt(0) + suffix + suffixNotation.charAt(1) + temp;
		return temp;
	}
	
	public static void print(String text) {
		print(globalPrefix, text, globalSuffix);
	}
	
	public static void print(String prefix, String text) {
		print(prefix, text, globalSuffix);
	}
	
	public static void print(String prefix, String text, String suffix) {
		printRaw(compileText(prefix, text, suffix));
	}
	
	public static void println(String text) {
		println(globalPrefix, text, globalPrefix);
	}
	
	public static void println(String prefix, String text) {
		println(prefix, text, globalPrefix);
	}
	
	public static void println(String prefix, String text, String suffix) {
		printRaw(compileText(prefix, text + "\n", suffix));
	}
	
	public static void printErr(String text) {
		printErr(globalPrefix, text, globalSuffix);
	}
	
	public static void printErr(String prefix, String text) {
		printErr(prefix, text, globalSuffix);
	}
	
	public static void printErr(String prefix, String text, String suffix) {
		printErrRaw(compileText(prefix, text, suffix));
	}
	
	public static void printErrln(String text) {
		printErrln(globalPrefix, text, globalPrefix);
	}
	
	public static void printErrln(String prefix, String text) {
		printErrln(prefix, text, globalPrefix);
	}
	
	public static void printErrln(String prefix, String text, String suffix) {
		printErrRaw(compileText(prefix, text + "\n", suffix));
	}


}
