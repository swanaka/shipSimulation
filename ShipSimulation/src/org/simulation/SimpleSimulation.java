package org.simulation;

import org.model.Fleet;
import org.model.Market;
import org.model.Port;
import org.model.PortNetwork;

public class SimpleSimulation extends Simulation{

	public SimpleSimulation(Fleet fleet, PortNetwork portNetwork, Market market, int endTime) {
		super(fleet, portNetwork, market, endTime);
	}

	@Override
	public void save(int now) {
		//TO-DO
		System.out.println("Now: " + now + " Saved!");
		
	}

}
