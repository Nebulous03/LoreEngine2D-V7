package loreEngine.utils;

public class LogLevel {
	
	public static final LogLevel ERROR   = new LogLevel("ERROR",   3, true );
	public static final LogLevel WARNING = new LogLevel("WARNING", 2, false);
	public static final LogLevel INFO    = new LogLevel("INFO",    1, false);
	public static final LogLevel DEBUG   = new LogLevel("DEBUG",   0, false);
	
	private int level = 0;
	private String prefix = "LOG";
	private boolean isError = false;

	public LogLevel(String prefix, int level, boolean isError) {
		this.prefix = prefix;
		this.level = level;
		this.isError = isError;
	}

	public String getPrefix() {
		return prefix;
	}
	
	public int getLevel() {
		return level;
	}

	public boolean isError() {
		return isError;
	}
}
