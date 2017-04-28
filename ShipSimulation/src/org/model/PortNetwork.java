package org.model;

import java.util.List;

public abstract class PortNetwork {
	
	protected List<Port> ports;
	protected double[][] routeMatrix;

	public PortNetwork (List<Port> ports, double[][] routeMatrix){
		this.ports = ports;
		this.routeMatrix = routeMatrix;
	}
	public void timeNext(int now){
		
		for (Port port :this.ports){
			port.timeNext(now);
		}
				
	}
	public Port getPort(String portName){
		for (Port port : ports){
			if (port.getName().equals(portName)) return port;
		}
		return null;
	}
	public void add(Port port){
		//TO-DO
	}
	public void remove(Port port){
		//TO-DO
	}
	public List<Port> getPorts() {
		return ports;
	}
}
