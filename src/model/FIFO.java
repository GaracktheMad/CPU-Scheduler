package model;

import java.util.ArrayList;

/**
 * Processes are simulated using a First-In-First-Out algorithm
 * 
 * @author Peter Vukas
 *
 */
public class FIFO extends Scheduler<ArrivalProcess> {

	/**
	 * Initializes the list of processes
	 */
	public FIFO() {
		super();
		processes = new ArrayList<ArrivalProcess>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Scheduler#run()
	 */
	@Override
	public ArrayList<ArrivalProcess> run() {
		double waitTimeProcessor = 0; // Used in wait time calc
		double turnAroundProcessor = 0; // Used in TA calc
		try {
			processes.sort(new ArrivalProcess()); // Sorts processes by arrival time
		} catch (InvalidTimeException e1) {// Exception which should never throw
			System.out.println("UH-OH! UNEXPECTED ERROR ALERT! FIX IT");
		}
		for (ArrivalProcess ap : processes) {// Algorithmic loop
			try {
				ap.setWaitTime(waitTimeProcessor); // Sets the calculated wait time
				turnAroundProcessor += ap.getBurstTime();// Turnaround = Last turnaround + burst time
				ap.setTurnAroundTime(turnAroundProcessor); // Sets the calc'd TA
			} catch (InvalidTimeException e) {
				return null; // This should never happen. It it occurs a painful bug has happened or math
								// broke again
			}
			waitTimeProcessor += ap.getBurstTime();// The next wait time is the current wait time + the current burst
													// time
		}
		boolean noError = averageCalc(); // Calculates the average values for both Turnaround and wait
		if (noError == false) {
			return null; // This should only occur if a wait time or turn around time wasn't added
		}
		return getProcesses(); // Returns a copy of the current array
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Scheduler#populateProcessList(int)
	 */
	@Override
	public void populateProcessList(int size) {
		for (int i = 0; i < size; i++) {
			try {
				processes.add(new ArrivalProcess());
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Scheduler#addProcess(model.Process)
	 */
	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Scheduler#removeProcess(model.Process)
	 */
	@Override
	public boolean removeProcess(ArrivalProcess ap) {
		return processes.remove(ap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Scheduler#getProcesses()
	 */
	@Override
	public ArrayList<ArrivalProcess> getProcesses() {
		ArrayList<ArrivalProcess> returnable = new ArrayList<>();
		for (ArrivalProcess ap : processes) {
			returnable.add(new ArrivalProcess(ap));
		}
		return returnable;
	}

}
