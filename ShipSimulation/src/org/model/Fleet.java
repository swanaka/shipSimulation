package org.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Fleet {
	
	private List<Ship> ships;
	private int fleetNum;
	
	public Fleet(){
		ships = new ArrayList<Ship>();
		fleetNum = 0;
	}
	public void timeNext(){
		for (Ship ship : ships){
			ship.timeNext();
		}
	}
	public void add(Ship ship){
		ships.add(ship);
		this.fleetNum ++;
	}
	
	public List<Ship> getShips(){
		return ships;
	}

}
