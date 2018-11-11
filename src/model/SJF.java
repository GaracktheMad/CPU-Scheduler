package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Shortest-Job-First algorithm
 * 
 * @author Brandon Ruiz
 *
 */
public class SJF extends Scheduler<BurstProcess> {

	/**
	 * Constructor - nothing passed
	 */
	public SJF() {
		super();
		processes = new ArrayList<BurstProcess>();
	}

	/**
	 * Constructor - list of processes passed
	 * 
	 * @param burst
	 *            process list
	 */
	public SJF(ArrayList<BurstProcess> processes) {
		super();
		this.processes = processes;
	}

	/**
	 * Process addition
	 * 
	 * @param new
	 *            burst process
	 */
	public void addProcess(BurstProcess bp) {
		processes.add(bp);
	}

	/**
	 * Generate a series of random burst processes
	 * 
	 * @param Number
	 *            of processes to be generated
	 */
	@Override
	public void populateProcessList(int size) {
		for (int i = 0; i < size; i++) {
			try {
				processes.add(new BurstProcess());
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deep copy of the array list
	 * 
	 * @return the array list copy
	 */
	@Override
	public ArrayList<BurstProcess> getProcesses() {
		ArrayList<BurstProcess> returnable = new ArrayList<>();
		for (BurstProcess bp : processes) {
			returnable.add(new BurstProcess(bp));
		}
		return returnable;
	}

	/**
	 * Execute the processes in an SJF algorithm
	 */
	@Override
	public ArrayList<BurstProcess> run() {
		// Reset the termination button
		terminate = false;
		// Show the termination pop-up
		showAlert();

		// An array of terminated processes, the returned array
		ArrayList<BurstProcess> terminated = new ArrayList<BurstProcess>();

		// for(Process p: processes)
		// System.out.println(p.getName() + ": " + p.getBurstTime() + " : " +
		// p.getArrivalTime());

		Collections.sort(processes);

		double time = 0; // Current time of the CPU
		boolean idle = true; // Boolean for when the CPU time is shorter than all remaining arrival times

		while (processes.size() != 0) {

			// If the CPU was idle, move forward the time so that a process is available
			if (idle) {
				gantt.addSection("Idle", time);
				time = getShortestArrival(processes);
			}

			idle = true;

			for (BurstProcess p : processes) {
				
				// End chance
				if (terminate)
					return null;
				
				// Execute the shortest process that had arrived
				if (p.getArrivalTime() <= time) {
					try {
						p.setWaitTime(time);
						p.setTurnAroundTime(p.getBurstTime() + time);
						// System.out.println(p.getWaitTime() + " : " + p.getTurnAroundTime());
						gantt.addSection(p.getName(), time);
						time += p.getBurstTime();
						terminated.add(p);
						processes.remove(p);
						idle = false;
						break;
					} catch (InvalidTimeException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// End chance
		if (terminate)
			return null;
		alert.close();

		gantt.end(time);
		processes = terminated;
		return terminated;
	}

	@Override
	public boolean removeProcess(BurstProcess p) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * A process that loops through the queue to find the next process with the
	 * shortest arrival time
	 * 
	 * @param processes
	 * @return the shortest arrival time
	 */
	public int getShortestArrival(ArrayList<BurstProcess> processes) {
		int time = Integer.MAX_VALUE;
		for (Process p : processes) {
			if (p.getArrivalTime() < time)
				time = (int) p.getArrivalTime();
		}
		return time;
	}

}
