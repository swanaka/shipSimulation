package org.model;

import java.util.HashMap;

import org.model.Status.FuelType;
import org.model.Status.LoadingType;

public class SimplePort extends Port {
	
	public SimplePort(HashMap<String, String> param, int num){
		super();
		for (int i=0;i<num;i++){
			createPortFacility(param);
		}
	}
	
	public SimplePort(HashMap<String, String>[] paramList){
		super();
		for (HashMap<String, String> param : paramList){
			createPortFacility(param);
		}
	}
	
	private void createPortFacility(HashMap<String, String> param){
		FuelType fuelType = FuelType.valueOf(param.get("FuelType"));
		LoadingType loadingType = LoadingType.valueOf(param.get("LoadingType"));
		double bunkeringCapacity = Double.parseDouble(param.get("BunkeringCapacity"));
		double loadingCapacity = Double.parseDouble(param.get("LoadingCapacity"));
		PortFacility port = new SimplePortFacitliy(fuelType, loadingType, bunkeringCapacity, loadingCapacity);
		super.facilities.add(port);
	}

	@Override
	public void timeNext() {
		// 1. Check the occupied and # of waiting ship and If it is available, change the ship's status => BERTH
		// 2. BERTH, BUNKERING, LOADING, UNLOADING, Port provide service,
		// 3. Check the ship's status and update ships' status and port status

	}


	@Override
	public void loading(Ship ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unloading(Ship ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void maintenance(Ship ship) {
		// TODO Auto-generated method stub

	}


	@Override
	public void gatewayForBerth() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void checkShipStatus(Ship ship) {
		// TODO Auto-generated method stub
		
	}

	//InnerClass
	private class SimplePortFacitliy extends PortFacility{

		public SimplePortFacitliy(FuelType fuelType, LoadingType loadingType, double bunkeringCapacity, double loadingCapacity){
			super.occupiedFlag = 0;
			super.bunkeringCapacity = bunkeringCapacity;
			super.fuelType = fuelType;
			super.loadingType = loadingType;
			super.loadingCapacity = loadingCapacity;
		}

	}

}
