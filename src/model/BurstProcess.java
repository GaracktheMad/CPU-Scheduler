package model;

import java.util.Comparator;

public class BurstProcess extends Process implements Comparable<BurstProcess>,Comparator<BurstProcess>  {
	
	/**
	 * Copy constructor
	 * @param p The process to deep copy
	 */
	public BurstProcess(BurstProcess bp) {
		super(bp);
	}
	
	/**
	 * Assigns a random burst time and an auto-generated name
	 */
	public BurstProcess() {
		super();
	}

	/**
	 * @param burstTime Double value of the process burst time
	 * @param name String value of the process name
	 */
	public BurstProcess(double burstTime, String name) {
		super(burstTime, name);
	}

	/**
	 * Double value of the process burst time
	 * @param burstTime Double value of the process burst time
	 */
	public BurstProcess(double burstTime) {
		super(burstTime);
	}

	/**
	 * Assigns the process a random burst time.
	 * @param n String value of the process's name
	 */
	public BurstProcess(String n) {
		super(n);
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
		} else if (getBurstTime() < p.getBurstTime() ) {
			return -1;
		} else {
			return 1;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(BurstProcess p1, BurstProcess p2) {
		if (p1.getBurstTime() == p2.getBurstTime()) {
			return 0;
		} else if (p1.getBurstTime() < p2.getBurstTime() ) {
			return -1;
		} else {
			return 1;
		}
	}

}
