package framework.core;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

public class WebApplication {

	public static ApplicationContext ctx;
	
	public static ServletContext sctx;
	
	public static String contextPath;
	
	public static boolean isDestroy = false;
}
