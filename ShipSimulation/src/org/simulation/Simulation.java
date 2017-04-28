package org.simulation;

import org.model.Fleet;
import org.model.Market;
import org.model.PortNetwork;

public abstract class Simulation {
	//Configuration
	private int startTime;
	private int endTime;
	//Status
	protected int now;
	
	private Fleet fleet;
	private PortNetwork portNetwork;
	private Market market;
	
	//Function
	public abstract void save(int now);
	
	public Simulation(Fleet fleet, PortNetwork portNetwork, Market market,int endTime){
		this.fleet = fleet;
		this.portNetwork = portNetwork;
		this.market = market;
		setEndTime(endTime);
	}
	
	public void execute(){
		while(now == endTime){
			timeNext(now);
			save(now);
			now++;
		}
	};
	
	public void timeNext(int now) {
		//1 Update the market situation.
		market.timeNext(now);
		//2 Check demand, and if new demand happen, make a new contract, schedule to ship.
		if(market.checkDemand()){
			market.addContract(fleet,portNetwork);
		}
		//3 Update ships' situation.
		fleet.timeNext(now);
		//4 Update ports' situation
		portNetwork.timeNext(now);
		
		
	}
	
	
	//Getter and Setter
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	

}
