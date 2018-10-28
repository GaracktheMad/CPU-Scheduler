package model;

import java.util.ArrayList;

/**
 * Methods to calculate the average wait and turn around times of multiple
 * processes
 * 
 * @author Peter Vukas
 */
public interface CalcAverages <N extends Process> {

	/**
	 * @param alp
	 *            An array list of all processes to be included in the average
	 * @return A double value of the average wait time
	 * @throws InvalidTimeException
	 *             Thrown when a process is found without a wait time
	 */
	public default double averageWait(ArrayList<N> alp) throws InvalidTimeException {
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
	public default double averageTurnAround(ArrayList<N> alp) throws InvalidTimeException {
		double average = 0;
		for (Process p : alp) {
			if (p.isTurnAroundTimeAssigned() == false) {
				throw new InvalidTimeException();
			}
			average += p.getTurnAroundTime();
		}
		return average / (double) (alp.size());
	}

	/**
	 * Calculation with little's formula
	 * 
	 * @param w
	 *            Average wait time in queue
	 * @param lambda
	 *            Average arrival rate into queue
	 * @return Average queue length
	 */
	public default double littleAverageQueueLength(double w, double lambda) {
		return w * lambda;
	}

	/**
	 * Calculation with little's formula
	 * 
	 * @param n
	 *            Average queue length
	 * @param lambda
	 *            Average arrival rate into queue
	 * @return Average wait time in queue
	 */
	public default double littleAverageWaitTime(double n, double lambda) {
		return n / lambda;
	}

	/**
	 * Calculation with little's formula
	 * 
	 * @param n
	 *            Average queue length
	 * @param w
	 *            Average wait time in queue
	 * @return Average arrival rate into queue
	 */
	public default double littleAverageArrivalRate(double n, double w) {
		return n / w;
	}
}
