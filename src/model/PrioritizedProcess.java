package model;

import java.util.Comparator;
import java.util.Random;

/**
 * Process which has and is sorted by a priority level
 * 
 * @author Peter Vukas
 *
 */
public class PrioritizedProcess extends Process
		implements Comparable<PrioritizedProcess>, Comparator<PrioritizedProcess> {
	/**
	 * The priority level of this process instance. 
	 */
	private short priority;

	/**
	 * Assigns a random burst time, priority level and an auto-generated name
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you should contact the creator of this method asap for fixing
	 */
	public PrioritizedProcess() throws InvalidTimeException {
		super();
		randomPriority();
	}

	/**
	 * Copy constructor
	 * 
	 * @param pp
	 *            The process to deep copy
	 */
	public PrioritizedProcess(PrioritizedProcess pp) {
		super(pp);
		priority = pp.getPriority();
	}
	
	
	/**
	 * Converts the process into a prioritized process
	 * @param p Process to be converted
	 * @param prior Priority level of the new converted process
	 */
	public PrioritizedProcess(Process p, short prior) {
		super(p);
		priority = prior;
	}


	/**
	 * Assigns a random burst time and an auto-generated name with the specified
	 * arrival time
	 * 
	 * @param p
	 *            Short value of the Priority level
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you should contact the creator of this method asap for fixing
	 */
	public PrioritizedProcess(short p) throws InvalidTimeException {
		super();
		priority = p;
	}

	/**
	 * Sets priority level to a random value
	 */
	protected void randomPriority() {
		Random r = new Random();
		r = new Random(r.nextLong());
		priority = (short) (r.nextInt() + 1);
	}

	/**
	 * @return Priority level of the process
	 */
	public short getPriority() {
		return priority;
	}

	/**
	 * Used to assign the priority level of this process.
	 * 
	 * @param priority
	 *            Priority level of this process.
	 */
	public void setPriority(short priority) {
		this.priority = priority;
	}

	/**
	 * @param p
	 *            The process to be compared by priority
	 * @return 0 if both priorities are equal, -1 if this < p, 1 if this > p
	 */
	@Override
	public int compareTo(PrioritizedProcess p) {
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
	public int compare(PrioritizedProcess p1, PrioritizedProcess p2) {
		if (p1.getArrivalTime() == p2.getArrivalTime()) {
			return 0;
		} else if (p1.getArrivalTime() < p2.getArrivalTime()) {
			return -1;

		} else {
			return 1;
		}
	}
}
