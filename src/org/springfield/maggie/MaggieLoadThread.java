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

import java.util.Iterator;
import java.util.List;

import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.FsNode;

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
			FSListManager.add(url+provider+"/teaser",fslist);
			FSListManager.add(url+provider+"/collection",fslist);
			orderEpisodes(fslist);
			provider = ml.getNextProvider();
		}
		System.out.println("Maggie thread ("+tnumber+") done");
		ml.signalDone();
	}
	
	private void orderEpisodes(FSList fslist) {
		int basecounter = 1;
		List<FsNode> nodes = fslist.getNodes();
		for(Iterator<FsNode> iter = nodes.iterator() ; iter.hasNext(); ) {
			// get the next node
			FsNode n = (FsNode)iter.next();
			int value = basecounter++;
			int evalue = -1;
			int svalue = -1;
			try {
				String tmp = n.getProperty("episodeNumber");
				if (tmp!=null) {
					tmp = tmp.replace("OC", "00");
					System.out.println("EPI="+tmp);
					evalue = Integer.parseInt(tmp);
				}
			} catch(Exception e) {}
			try {
				String tmp = n.getProperty("Series/season");
				if (tmp!=null) {
					System.out.println("SEA="+tmp);
					svalue = Integer.parseInt(tmp);
				}
			} catch(Exception e) {}
			
			if (evalue!=-1) {
				if (svalue!=-1) {
					value = evalue*10000;
					value += svalue*1000000;
				} else {
					value = evalue*10000;
				}
			} 
			
			String valueString = String.format("%010d", value);
			n.setProperty("ordervalue", valueString);
		}
	}
}
