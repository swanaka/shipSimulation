package org.simulation;

import java.util.List;

import org.model.Fleet;
import org.model.Market;
import org.model.Port;
import org.model.PortNetwork;
import org.model.Ship;

public class SimpleSimulation extends Simulation{

	public SimpleSimulation(int endTime) {
		super(endTime);
	}

	@Override
	public void save(int now) {
		//TO-DO
		List<Ship> ships = Fleet.getShips();
		for(Ship ship : ships){
			ship.getAmountOfCargo();
			ship.getAmountOfFuel();
			ship.getRemainingDistance();
			ship.getCargoType();
			ship.getShipStatus().name();
		}
		//this.portNetwork.save(now);
		double freight = Market.getFreight().get(0).getPrice();
		double fuelPrice = Market.getFuels().get(0).getPrice();
		System.out.println("Now: " + now + " Saved!");
		
	}

}
