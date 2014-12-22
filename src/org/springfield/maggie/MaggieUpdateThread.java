/* 
* MaggieLoadThread.java
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

import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;

public class MaggieUpdateThread extends Thread{
	
	private int mb = 1024*1024;
	private boolean running = true;
	
	public MaggieUpdateThread() {
		start();
	}
	
	public void run() {
		while (running) {		
			Runtime runtime = Runtime.getRuntime();
			long totalmem = runtime.totalMemory() / mb;
			long freemem = runtime.freeMemory() / mb;
			long usedmem = (runtime.totalMemory() - runtime.freeMemory()) / mb;
			long maxmem = runtime.maxMemory() / mb;
			System.out.println("totalmem = "+totalmem+"MB freemem = "+freemem+"MB usedmem = "+usedmem+"MB maxmem = "+maxmem+"MB");
			try {
				sleep(60*1000);
			} catch(Exception e) {
				System.out.println("Maggie can't sleep in update");
			}
		}
	}
}
