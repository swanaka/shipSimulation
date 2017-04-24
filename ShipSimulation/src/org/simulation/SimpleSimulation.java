package org.simulation;

import org.model.Fleet;
import org.model.Market;
import org.model.PortNetwork;

public class SimpleSimulation extends Simulation{
	
	private Fleet fleet;
	private PortNetwork portNetwork;
	private Market market;
	
	public SimpleSimulation(Fleet fleet, PortNetwork portNetwork, Market market,int endTime){
		this.fleet = fleet;
		this.portNetwork = portNetwork;
		this.market = market;
		setEndTime(endTime);
	}

	@Override
	public void save() {
		int now = getTime();
		System.out.println("Now: " + now + " Saved!");
		
	}

	@Override
	public void timeNext() {
		market.timeNext();
		fleet.timeNext();
		portNetwork.timeNext();
		
	}

}
