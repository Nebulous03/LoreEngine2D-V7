package loreEngine.core.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;

public class Window {
	
	protected String title;
	protected int width;
	protected int height;
	protected DisplayType display;
	protected boolean decorated;
	protected boolean vsync;
	
	protected long glWindowID;
	
	private static boolean glInitialized;

	public Window() {}

	public Window(String title,int width, int height, DisplayType display, boolean decorated) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.display = display;
		this.decorated = decorated;
		this.vsync = false;
	}

	public static Window createWindow(String title,int width, int height, DisplayType display, boolean decorated) {
		Window window = new Window(title, width, height, display, decorated);
		window.initWindow(window);
		window.focus();
		return window;
	}
	
	public static Window createWindow(String title,int width, int height, DisplayType display) {
		return createWindow(title, width, height, display, true);
	}
	
	public static Window createWindow(String title,int width, int height, boolean decorated) {
		return createWindow(title, width, height, DisplayType.WINDOWED, decorated);
	}
	
	public static Window createWindow(String title,int width, int height) {
		return createWindow(title, width, height, DisplayType.WINDOWED, true);
	}

	public void initWindow(Window window) {
		
		initOpenGL();
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
		
		long monitor = glfwGetPrimaryMonitor();
		if(monitor == 0) System.err.println("ERROR! - GLFW failed to find attached monitor!");
		
		GLFWVidMode mode = glfwGetVideoMode(monitor);
		glfwWindowHint(GLFW_RED_BITS, mode.redBits());
		glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits());
		glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits());
		glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());
		
		switch(display){
		default:
			glWindowID = glfwCreateWindow(width, height, title, 0, 0);
			break;
		case WINDOWED:
			glWindowID = glfwCreateWindow(width, height, title, 0, 0);
			break;
		case FULLSCREEN:
			glfwWindowHint(GLFW_DECORATED, GL_FALSE);
			glWindowID = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
			break;
		case WINDOWED_FULLSCREEN:
			glfwWindowHint(GLFW_DECORATED, GL_FALSE);
			glfwCreateWindow(mode.width(), mode.height(), title, glfwGetPrimaryMonitor(), 0);
			break;
		}
		
		glfwMakeContextCurrent(glWindowID);
		GL.createCapabilities();
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glfwSwapInterval(0);
		glClearColor(0.0f, 0.04f, 0.06f, 1.0f);
		
		if(display == DisplayType.WINDOWED)
			glfwSetWindowPos(glWindowID,(mode.width() - width) / 2,(mode.height() - height) / 2);
		
		glfwShowWindow(glWindowID);
		
		GLUtil.setupDebugMessageCallback(); // Move / remove?
	}
	
	private void initOpenGL() {
		if(!glInitialized) {
			
			 GLFWErrorCallback.createPrint(System.err).set();
			
			if (!GLFW.glfwInit()) {
				System.err.println("ERROR! - Failed to initialize GLFW!");
				System.exit(1);
			} else 
				System.out.println("SUCCESS! - GLFW initialized successfully!");
			
			glInitialized = true;
		}
	}
	
	public void update() {
		glfwSwapBuffers(glWindowID);
		glfwPollEvents();
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public Window resize(int width, int height) {
		this.width = width;
		this.height = height;
		glfwSetWindowSize(glWindowID, width, height);
		return this;
	}
	
	public Window setTitle(String title) {
		this.title = title;
		glfwSetWindowTitle(glWindowID, title);
		return this;
	}
	
	public Window show() {
		glfwShowWindow(glWindowID);
		return this;
	}
	
	public Window hide() {
		glfwHideWindow(glWindowID);
		return this;
	}
	
	public Window focus() {
		glfwFocusWindow(glWindowID);;
		return this;
	}
	
	public Window setDecorated(boolean decorated) {
		glfwWindowHint(GLFW_DECORATED, decorated ? GL_TRUE : GL_FALSE);
		return this;
	}
	
	public Window vsync(boolean vsync) {
		this.vsync = vsync;
		glfwSwapInterval(vsync ? 1 : 0);
		return this;
	}
	
	public Window setClearColor(float red, float green, float blue){
		glClearColor(red, green, blue, 1.0f);
		return this;
	}
	
	public void destroy() {
		glfwDestroyWindow(glWindowID);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public long getGLWindowID()
	{
		return glWindowID;
	}
	
	public boolean isVisable()
	{
		return glfwGetWindowAttrib(glWindowID, GLFW_VISIBLE) == GLFW_TRUE;
	}
	
	public boolean isFocused()
	{
		return glfwGetWindowAttrib(glWindowID, GLFW_FOCUSED) == GLFW_TRUE;
	}
	
	public void printGLStats(){
        System.out.println(
        " OPENGL: " + glGetString(GL_VERSION) + "\n" +
        " LWJGL: " + Version.getVersion() + "\n" + 
        " GRAPHICS: " + glGetString(GL_RENDERER) + "\n" +
        " VENDORS: " + glGetString(GL_VENDOR) + "\n" +
        " OPERATING SYSTEM: " + System.getProperty("os.name") + "\n" +
        " JAVA VERSION: " + System.getProperty("java.version") + "\n" +
        " CURRENT DIRECTORY: \n" +
        " " + System.getProperty("user.dir")
        );
    }
}
