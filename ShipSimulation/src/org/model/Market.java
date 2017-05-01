package org.model;

import java.util.ArrayList;
import java.util.List;

import org.model.Status.FuelType;
import org.model.Status.LoadingType;

public abstract class Market {
	protected List<FuelPrice> fuels;
	protected List<Demand> demands;
	
	public Market(){
		fuels = new ArrayList<FuelPrice>();
		demands = new ArrayList<Demand>();
	}
	public void addFuelPrice(FuelPrice fuelPrice){
		fuels.add(fuelPrice);
	}
	public void addDemand(Demand demand){
		demands.add(demand);
	}
	public void timeNext(int now){
		for(FuelPrice fuelPrice : fuels){
			fuelPrice.timeNext(now);
		}
		for (Demand demand : demands){
			demand.timeNext(now);
		}
		
	}
	
	public abstract boolean checkDemand();
	public abstract void addContract(Fleet fleet, PortNetwork portNetwork);
	
}
