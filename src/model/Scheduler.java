package model;

import java.util.ArrayList;

/**
 * Super class of all CPU Scheduling algorithms with common variables and
 * methods. This is an abstract class and should be used accordingly.
 * 
 * @author Peter Vukas
 */
public abstract class Scheduler implements CalcAverages {
	protected ArrayList<Process> processes;
	protected double averageWaitTime;
	protected double averageTurnAroundTime;

	/**
	 * Initializes the process list
	 */
	Scheduler() {
		processes = new ArrayList<Process>();
		averageWaitTime = -1;
		averageTurnAroundTime = -1;
	}

	/**
	 * Populates the process list with auto generated processes.
	 * 
	 * @param size
	 *            An integer > 0 representing the amount of generated processes
	 *            desired. If this value is <= 0, it will be replaced with 10.
	 */
	public abstract void populateProcessList(int size);

	/**
	 * Should be filled with the logic to determine process wait/turn around times
	 */
	public abstract void run();

	/**
	 * @return Average wait time if it's been calculated. Otherwise, it will return
	 *         a -1 if a successful calculation did not occur.
	 */
	public double getAverageWaitTime() {
		return averageWaitTime;
	}

	/**
	 * @return Average turn around time if it's been calculated. Otherwise, it will
	 *         return a -1 if a successful calculation did not occur.
	 */
	public double getAverageTurnAroundTime() {
		return averageTurnAroundTime;
	}

	/**
	 * Calculates the average wait and turn around of all processes
	 * 
	 * @return True if the averages were calculated successfully, false if it was
	 *         unable to be calculated.
	 */
	protected boolean averageCalc() {
		try {
			averageWaitTime = averageWait(processes);
			averageTurnAroundTime = averageTurnAround(processes);
		} catch (InvalidTimeException e) {
			averageWaitTime = -1;
			averageTurnAroundTime = -1;
			return false;
		}
		return true;
	}
}
