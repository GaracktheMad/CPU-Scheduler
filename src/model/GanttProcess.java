package model;

/**
 * A class that represents a section of a process' runtime
 * Solely for the construction of a Gantt chart
 * 
 * @author Brandon Ruiz
 * 
 */

public class GanttProcess {
	
	private String n;	//Process name
	private int startTime, endTime;	//The start and end times of a period in which a process is processed
	
	public GanttProcess(String n, int startTime) {
		this.n = n;
		this.startTime = startTime;
		endTime = startTime;
	}
	
	public String getName() {
		return n;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	//Sets the end time of a process; for when it is swapped or terminated
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
