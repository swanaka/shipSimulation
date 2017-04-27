package org.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.model.Status.FuelType;
import org.model.Status.LoadingType;
import org.util.Location;

public abstract class Port {
	
	//Configuration 
	private int capacity;
	protected List<PortFacility> facilities;
	private Location loc;
	protected PortOperator operator;

	//Status
	//	List of ships whose destination or location is this ports.
	private List<Ship> waitingShips;
	protected String name;

	//Function
	public abstract void loading();
	public abstract void unloading(Ship ship);
	public abstract void maintenance();
	public abstract PortFacility checkBerthing(Ship ship);

	public abstract void addPortFacility(HashMap<String, String> param);
	public abstract void addPortFacilities(HashMap<String, String> param, int num);
	public abstract void addPortFacilities(HashMap<String, String>[] params);
	public void gatewayForBerth(){
		for (Ship ship : waitingShips){
			PortFacility port = checkBerthing(ship);
			if(!(port == null)){
				port.accept(ship);
			}
		}
	};
	
	public Port(String name){
		this.name = name;
		waitingShips = new ArrayList<Ship>();
		facilities = new ArrayList<PortFacility>();
	}

	public int getCapacity() {
		return capacity;
	}

	public PortOperator getOperator(){
		return this.operator;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName(){
		return this.name;
	}

	public Location getLoc() {
		return loc;
	}


	public void setLoc(Location loc) {
		this.loc = loc;
	}

	protected abstract class PortFacility{
		protected Ship berthingShip;
		protected FuelType fuelType;
		protected LoadingType loadingType;
		protected int occupiedFlag;
		protected double bunkeringCapacity;
		protected double loadingCapacity;
		

		public abstract void accept(Ship ship);
		public abstract void berthing();
		public abstract void loading();
		public abstract void unloading();
		public abstract void bunkering();
		public abstract void maintenance();
		public abstract boolean match(Ship ship);

	}

}
