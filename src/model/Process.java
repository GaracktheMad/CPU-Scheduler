package model;

import java.util.Random;

/**
 * Represents a process. Holds burst time, wait time, and turn around time as
 * doubles for simplified calculations. Stores the process name as a string.
 * Allows for auto generation of burst and names. Wait time and turn around time
 * must be set manually.
 * 
 * @author Peter Vukas
 */
public abstract class Process {
	private double burstTime;
	private String name;
	private double waitTime;
	private double turnAroundTime;
	/**
	 * Used for auto name generation for default constructor
	 */
	private static int processNameGen = 0;
	private boolean waitTimeAssigned;
	private boolean turnAroundTimeAssigned;
	private boolean isFinished;

	/**
	 * @param burstTime
	 *            Double value of the process burst time
	 * @param name
	 *            String value of the process name
	 */
	public Process(double burstTime, String name) {
		this.burstTime = burstTime;
		this.name = name;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
	}

	/**
	 * Assigns an auto-generated name
	 * 
	 * @param burstTime
	 *            Double value of the process burst time
	 */
	public Process(double burstTime) {
		this.burstTime = burstTime;
		name = String.format("P%d", processNameGen++);
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
	}

	/**
	 * Assigns the process a random burst time.
	 * 
	 * @param n
	 *            String value of the process's name
	 */
	public Process(String n) {
		randomBurstTime();
		name = n;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
	}

	/**
	 * Assigns a random burst time and an auto-generated name
	 */
	public Process() {
		randomBurstTime();
		name = String.format("P%d", processNameGen++);
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
	}
	
	/**
	 * Copy constructor
	 * @param p The process to deep copy
	 */
	protected Process(Process p) {
		name = p.getName();
		burstTime = p.getBurstTime();
		waitTimeAssigned = p.isWaitTimeAssigned();
		turnAroundTimeAssigned = p.isTurnAroundTimeAssigned();
		isFinished = p.isFinished();
		waitTime = p.getWaitTime();
		turnAroundTime = p.getTurnAroundTime();
	}

	/**
	 * @return Checks if there is an assigned wait time
	 */
	public boolean isWaitTimeAssigned() {
		return waitTimeAssigned;
	}

	/**
	 * @return Checks if there is an assigned turn around time
	 */
	public boolean isTurnAroundTimeAssigned() {
		return turnAroundTimeAssigned;
	}

	/**
	 * @return Boolean. If true, the process has been flagged as finished.
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Inverts the status of finished. The default state on instantiation is false.
	 */
	public void toggleFinished() {
		isFinished = !isFinished;
	}

	/**
	 * Sets burst time to a random value
	 */
	public void randomBurstTime() {
		Random r = new Random();
		r = new Random(r.nextLong());
		burstTime = Math.abs(Math.ceil(r.nextDouble() * 100)) + 1;
	}

	/**
	 * @return Double value of the burst time.
	 */
	public double getBurstTime() {
		return burstTime;
	}

	/**
	 * @param bTime
	 *            Double value of the proposed new burst time.
	 * @throws InvalidTimeException
	 *             Thrown if the bTime is <= 0
	 */
	public void setBurstTime(double bTime) throws InvalidTimeException {
		if (bTime <= 0) {
			throw new InvalidTimeException();
		} else {
			burstTime = bTime;
		}
	}

	/**
	 * @return String value of the process's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Double value of wait time. Must be assigned via the setWaitTime()
	 *         method first.
	 */
	public double getWaitTime() {
		return waitTime;
	}

	/**
	 * @param wTime
	 *            Double value of the proposed new wait time.
	 * @throws InvalidTimeException
	 *             Thrown if the wTime is < 0
	 */
	public void setWaitTime(double wTime) throws InvalidTimeException {
		if (wTime < 0) {
			throw new InvalidTimeException();
		} else {
			waitTime = wTime;
			waitTimeAssigned = true;
		}
	}

	/**
	 * @return Double value of turn around time. Must be assigned via the
	 *         setTurnAroundTime() method first.
	 */
	public double getTurnAroundTime() {
		return turnAroundTime;
	}

	/**
	 * @param tAroundTime
	 *            Double value of the proposed new turn around time.
	 * @throws InvalidTimeException
	 *             Thrown if the tAroundTime is < 0
	 */
	public void setTurnAroundTime(double tAroundTime) throws InvalidTimeException {
		if (tAroundTime < 0) {
			throw new InvalidTimeException();
		} else {
			turnAroundTime = tAroundTime;
			turnAroundTimeAssigned = true;
		}
	}

}
