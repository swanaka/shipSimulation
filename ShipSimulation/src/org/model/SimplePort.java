package org.model;

import java.util.HashMap;

import org.model.Status.FuelType;
import org.model.Status.LoadingType;
import org.model.Status.ShipStatus;

public class SimplePort extends Port {
	
	public SimplePort(String name){
		super(name);
	}
	
	
	@Override
	public void addPortFacility(HashMap<String, String> param){
		String name = param.get("Name");
		super.name = name;
		FuelType fuelType = FuelType.valueOf(param.get("FuelType"));
		LoadingType loadingType = LoadingType.valueOf(param.get("LoadingType"));
		double bunkeringCapacity = Double.parseDouble(param.get("BunkeringCapacity"));
		double loadingCapacity = Double.parseDouble(param.get("LoadingCapacity"));
		double berthingFee = Double.parseDouble(param.get("BerthinFee"));
		PortFacility facility = new SimplePortFacitliy(fuelType, loadingType, bunkeringCapacity, loadingCapacity, berthingFee);
		super.facilities.add(facility);
	}
	
	@Override
	public void addPortFacilities(HashMap<String, String>param, int num){
		for (int i=0;i<num;i++){
			addPortFacility(param);
		}
	}
	
	@Override
	public void addPortFacilities(HashMap<String, String>[] paramList){
		for (HashMap<String, String> param : paramList){
			addPortFacility(param);
		}
	}

	@Override
	public void loading() {
		for (PortFacility facility : super.facilities){
			facility.loading();
		}

	}

	@Override
	public void unloading(Ship ship) {
		for (PortFacility facility : super.facilities){
			facility.unloading();
		}

	}

	@Override
	public void maintenance() {
		for (PortFacility facility : super.facilities){
			facility.maintenance();
		}

	}




	@Override
	public PortFacility checkBerthing(Ship ship) {
		for (PortFacility facility : super.facilities){
			if(facility.match(ship)){
				return facility;
			}
		}
		return null;
	}

	//InnerClass
	private class SimplePortFacitliy extends PortFacility{
		
		private double berthingFee;

		public SimplePortFacitliy(FuelType fuelType, LoadingType loadingType, double bunkeringCapacity, double loadingCapacity, 
				double berthingFee){
			super.occupiedFlag = 0;
			super.bunkeringCapacity = bunkeringCapacity;
			super.fuelType = fuelType;
			super.loadingType = loadingType;
			super.loadingCapacity = loadingCapacity;
			this.berthingFee = berthingFee;
		}

		public void accept(Ship ship,int now){
			super.berthingShip = ship;
			ship.setShipStatus(ShipStatus.BERTH);
			ship.appropriateRevenue(now);
		}
		public void berthing(){
			loading();
			unloading();
			bunkering();
			maintenance();
			berthingShip.owner.addCashFlow(-1*this.berthingFee);
			getOperator().addCashFlow(this.berthingFee);
		}
		public void loading(){
			switch(super.berthingShip.lStatus){
			case LOADING:
				super.berthingShip.setAmountOfCargo(super.berthingShip.getAmountOfCargo() + loadingCapacity);
			default:;
			}
		}

		public void unloading(){
			switch(super.berthingShip.lStatus){
			case LOADING:
				super.berthingShip.setAmountOfCargo(super.berthingShip.getAmountOfCargo() -  loadingCapacity);
			default:;
			}
		}
		
		public void maintenance(){
			switch(super.berthingShip.mStatus){
			case YES:
				//TO-DO maintenance
			default:;
			}
		}

		public boolean match(Ship ship){
			if (super.occupiedFlag == 1) return false;
			if (super.fuelType != ship.getFuelType()) return false;
			if (super.loadingType != ship.getCargoType()) return false;
			return true;
		}

		@Override
		public void bunkering() {
			switch(super.berthingShip.bStatus){
			case YES:
				super.berthingShip.setAmountOfFuel(super.berthingShip.getAmountOfFuel() + bunkeringCapacity);
			default:;
			}
			
		}

	}

	@Override
	public int getTimeForReady(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

}
