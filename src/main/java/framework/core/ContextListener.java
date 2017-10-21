package framework.core;

import javax.servlet.*;

public class ContextListener implements ServletContextListener {
	private ServletContext context = null;

	public void contextDestroyed(ServletContextEvent event) {
		// Output a simple message to the server's console
		System.out.println("The CodingDiary Web App Has Been Removed");
		this.context = null;
	}

	// 这个方法在Web应用服务做好接受请求的时候被调用。
	public void contextInitialized(ServletContextEvent event) {
		this.context = event.getServletContext();
		// Output a simple message to the server's console
		System.out.println("The CodingDiary Web App Is Ready");
	}
}
