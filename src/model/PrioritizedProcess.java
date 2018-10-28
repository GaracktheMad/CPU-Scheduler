package model;

import java.util.Comparator;
import java.util.Random;

public class PrioritizedProcess extends Process implements Comparable<PrioritizedProcess>, Comparator<PrioritizedProcess> {
	private short priority;

	/**
	 * Assigns a random burst time, priority level and an auto-generated name
	 */
	public PrioritizedProcess() {
		super();
		randomPriority();
	}
	
	/**
	 * Copy constructor
	 * @param p The process to deep copy
	 */
	public PrioritizedProcess(PrioritizedProcess pp) {
		super(pp);
		priority = pp.getPriority();
	}

	/**
	 * Assigns a random burst time and an auto-generated name with the specified
	 * arrival time
	 * 
	 * @param p
	 *            Short value of the Priority level
	 */
	public PrioritizedProcess(short p) {
		super();
		priority = p;
	}

	/**
	 * @param p
	 *            Short value of the Priority level
	 * @param burstTime
	 *            Double value of the process burst time
	 * @param name
	 *            String value of the process name
	 */
	public PrioritizedProcess(double burstTime, String name, short p) {
		super(burstTime, name);
		priority = p;
	}

	/**
	 * Auto-generates a process name.
	 * 
	 * @param p
	 *            Short value of the Priority level
	 * @param burstTime
	 *            Double value of the process burst time
	 */
	public PrioritizedProcess(double burstTime, short p) {
		super(burstTime);
		priority = p;
	}

	/**
	 * Assigns a random burst time
	 * 
	 * @param p
	 *            Short value of the Priority level
	 * @param name
	 *            String value of the process name
	 */
	public PrioritizedProcess(String n, short p) {
		super(n);
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
		if (priority == p.getPriority()) {
			return 0;
		} else if (priority < p.getPriority()) {
			return -1;

		} else {
			return 1;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(PrioritizedProcess p1, PrioritizedProcess p2) {
		if (p1.getPriority() == p2.getPriority()) {
			return 0;
		} else if (p1.getPriority() < p2.getPriority()) {
			return -1;

		} else {
			return 1;
		}
	}
}
