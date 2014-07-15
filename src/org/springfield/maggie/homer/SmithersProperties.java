/* 
* SmithersProperties.java
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
package org.springfield.maggie.homer;

public class SmithersProperties {
	private String ipnumber;
	private String port;
	private boolean alive = true;

	public void setIpNumber(String i) {
		ipnumber = i;
	}
	
	public String getIpNumber() {
		return ipnumber;
	}
	
	public void setPort(String i) {
		port = i;
	}
	
	
	public String getPort() {
		return port;
	}
	
	public void setAlive(boolean a) {
		alive = a;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
