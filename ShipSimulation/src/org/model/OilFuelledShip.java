package org.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simulation.Simulation;

public class OilFuelledShip extends Ship {
	
	//kinetic viscous coefficient
	private final double mu = 1.4267E-06;
	private final double rho = 101.9515;
	
	public OilFuelledShip(double c0, double c1, double c2, double l, double k, double s,
			double etaH, double etaR, double eta0, double etaTau, double etaM,
			double maxBHP, double SFOC0, double SFOC1, double SFOC2){
		Hull hull = new OHull(c0,c1,c2,l,k,s);
		setHull(hull);
		
		Propeller propeller = new OPropeller(etaH,etaR,eta0,etaTau,etaM);
		setPropeller(propeller);
		
		Engine engine = new OEngine(maxBHP,SFOC0,SFOC1,SFOC2);
		setEngine(engine);

		super.status = Status.TRANSPORT;
	}
	
	public OilFuelledShip(HashMap<String, String> Param) {
		double c0 = Double.parseDouble(Param.get("c0"));
		double c1 = Double.parseDouble(Param.get("c1"));
		double c2 = Double.parseDouble(Param.get("c2"));
		double l = Double.parseDouble(Param.get("l"));
		double k = Double.parseDouble(Param.get("k"));
		double s = Double.parseDouble(Param.get("s"));
		double etaH = Double.parseDouble(Param.get("etaH"));
		double etaR = Double.parseDouble(Param.get("etaR"));
		double eta0 = Double.parseDouble(Param.get("eta0"));
		double etaTau = Double.parseDouble(Param.get("etaTau"));
		double etaM = Double.parseDouble(Param.get("etaM"));
		double maxBHP = Double.parseDouble(Param.get("maxBHP"));
		double SFOC0 = Double.parseDouble(Param.get("SFOC0"));
		double SFOC1 = Double.parseDouble(Param.get("SFOC1"));
		double SFOC2 = Double.parseDouble(Param.get("SFOC2"));

		Hull hull = new OHull(c0,c1,c2,l,k,s);
		setHull(hull);
		
		Propeller propeller = new OPropeller(etaH,etaR,eta0,etaTau,etaM);
		setPropeller(propeller);
		
		Engine engine = new OEngine(maxBHP,SFOC0,SFOC1,SFOC2);
		setEngine(engine);

		super.status = Status.TRANSPORT;

	}
	
	@Override
	public void timeNext() {
		
		switch(super.status){
			case TRANSPORT:
				transport();
			case WAIT:
				super.waitingTime ++;
			case BERTH:
			case LOADING:
			case UNLOADING:
			case BUNKERING:
		}

	}

	@Override
	public void transport() {

		// 1. Get planned distance
		int now = Simulation.time;
		int plannedTime = super.schedule.getPlannedTime();
		double distance = super.remainingDistance; 
		double plannedDistance = calcPlannedDistance(now, plannedTime, distance);

		// 2. Calculate ship speed
		double speed = plannedDistance;

		// 3. Calculate FOC
		double foc = super.engine.calcFOC(speed);

		// 4. Update remainng distance, fuel, gas emission
		// TO-DO update Location
		double actualDis = calcActualDistance(distance);
		super.remainingDistance -= actualDis;
		super.amountOfFuel -= foc;
		super.emssionedGas += calcGasEmission(foc);

	}

	private double calcActualDistance(double distance){
		return distance;
	}

	private double calcPlannedDistance(int now, int plannedTime, double distance){
		return distance / (plannedTime - now);
	}

	private double calcGasEmission(double foc){
		return foc;
	}
	
	private class OHull extends Hull{
		private double c0;
		private double c1;
		private double c2;
		private double l;
		private double k;
		private double s;
		
		private OHull(double c0, double c1, double c2, double l, double k, double s){
			this.c0 = c0;
			this.c1 = c1;
			this.c2 = c2;
			this.l = l;
			this.k = k;
			this.s = s;
		}
		
		private double calcCw(double v){
			return c0 + c1*v + c2*v;
		}
		
		private double calcCf(double v){
			double re = v * l / mu;
			double cf = 0.463*Math.pow((Math.log10(re)),-2.6);
			return cf;
		}

		private double calcCt(double v){
			return calcCw(v) + (1+k) * calcCf(v);
		}

		public double calcEHP(double v){
			double rt = 0.5 * rho * calcCt(v) * Math.pow(v,2) * s;
			return rt * v / 1000;
		}
		
	}

	private class OPropeller extends Propeller{
		private double etaH;
		private double etaR;
		private double eta0;
		private double etaTau;
		private double etaM;

		private OPropeller(double etaH, double etaR, double eta0, double etaTau, double etaM){
			this.etaH = etaH;
			this.etaR = etaR;
			this.eta0 = eta0;
			this.etaTau = etaTau;
			this.etaM = etaM;
		}

		public double calcBHP(double v){
			return getHull().calcEHP(v) / (etaH * etaR * eta0 * etaTau * etaM);
		}
	}
	
	private class OEngine extends Engine{
		private double maxBHP;
		private double SFOC0;
		private double SFOC1;
		private double SFOC2;
	
		private OEngine(double maxBHP, double SFOC0, double SFOC1, double SFOC2){
			this.maxBHP = maxBHP;
			this.SFOC0 = SFOC0;
			this.SFOC1 = SFOC1;
			this.SFOC2 = SFOC2;
		}
		
		private double calcSFOC(double v) {
			double load = getPropeller().calcBHP(v) / maxBHP;
			return SFOC0 * SFOC1 * load * SFOC2 * Math.pow(load, 2); 
		}
		
		public double calcFOC(double v){
			return calcSFOC(v) * getPropeller().calcBHP(v) / 1000;
		}
		
	
	}

	public class OSchedule extends ShipSchedule{
		private List<HashMap<String,Object>> scheduleList;

		private OSchedule(int startTime, int endTime, Port departure, Port destination){
			scheduleList = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> schedule = new HashMap<String, Object>();
			schedule.put("startTime", startTime);
			schedule.put("endTime", endTime);
			schedule.put("departure", departure);
			schedule.put("destination", destination);
			scheduleList.add(schedule);

		}
		
		@Override
		public void add(int startTime, int endTime, Port departure, Port destination){

			HashMap<String, Object> schedule = new HashMap<String, Object>();
			schedule.put("startTime", startTime);
			schedule.put("endTime", endTime);
			schedule.put("departure", departure);
			schedule.put("destination", destination);
			this.scheduleList.add(schedule);
		}
		
		@Override
		public void pop(){
			this.scheduleList.remove(0);
		}
		
		@Override
		public int getPlannedTime() {
			return (int) scheduleList.get(0).get("endTime");
		}
	}


}
