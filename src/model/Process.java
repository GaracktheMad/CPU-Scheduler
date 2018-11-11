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
public class Process {
	/**
	 * The time at which the process arrived into the CPU. Must be >= 0
	 */
	private double arrivalTime;
	/**
	 * The time it takes for this process to complete. Must be >= 0
	 */
	private double burstTime;
	/**
	 * Name of this process. Default format is P#
	 */
	private String name;
	/**
	 * Time of Execution - Arrival Time Must be >= 0
	 */
	private double waitTime;
	/**
	 * Time of completion. Must be >= 0
	 */
	private double turnAroundTime;
	/**
	 * Used for auto name generation for default constructor
	 */
	private static int processNameGen = 1;
	/**
	 * When true, indicates a wait time has been added to this process
	 */
	private boolean waitTimeAssigned;
	/**
	 * When true, indicates a turn around time has been added to this process
	 */
	private boolean turnAroundTimeAssigned;
	/**
	 * Indicates this process has already run and is no longer needed
	 */
	private boolean isFinished;
	public final int id;
	private static int idGen = 0;

	/**
	 * @param burstTime Double value of the process burst time
	 * @param name      String value of the process name
	 * @param arrivalT  Double value of the time the process arrived in the cpu
	 * @throws InvalidTimeException Thrown when the burst time or arrival time is
	 *                              invalid
	 */
	public Process(String name, double burstTime, double arrivalT) throws InvalidTimeException {
		setBurstTime(burstTime);
		this.name = name;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		setArrivalTime(arrivalT);
		id = idGen++;
	}

	/**
	 * Assigns an auto-generated name
	 * 
	 * @param burstTime Double value of the process burst time
	 * @param arrivalT  Double value of the process arrival time into the CPU
	 * @throws InvalidTimeException Thrown when the burst time or arrival time is
	 *                              invalid
	 */
	public Process(double burstTime, double arrivalT) throws InvalidTimeException {
		setBurstTime(burstTime);
		generateName();
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		setArrivalTime(arrivalT);
		id = idGen++;
	}

	/**
	 * Assigns the process a random burst time.
	 * 
	 * @param n        String value of the process's name
	 * @param arrivalT Double value of the process arrival time into the CPU
	 * @throws InvalidTimeException
	 */
	public Process(String n, double arrivalT) throws InvalidTimeException {
		randomBurstTime();
		name = n;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		arrivalTime = arrivalT;
		id = idGen++;
	}

	/**
	 * Assigns a random burst time and an auto-generated name with the specified
	 * arrival time
	 * 
	 * @param arrivalT Double value of the time the process arrived in the cpu
	 * @throws InvalidTimeException Invalid Arrival Time
	 */
	public Process(double arrivalT) throws InvalidTimeException {
		randomBurstTime();
		generateName();
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		setArrivalTime(arrivalT);
		id = idGen++;
	}

	/**
	 * Assigns a random burst time and an auto-generated name
	 * 
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you
	 *                              should contact the creator of this method asap
	 *                              for fixing
	 */
	public Process() throws InvalidTimeException {
		randomBurstTime();
		generateName();
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		randomArrivalTime();
		id = idGen++;
	}

	/**
	 * Copy constructor
	 * 
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
		arrivalTime = p.getArrivalTime();
		id = p.id;
	}

	/**
	 * Creates a process with a random arrival time
	 * 
	 * @param burstT Burst time of this process
	 * @param n      Name of this process
	 * @throws InvalidTimeException Invalid burst time
	 */
	public Process(double burstT, String n) throws InvalidTimeException {
		setBurstTime(burstT);
		name = n;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		randomArrivalTime();
		id = idGen++;
	}

	/**
	 * Creates a process with only a custom name, all other fields are randomized
	 * 
	 * @param n Custom process name
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you
	 *                              should contact the creator of this method asap
	 *                              for fixing
	 */
	public Process(String n) throws InvalidTimeException {
		randomBurstTime();
		name = n;
		waitTimeAssigned = false;
		turnAroundTimeAssigned = false;
		isFinished = false;
		randomArrivalTime();
		id = idGen++;
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
	 * @return Double value of the burst time.
	 */
	public double getBurstTime() {
		return burstTime;
	}

	/**
	 * @param bTime Double value of the proposed new burst time.
	 * @throws InvalidTimeException Thrown if the bTime is < 0
	 */
	public void setBurstTime(double bTime) throws InvalidTimeException {
		if (bTime < 0) {
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
	 * @param wTime Double value of the proposed new wait time.
	 * @throws InvalidTimeException Thrown if the wTime is < 0
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
	 * @param tAroundTime Double value of the proposed new turn around time.
	 * @throws InvalidTimeException Thrown if the tAroundTime is < 0
	 */
	public void setTurnAroundTime(double tAroundTime) throws InvalidTimeException {
		if (tAroundTime < 0) {
			throw new InvalidTimeException();
		} else {
			turnAroundTime = tAroundTime;
			turnAroundTimeAssigned = true;
		}
	}

	/**
	 * 
	 * @return Arrival time of the process.
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Resets the value of the process name generator to 0.
	 */
	public static void resetProcessNameGen() {
		processNameGen = 1;
	}

	/**
	 * Sets arrival time to a random value
	 * 
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you
	 *                              should contact the creator of this method asap
	 *                              for fixing
	 */
	protected void randomArrivalTime() throws InvalidTimeException {
		Random r = new Random();
		r = new Random(r.nextLong());
		setArrivalTime(Math.abs(Math.ceil(r.nextDouble() * 100)) + 1);
	}

	/**
	 * Sets burst time to a random value
	 * 
	 * @throws InvalidTimeException An unexpected error occurs in this case, and you
	 *                              should contact the creator of this method asap
	 *                              for fixing
	 */
	public void randomBurstTime() throws InvalidTimeException {
		Random r = new Random();
		r = new Random(r.nextLong());
		setBurstTime(Math.abs(Math.ceil(r.nextDouble() * 100)) + 1);
	}

	/**
	 * Used to create a new process name based on the name generation field
	 */
	private void generateName() {
		name = String.format("P%d", processNameGen++);
	}

	/**
	 * @param at Checks the validity of a potential new Arrival Time
	 * @throws InvalidTimeException Arrival time is found invalid
	 */
	private void setArrivalTime(double at) throws InvalidTimeException {
		if (at < 0) {
			throw new InvalidTimeException();
		} else {
			arrivalTime = at;
		}
	}

}
