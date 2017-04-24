package org.model;

import org.util.Location;

public abstract class Ship {
	//Congiguration of ship
	private Hull hull;
	private Engine engine;
	private FuelTank fuelTank;
	private Propeller propeller;
	private ShipSchedule shipSchedule;
	private ShipOperator owner;
	private String name;

	//Status of ship
	private Location loc;
	private double amountOfFuel;
	private double rationOfAccident;
	private double amountOfCargo;
	private double remainingDistance;
	
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

	public ShipSchedule getShipSchedule() {
		return shipSchedule;
	}

	public void setShipSchedule(ShipSchedule shipSchedule) {
		this.shipSchedule = shipSchedule;
	}

	public ShipOperator getOwner() {
		return owner;
	}
	public void setOwner(ShipOperator owner) {
		this.owner = owner;
	}


	//Abstract class
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
	}


}
