package org.simulation;

public abstract class Simulation {
	//Configuration
	private int startTime;
	private int endTime;
	//Status
	public int time;
	
	//Function
	public abstract void save();
	public abstract void timeNext();
	public void execute(){
		while(time == endTime){
			timeNext();
			save();
			time++;
		}
	};
	
	
	//Getter and Setter
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

	

}
