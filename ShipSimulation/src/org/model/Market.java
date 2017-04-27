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
	public void timeNext(){
		for(FuelPrice fuelPrice : fuels){
			fuelPrice.timeNext();
		}
		for (Demand demand : demands){
			demand.timeNext();
		}
		
	}
	
	public abstract List<Demand> checkDemand();
	public abstract void addContract(Fleet fleet, PortNetwork portNetwork);
	
	public abstract class Demand {
		private LoadingType cargoType;
		private int startTime;
		private int endTime;
		private double amountOfCargo;
		private String departure;
		private String destination;
		private boolean isdemand;
		
		public abstract void timeNext();
		public boolean isDemand(){
			return isdemand;
		};

		public LoadingType getCargoType() {
			return cargoType;
		}

		public void setCargoType(LoadingType cargoType) {
			this.cargoType = cargoType;
		}
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
		public double getAmountOfCargo() {
			return amountOfCargo;
		}
		public void setAmountOfCargo(double amountOfCargo) {
			this.amountOfCargo = amountOfCargo;
		}
		public String getDeparture() {
			return departure;
		}
		public void setDeparture(String departure) {
			this.departure = departure;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		
		
		
	}
	
	public abstract class FuelPrice {
		protected FuelType fuelType;
		protected double price;
		
		public abstract void timeNext();
		
		public FuelType getFuelType() {
			return fuelType;
		}
		public void setFuelType(FuelType fuelType) {
			this.fuelType = fuelType;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		
	}
	
	public abstract class Freight {
		
	}

}
