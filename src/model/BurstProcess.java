package model;

import java.util.Comparator;

/**
 * A process whose comparator and comparable interfaces are implemented based on
 * the burst time of the processes
 * 
 * @author Peter Vukas
 */
public class BurstProcess extends Process implements Comparable<BurstProcess>, Comparator<BurstProcess> {

	/**
	 * Copy constructor/Conversion Constructor
	 * 
	 * @param p
	 *            The process to deep copy
	 */
	public BurstProcess(Process p) {
		super(p);
	}

	/**
	 * Creates a new Burst Process with all fields of a process randomized
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you should contact the creator of this method asap for fixing
	 */
	public BurstProcess() throws InvalidTimeException {
		super(); 
	}

	/**
	 * @param p
	 *            The process to be compared by burst time
	 * @return 0 if both bursts are equal, -1 if this < p, 1 if this > p
	 */
	@Override
	public int compareTo(BurstProcess p) {
		if (getBurstTime() == p.getBurstTime()) {
			return 0;
		} else if (getBurstTime() < p.getBurstTime()) {
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
	public int compare(BurstProcess p1, BurstProcess p2) {
		if (p1.getBurstTime() == p2.getBurstTime()) {
			return 0;
		} else if (p1.getBurstTime() < p2.getBurstTime()) {
			return -1;
		} else {
			return 1;
		}
	}

}
