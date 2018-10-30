package model;

import java.util.Comparator;

/**
 * A process which is sorted by its arrival time
 * 
 * @author Peter Vukas
 *
 */
public class ArrivalProcess extends Process implements Comparable<ArrivalProcess>, Comparator<ArrivalProcess> {

	/**
	 * Used for compatibility with Brandom's SRT algorithm's linked list structure
	 * of processes
	 */
	public ArrivalProcess next;

	/**
	 * Assigns a random burst time, arrival time and an auto-generated name
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you should contact the creator of this method asap for fixing
	 */
	public ArrivalProcess() throws InvalidTimeException {
		super();
	}

	/**
	 * Copy constructor
	 * 
	 * @param p
	 *            The process to deep copy
	 */
	public ArrivalProcess(Process p) {
		super(p);
	}

	/**
	 * @param p
	 *            The process to be compared by arrival time
	 * @return 0 if both arrivals are equal, -1 if this < p, 1 if this > p
	 */
	@Override
	public int compareTo(ArrivalProcess p) {
		if (getArrivalTime() == p.getArrivalTime()) {
			return 0;
		} else if (getArrivalTime() < p.getArrivalTime()) {
			return -1;
		} else {
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ArrivalProcess p1, ArrivalProcess p2) {
		if (p1.getArrivalTime() == p2.getArrivalTime()) {
			return 0;
		} else if (p1.getArrivalTime() < p2.getArrivalTime()) {
			return -1;
		} else {
			return 1;
		}
	}

}
