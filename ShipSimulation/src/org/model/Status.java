package org.model;

public class Status {
	public enum ShipStatus{
		TRANSPORT,
		WAIT,
		BERTH,
		BERTH_LOADING_BUNKERING,
		BERTH_UNLOADING_BUNKERING,
		BERTH_LOADING,
		BERTH_UNLOADING,
		BERTH_BUNKERING,
	}
	
	public enum FuelType{
		OIL("Oil"), 
		LNG("LNG"), 
		METHANOL("METHANOL");
		
		private final String name;
		private FuelType(final String name){
			this.name = name;
		}
	}
	
	public enum LoadingType{Container,}
}
