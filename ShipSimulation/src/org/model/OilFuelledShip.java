package org.model;

import java.util.HashMap;

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

	}
	@Override
	public void timeNext() {
		// TODO Auto-generated method stub

	}

	@Override
	public void transport() {
		// TODO Auto-generated method stub

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

}
