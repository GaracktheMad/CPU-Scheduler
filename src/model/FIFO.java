package model;

import java.util.ArrayList;

/**
 * Processes are simulated using a First-In-First-Out algorithm
 * @author Peter Vukas
 *
 */
public class FIFO extends Scheduler<ArrivalProcess>  {

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
		try {
			processes.sort(new ArrivalProcess());
		} catch (InvalidTimeException e1) {
			e1.printStackTrace();
		}
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
			try {
				processes.add(new ArrivalProcess());
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}
	}


	/* (non-Javadoc)
	 * @see model.Scheduler#addProcess(model.Process)
	 */
	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	/* (non-Javadoc)
	 * @see model.Scheduler#removeProcess(model.Process)
	 */
	@Override
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
