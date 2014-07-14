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
