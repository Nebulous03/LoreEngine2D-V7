package loreEngine.utils;

public class Log {
	
	private static LogLevel globalLogLevel = LogLevel.DEBUG;
	
	public static void log(LogLevel logLevel, String text) {
		if(logLevel.getLevel() >= globalLogLevel.getLevel()) {
			if(logLevel.isError()) Console.printErr(logLevel.getPrefix(), text);
			else Console.print(logLevel.getPrefix(), text);
		}
	}
	
	public static void logln(LogLevel logLevel, String text) {
		if(logLevel.getLevel() >= globalLogLevel.getLevel()) {
			if(logLevel.isError()) Console.printErrln(logLevel.getPrefix(), text);
			else Console.println(logLevel.getPrefix(), text);
		}
	}
	
	public static void setGlobalLogLevel(LogLevel logLevel) {
		globalLogLevel = logLevel;
	}
	
	public static LogLevel getGlobalLogLevel() {
		return globalLogLevel;
	}

}
