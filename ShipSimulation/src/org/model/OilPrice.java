package org.model;


/**
 * OilPrice model which is expressed by binomial model
 * @author Shinnosuke Wanaka
 * 
 */
public class OilPrice extends FuelPrice{
	private double upFactor;
	private double downFactor;
	private double upProbability;

	@Override
	public void timeNext(int now) {
		double n = Math.random();
		if (n >= upProbability){
			setPrice(super.price * upFactor);
		}else{
			setPrice(super.price * downFactor);
		}
		
	}


	public OilPrice(double upFactor, double downFactor, double upProbability){
		super();
		this.upFactor = upFactor;
		this.downFactor = downFactor;
		this.upProbability = upProbability;
		
	}

}