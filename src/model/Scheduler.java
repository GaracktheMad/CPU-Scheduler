package model;

import java.util.ArrayList;

import view.Gantt;

/**
 * * Super class of all CPU Scheduling algorithms with common variables and
 * methods. This is an abstract class and should be used accordingly.
 * 
 * @author Peter Vukas
 * @param <N>
 *            A subclass of process which is used by this algorithm
 */
public abstract class Scheduler<N extends Process> implements CalcAverages<N> {
	/**
	 * An array list of all processes to be considered by this algorithm
	 */
	protected ArrayList<N> processes;
	/**
	 * The sum of wait times for all processes in the array list / the length of the
	 * array list
	 */
	protected double averageWaitTime;
	/**
	 * The sum of turn around times for all processes in the array list / the length
	 * of the array list
	 */
	protected double averageTurnAroundTime;
	/**
	 * A gantt chart generated during run()
	 */
	protected Gantt gantt;

	/**
	 * Initializes the process list
	 */
	Scheduler() {
		averageWaitTime = -1;
		averageTurnAroundTime = -1;
		gantt = new Gantt();
	}

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
	 * Removes ALL processes from the current list. This cannot be undone.
	 */
	public void clearProcesses() {
		processes.clear();
	}
	
	/**
	 * Access the gantt chart
	 */
	public Gantt getGantt() {
		return gantt;
	}

	/**
	 * @return A deep copied list of all processes which can be changed without
	 *         issue to this scheduler algorithm
	 */
	public abstract ArrayList<N> getProcesses();

	/**
	 * @param p
	 *            A Process to be added to the list of processes to run
	 */
	public abstract void addProcess(N p);

	/**
	 * @param p
	 *            The process to be removed from the list of processes to run
	 * @return True if the process was found and removed.
	 */
	public abstract boolean removeProcess(N p);

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
	 * 
	 * @return An array list with the order of processes running
	 */
	public abstract ArrayList<N> run();
}
