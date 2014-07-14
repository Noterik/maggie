package org.springfield.maggie;

import java.net.InetAddress;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springfield.maggie.homer.*;

public class MaggieContextListener implements ServletContextListener {

	private static LazyHomer lh = null; 
	
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Maggie: context initialized");
		ServletContext servletContext = event.getServletContext();
		
		
 		LazyHomer lh = new LazyHomer();

		lh.init(servletContext.getRealPath("/"));
		
		new MaggieLoader();
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Maggie: context destroyed");
		
		
	}

}
