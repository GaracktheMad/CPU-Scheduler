package model;

/**
 * A class that represents a section of a process' runtime Solely for the
 * construction of a Gantt chart
 * 
 * @author Brandon Ruiz
 * 
 */
public class GanttSection {

	/**
	 * Process name
	 */
	private String n;
	/**
	 * The start time of a period in which a process is processed
	 */
	/**
	 * The end time of a period in which a process is processed
	 */
	private double startTime, endTime;

	/**
	 * @param n
	 *            Process name
	 * @param startTime
	 *            Process start time
	 */
	public GanttSection(String n, double startTime) {
		this.n = n;
		this.startTime = startTime;
		endTime = startTime;
	}

	/**
	 * @return String value of the name
	 */
	public String getName() {
		return n;
	}

	/**
	 * @return Value of the start time
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * @return Value of the end time
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time of a process; for when it is swapped or terminated
	 * 
	 * @param endTime
	 *            Value of the end time to be set
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

}
