package model;

import java.util.Comparator;
import java.util.Random;

public class ArrivalProcess extends Process implements Comparable<ArrivalProcess>, Comparator<ArrivalProcess> {
	private double arrivalTime;

	/**
	 * Assigns a random burst time, arrival time and an auto-generated name
	 */
	public ArrivalProcess() {
		super();
		randomArrivalTime();
	}

	/**
	 * Assigns a random burst time and an auto-generated name with the specified
	 * arrival time
	 * 
	 * @param arrivalT
	 *            Double value of the time the process arrived in the cpu
	 */
	public ArrivalProcess(double arrivalT) {
		super();
		arrivalTime = arrivalT;
	}

	/**
	 * Copy constructor
	 * @param p The process to deep copy
	 */
	public ArrivalProcess(ArrivalProcess ap) {
		super(ap);
		arrivalTime = ap.getArrivalTime();
	}
	
	/**
	 * @param arrivalT
	 *            Double value of the time the process arrived in the cpu
	 * @param burstTime
	 *            Double value of the process burst time
	 * @param name
	 *            String value of the process name
	 */
	public ArrivalProcess(double burstTime, String name, double arrivalT) {
		super(burstTime, name);
		arrivalTime = arrivalT;
	}

	/**
	 * Autogenerates a process name.
	 * 
	 * @param arrivalT
	 *            Double value of the time the process arrived in the cpu
	 * @param burstTime
	 *            Double value of the process burst time
	 */
	public ArrivalProcess(double burstTime, double arrivalT) {
		super(burstTime);
		arrivalTime = arrivalT;
	}

	/**
	 * Assigns a random burst time
	 * 
	 * @param arrivalT
	 *            Double value of the time the process arrived in the cpu
	 * @param name
	 *            String value of the process name
	 */
	public ArrivalProcess(String n, double arrivalT) {
		super(n);
		arrivalTime = arrivalT;
	}

	/**
	 * Sets arrival time to a random value
	 */
	protected void randomArrivalTime() {
		Random r = new Random();
		r = new Random(r.nextLong());
		arrivalTime = Math.abs(Math.ceil(r.nextDouble() * 100)) + 1;
	}

	/**
	 * 
	 * @return Arrival time of the process.
	 */
	public double getArrivalTime() {
		return arrivalTime;
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

	/* (non-Javadoc)
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
