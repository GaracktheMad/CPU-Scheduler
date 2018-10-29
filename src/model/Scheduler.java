package model;

import java.util.ArrayList;

/**
 * Super class of all CPU Scheduling algorithms with common variables and
 * methods. This is an abstract class and should be used accordingly.
 * 
 * @author Peter Vukas
 */
public abstract class Scheduler<N extends Process> implements CalcAverages<N> {
	protected ArrayList<N> processes;
	protected double averageWaitTime;
	protected double averageTurnAroundTime;

	/**
	 * Initializes the process list
	 */
	Scheduler() {
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
	 * The algorithm which determines the order in which processes are run
	 * @return An array list with the order of processes running
	 */
	public abstract ArrayList<N> run();

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
	
	/**
	 * @return A deep copied list of all processes
	 */
	public abstract ArrayList<N> getProcesses();
	/**
	 * @param ap A Process to be added to the list of processes to run
	 */
	public abstract void addProcess(N p);
	/**
	 * @param ap The process to be removed from the list of processes to run
	 * @return True if the process was found and removed.
	 */
	public abstract boolean removeProcess(N p);
}
