package model;

import java.util.ArrayList;

/**
 * Methods to calculate the average wait and turn around times of multiple
 * processes
 * 
 * @author Peter Vukas
 */
public interface CalcAverages {

	/**
	 * @param alp
	 *            An array list of all processes to be included in the average
	 * @return A double value of the average wait time
	 * @throws InvalidTimeException
	 *             Thrown when a process is found without a wait time
	 */
	public default double averageWait(ArrayList<Process> alp) throws InvalidTimeException {
		double average = 0;
		for (Process p : alp) {
			if (p.isWaitTimeAssigned() == false) {
				throw new InvalidTimeException();
			}
			average += p.getWaitTime();
		}
		return average / (double) (alp.size());
	}

	/**
	 * @param alp
	 *            An array list of all processes to be included in the average
	 * @return A double value of the average turn around time
	 * @throws InvalidTimeException
	 *             Thrown when a process is found without a turn around time
	 */
	public default double averageTurnAround(ArrayList<Process> alp) throws InvalidTimeException {
		double average = 0;
		for (Process p : alp) {
			if (p.isTurnAroundTimeAssigned() == false) {
				throw new InvalidTimeException();
			}
			average += p.getTurnAroundTime();
		}
		return average / (double) (alp.size());
	}
}
