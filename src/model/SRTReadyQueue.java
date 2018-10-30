package model;

/**
 * @author Brandon Ruiz
 *
 */
public class SRTReadyQueue {

	/**
	 * The process currently being processed by the queue
	 */
	private ArrivalProcess current;
	/**
	 * Total waiting time of all processes
	 */
	public double wait;
	/**
	 * Total turnaround time of all processes
	 */
	public double turnaround;

	/**
	 * Creates a new ready queue
	 */
	public SRTReadyQueue() {
		current = null;
		wait = 0;
		turnaround = 0;
	}

	/**
	 * Reset the queue for the start of a new algorithm
	 */
	public void clear() {
		wait = 0;
		turnaround = 0;
		current = null;
	}

	/**
	 * Boolean for checking if the queue is empty
	 * 
	 * @return True if the queue is empty
	 */
	public boolean isEmpty() {
		return current == null;
	}

	/**
	 * Inserting a process with respect to the Shortest Time Remaining First. THIS
	 * PROCESS IS DEPENDANT ON THE REST OF THE CODE RECOGNIZING NOT TO PASS A NULL
	 * 
	 * @param arrivalProcess
	 *            Process to be added to the queue
	 */
	public void addSRTF(ArrivalProcess arrivalProcess) {

		// The process currently being compared to p, starting with the process
		// currently being processed
		ArrivalProcess temp = current;

		// The process that came before the process being compared to p, if one such
		// exists
		ArrivalProcess previous = null;

		while (true) {
			if (temp == null) { // If we are at the end of the queue, we append the process
				if (previous == null) { // If the queue is empty
					current = arrivalProcess; // Inserting a process as the only one
				} else {
					previous.next = arrivalProcess; // Appending the process to the end of the list
				}
				break;
			} else if (temp.getBurstTime() > arrivalProcess.getBurstTime()) { // If the next process has a greater burst
																				// time, we
				// append the process

				if (previous != null) {
					// A process is being inserted between two others
					previous.next = arrivalProcess;
					arrivalProcess.next = temp;
				} else {
					// A process is being appended in the front of the queue
					arrivalProcess.next = temp;
					current = arrivalProcess;
				}
				break;
			}

			// Compare p to the next process in the queue; p.burst is greater than
			// temp.burst
			previous = temp;
			temp = temp.next;
		}
	}

	/**
	 * This method will be called after every "second passing" in the processor. It
	 * will decrement the burst time of the of the current process by one second.
	 * THIS PROCESS IS DEPENDANT ON THE USER NEVER INPUTTING A PROCESS WITH BURST
	 * TIME 0
	 */
	public void SRTFExecuteProcess() {
		try {
			System.out.println("burst of current process " + current.getBurstTime());
			// If the current process has been completed, we start processing the next
			if (current.getBurstTime() == 0) {
				/*
				 * Add the wait and turnaround of the completed process to the accumulative wait
				 * and turnaround values of the ready queue
				 */
				wait += current.getWaitTime();
				turnaround += current.getTurnAroundTime();
				current = current.next;
				if (current != null) {
					current.setBurstTime(current.getBurstTime() - 1); // Decrement the burst time of the new current
																		// process
					current.setTurnAroundTime(current.getTurnAroundTime() + 1); // Increment the turnaround time of the
																				// new
																				// current process
				}
			} else {
				current.setBurstTime(current.getBurstTime() - 1); // Decrement the burst time
				current.setTurnAroundTime(current.getTurnAroundTime() + 1); // Increment the turnaround time of the
																			// current
																			// process
			}

			// Increase the waiting and turnaround time of all processes left in the queue
			if (current != null) {
				ArrivalProcess next = current.next;
				while (next != null) {
					next.setWaitTime(next.getWaitTime() + 1);
					next.setTurnAroundTime(next.getTurnAroundTime() + 1);
					next = next.next;
				}
			}
		} catch (InvalidTimeException e) {
		}
	}

}