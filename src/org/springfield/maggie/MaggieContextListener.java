/* 
* MaggieContextListener.java
* 
* Copyright (c) 2014 Noterik B.V.
* 
* This file is part of Maggie, related to the Noterik Springfield project.
*
* Maggie is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Maggie is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Maggie.  If not, see <http://www.gnu.org/licenses/>.
*/
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
