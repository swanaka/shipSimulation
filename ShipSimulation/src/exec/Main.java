package exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.model.Demand;
import org.model.Fleet;
import org.model.Market;
import org.model.FuelPrice;
import org.model.OilPrice;
import org.model.Port;
import org.model.SimpleShip;
import org.model.Status.FuelType;
import org.model.Status.LoadingType;
import org.model.PortNetwork;
import org.model.Ship;
import org.model.SimpleDemand;
import org.model.SimpleFleet;
import org.model.SimpleMarket;
import org.model.SimplePort;
import org.model.SimplePortNetwork;
import org.simulation.SimpleSimulation;
import org.simulation.Simulation;
import org.util.CSVReader;

public class Main {

	public static void main(String[] args){
		
		Fleet fleet = loadInitialFleet("../../data/ship_config.csv");
		PortNetwork ports= loadInitialPorts("../../data/port_config.csv");
		Market market = loadMarketInfo("../../data/market_config.csv");
		
		Simulation simulation = new SimpleSimulation(fleet, ports, market, 365);
		
	}
	
	private static Fleet loadInitialFleet(String filePath){
		Fleet fleet = new SimpleFleet();
		List<String[]> data =CSVReader.forGeneral(filePath);
		double speed = 28;
		LoadingType cargoType = LoadingType.HFO;
		double cargoAmount = 300000;
		double foc = 1.24;
		double fuelCapacity = 5000;
		FuelType fuelType = FuelType.OIL;
		
		Ship ship = new SimpleShip(speed, cargoType, cargoAmount, foc, fuelCapacity, fuelType);
		fleet.add(ship);
		return fleet;
	}
	
	private static PortNetwork loadInitialPorts(String configFilePath){
		List<String[]> data = CSVReader.forGeneral(configFilePath);
		List<Port> ports = new ArrayList<Port>();
		double[][] routeMatrix = null;
		int portCount = 0;
		for (int i=0;i<data.size();i++){
			if (data.get(i)[0].equals( "PortName")){
				String name = data.get(i+1)[0];
				String fuelType = data.get(i+3)[0];
				String loadingType = data.get(i+3)[1];
				String bunkeringCapacity = data.get(i+3)[2];
				String loadingCapacity = data.get(i+3)[3];
				int numOfPorts = Integer.parseInt(data.get(i+4)[0]);
				HashMap<String, String> param = new HashMap<String, String>();
				param.put("FuelType", fuelType);
				param.put("LoadingType", loadingType);
				param.put("BunkeringCapacity", bunkeringCapacity);
				param.put("LoadingCapacity", loadingCapacity);
				Port port = new SimplePort(name);
				port.addPortFacilities(param,numOfPorts);
				i = i + 5;
				portCount ++;
			}
			if (data.get(i)[0].equals("RouteMatrix")){
				routeMatrix = new double[portCount][portCount];
				for (int j=1;j<1+portCount;j++){
					for(int k=1;k<1+portCount;k++){
						routeMatrix[j-1][k-1] = Double.parseDouble(data.get(i+1+j)[k]);
					}	
				}
			}
		}
		PortNetwork portNetwork = new SimplePortNetwork(ports,routeMatrix);
		return portNetwork;
	}
	
	private static Market loadMarketInfo(String filePath){
		Market market = new SimpleMarket();
		List<String[]> data = CSVReader.forGeneral(filePath);
		double upFactor = 0;
		double downFactor = 0;
		double probability = 0;
		FuelPrice oilprice = new OilPrice(upFactor,downFactor,probability);
		
		Demand demand = new SimpleDemand();
		market.addDemand(demand);
		market.addFuelPrice(oilprice);
		
		return market;
	}
	
	
}
