package org.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.model.Status.LoadingType;
import org.simulation.Simulation;

public class SimpleMarket extends Market {

	@Override
	public List<Demand> checkDemand() {
		List<Demand> returnList = new ArrayList<Demand>();
		for (Demand demand : super.demands){
			if (demand.isDemand()) returnList.add(demand);
		}
		
		if (returnList.size() == 0) return null;
		else return returnList;
	}

	@Override
	public void addContract(Fleet fleet, PortNetwork portNetwork) {
		
		for(Demand demand : super.demands){
			//1.まずはdemandをほどく
			int startTime = demand.getStartTime();
			int endTime = demand.getEndTime();
			LoadingType cargoType = demand.getCargoType();
			String departure = demand.getDeparture();
			String destination = demand.getDestination();
			
			//2. 間に合う船がいるかどうか調べる
			//3. 運賃を決める
			//4. 
			
		}

	}
	
	public class ContainerDemand extends Demand{
		
		private int counter;
		private int limit;
		
		public ContainerDemand(){
			super();
			setCargoType(LoadingType.Container);
			this.counter = 0;
			this.limit = 30;
		}

		@Override
		public void timeNext() {
			if (counter > this.limit){
				super.setAmountOfCargo(6600);
				super.setStartTime(Simulation.getCurrentTime());
				super.setEndTime(Simulation.getCurrentTime()+720);
				super.setDeparture("Japan");
				super.setDestination("Los Angels");
				counter = 0;
			}
			counter ++;
			
		}

		@Override
		public boolean isDemand() {
			if (demandList.size() == 0) return false;
			else return false;
		}
		
	}
	
	public class OilPrice extends FuelPrice{
		private double upFactor;
		private double downFactor;
		private double upProbability;

		@Override
		public void timeNext() {
			double n = Math.random();
			if (n >= upProbability){
				setPrice(super.price * upFactor);
			}else{
				setPrice(super.price * downFactor);
			}
			
		}
		
	}

}
