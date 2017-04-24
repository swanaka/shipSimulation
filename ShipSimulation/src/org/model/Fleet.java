package org.model;

public abstract class Fleet {
	
	private Ship[] ships;
	private int maxFleetNum;
	private int fleetNum;
	
	public Fleet(int maxFleetNum){
		ships = new Ship[maxFleetNum];
		this.maxFleetNum = maxFleetNum;
		fleetNum = 0;
	}
	public void timeNext(){
		for (Ship ship : ships){
			ship.timeNext();
		}
	}
	public void add(Ship ship){
		if (fleetNum < maxFleetNum){
			ships[fleetNum] = ship;
		}else{
			//TO-DO: Expand array size and add.
			System.out.println("Too much!");
		}
	}

}
