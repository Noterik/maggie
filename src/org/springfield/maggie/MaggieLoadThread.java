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

public class MaggieLoadThread extends Thread{
	private MaggieLoader ml;
	private FSList fslist;
	private String url;
	private static Object obj;
	private int tnumber;
	
	// trying to be multithreaded.
	public MaggieLoadThread(Object o,MaggieLoader m,String u,FSList f,int i) {
		obj = o;
		tnumber = i;
		ml = m;
		fslist = f;
		url = u;
		start();
	}
	
	public void run() {
		System.out.println("Maggie thread ("+tnumber+") started");
		String provider = ml.getNextProvider();
		while (provider!=null) {
			FSListManager.add(url+provider+"/video",fslist);
			FSListManager.add(url+provider+"/audio",fslist);
			FSListManager.add(url+provider+"/series",fslist);
			FSListManager.add(url+provider+"/picture",fslist);
			FSListManager.add(url+provider+"/doc",fslist);
			provider = ml.getNextProvider();
		}
		System.out.println("Maggie thread ("+tnumber+") done");

	}
}
