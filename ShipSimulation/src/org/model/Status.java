package org.model;

public class Status {
	public enum ShipStatus{
		TRANSPORT,
		WAIT,
		BERTH,
	}

	public enum BunkeringStatus{
		YES,
		NO,
	}

	public enum LoadingStatus{
		LOADING,
		UNLOADING,
		NO,
	}
	public enum MaintenanceStatus{
		YES,
		NO,
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
