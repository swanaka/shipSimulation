package org.model;

import org.util.Location;

public abstract class Ship {
	//Congiguration of ship
	protected Hull hull;
	protected Engine engine;
	protected FuelTank fuelTank;
	protected Propeller propeller;
	protected ShipSchedule schedule;
	protected ShipOperator owner;
	protected String name;

	//Status of ship
	protected Location loc;
	protected double amountOfFuel;
	protected double rationOfAccident;
	protected double amountOfCargo;
	protected double remainingDistance;
	protected double cashFlow;
	protected double emssionedGas;
	protected Status status;
	protected int waitingTime;
	
	//Function
	public abstract void timeNext();
	public abstract void transport();

	//Getter and Setter
	public double getRemainingDistance() {
		return remainingDistance;
	}
	public void setRemainingDistance(double remainingDistance) {
		this.remainingDistance = remainingDistance;
	}
	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public double getAmountOfFuel() {
		return amountOfFuel;
	}

	public void setAmountOfFuel(double amountOfFuel) {
		this.amountOfFuel = amountOfFuel;
	}

	public double getRationOfAccident() {
		return rationOfAccident;
	}

	public void setRationOfAccident(double rationOfAccident) {
		this.rationOfAccident = rationOfAccident;
	}

	public double getAmountOfCargo() {
		return amountOfCargo;
	}

	public void setAmountOfCargo(double amountOfCargo) {
		this.amountOfCargo = amountOfCargo;
	}

	public Hull getHull() {
		return hull;
	}

	public void setHull(Hull hull) {
		this.hull = hull;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public FuelTank getFuelTank() {
		return fuelTank;
	}

	public void setFuelTank(FuelTank fuelTank) {
		this.fuelTank = fuelTank;
	}

	public Propeller getPropeller() {
		return propeller;
	}

	public void setPropeller(Propeller propeller) {
		this.propeller = propeller;
	}

	public ShipSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(ShipSchedule shipSchedule) {
		this.schedule = shipSchedule;
	}

	public ShipOperator getOwner() {
		return owner;
	}
	public void setOwner(ShipOperator owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	//Abstract inner class
	public abstract class Hull{

		public abstract double calcEHP(double v);
	}

	public abstract class Engine{
		public abstract double calcFOC(double v);
	}

	public abstract class FuelTank{
	}

	public abstract class Propeller{
		public abstract double calcBHP(double v);
	}

	public abstract class ShipSchedule{
		public abstract void add(int startTime, int endTime, Port departure, Port destination);
		public abstract void pop();
		public abstract int getPlannedTime();
	}

	public enum Status{
		TRANSPORT,
		WAIT,
		BERTH,
		LOADING,
		UNLOADING,
		BUNKERING;
	}


}
