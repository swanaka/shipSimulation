package org.model;

public abstract class PortNetwork {
	
	private Port[] ports;
	public void timeNext(){
		for (Port port : ports){
			port.timeNext();
		}
	}
}
