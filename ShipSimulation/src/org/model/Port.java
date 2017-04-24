package org.model;

import org.util.Location;

public abstract class Port {
	
	//Configuration 
	private int capacity;
	private BunkeringFacility bunkeringFacility;
	private Location loc;

	//Status
	private int occupied;

	//Function
	public abstract void timeNext();
	public abstract void Berthing();
	public abstract void Loading();
	public abstract void Unloading();
	public abstract void Maintenance();


	public int getOccupied() {
		return occupied;
	}


	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public BunkeringFacility getBunkeringFacility() {
		return bunkeringFacility;
	}


	public void setBunkeringFacility(BunkeringFacility bunkeringFacility) {
		this.bunkeringFacility = bunkeringFacility;
	}


	public Location getLoc() {
		return loc;
	}


	public void setLoc(Location loc) {
		this.loc = loc;
	}


	private abstract class BunkeringFacility{}

}
