package org.simulation;

import org.model.Fleet;
import org.model.Market;
import org.model.Port;
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
		int now = getCurrentTime();
		System.out.println("Now: " + now + " Saved!");

	}

	@Override
	public void timeNext() {
		market.timeNext();
		if(market.checkDemand()!=null){
			market.addContract(fleet,portNetwork);
		}
		fleet.timeNext();
		portNetwork.timeNext();

		// test comment by shiraishi
	}

}
