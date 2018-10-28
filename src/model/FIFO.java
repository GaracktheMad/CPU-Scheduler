package model;

import java.util.ArrayList;

/**
 * Processes are simulated using a First-In-First-Out algorithm
 * @author Peter Vukas
 *
 */
public class FIFO extends Scheduler<ArrivalProcess> implements UsesArrivalProcesses {

	/**
	 * Initializes the list of processes
	 */
	public FIFO() {
		super();
		processes = new ArrayList<ArrivalProcess>();
	}

	/* (non-Javadoc)
	 * @see model.Scheduler#run()
	 */
	@Override
	public ArrayList<ArrivalProcess> run() {
		double waitTimeProcessor = 0;
		double turnAroundProcessor = 0;
		processes.sort(new ArrivalProcess());
		for (ArrivalProcess ap : processes) {
			try {
				ap.setWaitTime(waitTimeProcessor);
				turnAroundProcessor += ap.getBurstTime();
				ap.setTurnAroundTime(turnAroundProcessor);
			} catch (InvalidTimeException e) {
				return null;
			}
			waitTimeProcessor += ap.getBurstTime();
		}
		boolean noError = averageCalc();
		if (noError == false) {
			return null;
		}
		return getProcesses();
	}

	/* (non-Javadoc)
	 * @see model.Scheduler#populateProcessList(int)
	 */
	@Override
	public void populateProcessList(int size) {
		for (int i = 0; i < size; i++) {
			processes.add(new ArrivalProcess());
		}
	}

	/* (non-Javadoc)
	 * @see model.UsesArrivalProcesses#addProcess(model.ArrivalProcess)
	 */
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	/* (non-Javadoc)
	 * @see model.UsesArrivalProcesses#removeProcess(model.ArrivalProcess)
	 */
	public boolean removeProcess(ArrivalProcess ap) {
		return processes.remove(ap);
	}

	/* (non-Javadoc)
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
