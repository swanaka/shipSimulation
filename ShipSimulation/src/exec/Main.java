package exec;

import org.model.Fleet;
import org.model.Market;
import org.model.OilFuelledShip;
import org.model.PortNetwork;
import org.model.Ship;
import org.model.SimpleFleet;
import org.simulation.SimpleSimulation;
import org.simulation.Simulation;
import org.util.CSVReader;

public class Main {

	public static void main(String[] args){
		
		Fleet fleet = loadInitialFleet();
		//PortNetwork ports= loadInitialPorts();
		//Market market = loadMarketInfo();
		
		//Simulation simulation = new SimpleSimulation(fleet, ports, market, 365);
		
	}
	
	private static Fleet loadInitialFleet(){
		Fleet fleet = new SimpleFleet(1);
		Ship ship = new OilFuelledShip(CSVReader.forParam("../../data/ship_config.csv"));
		fleet.add(ship);
		return fleet;
	}
	
	
}
