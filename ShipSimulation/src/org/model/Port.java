package org.model;

import java.util.ArrayList;
import java.util.List;

import org.model.Status.FuelType;
import org.model.Status.LoadingType;
import org.util.Location;

public abstract class Port {
	
	//Configuration 
	private int capacity;
	protected List<PortFacility> facilities;
	private Location loc;

	//Status
	//	List of ships whose destination or location is this ports.
	private List<Ship> shipList;

	//Function
	public abstract void timeNext();
	public abstract void gatewayForBerth();
	public abstract void loading(Ship ship);
	public abstract void unloading(Ship ship);
	public abstract void maintenance(Ship ship);
	public abstract void checkShipStatus(Ship ship);
	
	public Port(){
		shipList = new ArrayList<Ship>();
		facilities = new ArrayList<PortFacility>();
	}

	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public Location getLoc() {
		return loc;
	}


	public void setLoc(Location loc) {
		this.loc = loc;
	}

	protected abstract class PortFacility{
		protected FuelType fuelType;
		protected LoadingType loadingType;
		protected int occupiedFlag;
		protected double bunkeringCapacity;
		protected double loadingCapacity;

	}

}
