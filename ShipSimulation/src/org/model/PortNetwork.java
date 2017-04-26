package org.model;

import java.util.List;

public abstract class PortNetwork {
	
	protected List<Port> ports;
	protected double[][] routeMatrix;

	public PortNetwork (List<Port> ports, double[][] routeMatrix){
		this.ports = ports;
		this.routeMatrix = routeMatrix;
	}
	public void timeNext(){
		// 1. Check the occupied and # of waiting ship and If it is available, change the ship's status => BERTH
		// 2. BERTH, BUNKERING, LOADING, UNLOADING, Port provide service,
		// 3. Check the ship's status and update ships' status and port status
				
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
