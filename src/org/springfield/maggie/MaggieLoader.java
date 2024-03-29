/* 
* MaggieLoader.java
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

import java.util.*;

//import org.springfield.lou.application.components.types.OpenappsComponent;
import org.springfield.fs.*;

public class MaggieLoader extends Thread {
	
	private ArrayList<String> providers = new ArrayList<String>();
	private static Object obj = new Object();
	private static int done = 0;
	private static MaggieUpdateThread updatethread = null;
	
	public MaggieLoader() {
		updatethread = new MaggieUpdateThread();
		providers.add("agency");
		providers.add("nisv");
		providers.add("dw");
		providers.add("lcva");
		providers.add("rte");
		providers.add("tvc");
		providers.add("tvr");
		providers.add("ina");
		providers.add("nina");
		providers.add("orf");
		providers.add("sase");
		providers.add("kb");
		providers.add("nava");
		providers.add("ctv");
		providers.add("rtp");
		providers.add("henaa");
		providers.add("dr");
		providers.add("rtbf");
		providers.add("rai");
		providers.add("luce");
		providers.add("rtvs");
		providers.add("bbc");
		providers.add("vrt");
		providers.add("tvp");
		providers.add("memoriav_srf");
		providers.add("memoriav_rts");
		providers.add("fina");
		providers.add("rh");
		providers.add("rtve");
		start();
	}
	
	public synchronized String getNextProvider() {
		if (providers.size()>0) {
			String provider = providers.get(0);
			providers.remove(0);
			System.out.println("PROVIDERS LEFT="+providers.size());
			return provider;
		}
		return null;
	}
	
	public void extendIndex() {
		System.out.println("extend index");
		String uri = "/domain/euscreenxl/user/*/*"; // does this make sense, new way of mapping (daniel)
		FSList fslist = FSListManager.get(uri);
		List<FsNode> nodes = fslist.getNodes();
		
		for(Iterator<FsNode> iter = nodes.iterator() ; iter.hasNext(); ) {
			FsNode n = (FsNode)iter.next();	
			//System.out.println("NODE="+n.asXML());
			// check for publisherbroadcaster changes needed
			String pb = n.getProperty("provider");
			if (pb!=null) {
        			if (pb.equals("KB")) {
        				n.setProperty("provider", "Kungliga biblioteket");
        			} else if (pb.equals("NINA")) {
        				n.setProperty("provider", "FINA");
        			} else if (pb.equals("TVC")) {
        			    	n.setProperty("provider", "TV3 Televisió de Catalunya (TVC)");
        			} else if (pb.equals("SASE")) {
    					n.setProperty("provider", "Screen Archive South East");
        			} else if (pb.equals("NISV")) {
    					n.setProperty("provider", "Netherlands Institute for Sound and Vision");
        			} else if (pb.equals("DW")) {
    					n.setProperty("provider", "Deutsche Welle");
        			}
        			System.out.println("provider="+n.getProperty("provider"));
			} else {
				System.out.println("EMPTY PROVIDER NAME="+n.getId());
			}
			
			String title = n.getProperty("TitleSet_TitleSetInEnglish_title");
			if (title!=null) {
				String newtitle = title;
				if (newtitle.indexOf("\"")==0) {
					newtitle = newtitle.substring(1);
				} else	if (newtitle.indexOf("'")==0) {
					newtitle = newtitle.substring(1);
				}
				n.setProperty("sort_title",newtitle);
			}
		}
	}
	
	public void signalDone() {
		done++;
		System.out.println("DONE SIGNAL AT "+done);
		if (done>3) {
			extendIndex();
		}
	}

	
	public void run() {
		FSList test = FSListManager.get("/domain/euscreenxl/user/*/*");
		if (test!=null) {
			System.out.println("PREVIEW CACHING IGNORED (ALLREADY IN CACHE THIS SHOULD NOT HAPPEN!");
			return;
		} else {
			System.out.println("PREVIEW CACHING");
			FSListManager.put("/domain/euscreenxl/user/*/*",new FSList());
		}
		long starttime = new Date().getTime(); // we track the request time for debugging only

    	FSList fslist = new FSList("/domain/euscreenxl/user/*/*");
		FSListManager.put("/domain/euscreenxl/user/*/*",fslist);
		for (int i=0;i<4;i++) { // start multiple threads
				new MaggieLoadThread(obj, this,"/domain/euscreenxl/user/eu_",fslist,i);
		}		
    			

	}

	


}
