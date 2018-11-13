package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Round Robin Algorithm
 * WIP
 * 
 * @author Brandon Ruiz
 *
 */

public class RoundRobin extends Scheduler<ArrivalProcess> {
	private double timeQuantum;

	/**
	 * Scheduler constructor - only time quantum given
	 * 
	 * @param time quantum
	 */
	public RoundRobin(double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
	}

	/**
	 * Scheduler constructor - random time quantum
	 * 
	 */
	public RoundRobin() {
		super();
		timeQuantum = 5;
	}

	/**
	 * Scheduler constructor - process list and quantum given
	 * 
	 * @param arrival process list
	 * @param time quantum
	 */
	public RoundRobin(ArrayList<ArrivalProcess> processes, double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
		this.processes = processes;
	}

	/**
	 * Scheduler constructor - process list given, random quantum
	 * 
	 * @param arrival process list
	 */
	public RoundRobin(ArrayList<ArrivalProcess> processes) {
		super();
		timeQuantum = 5;
		this.processes = processes;
	}

	/**
	 * Returns time quantum
	 * 
	 * @return time quantum
	 */
	public double getTimeQuantum() {
		return timeQuantum;
	}

	/**
	 * Create a randomized list of processes
	 * 
	 * @param number of processes to be generated
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

	/**
	 * Add a process to the list
	 * 
	 * @param new arrival process
	 */
	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	/**
	 * Return the process list - deep copy
	 * 
	 * @return process list - deep copy
	 */
	@Override
	public ArrayList<ArrivalProcess> getProcesses() {
		ArrayList<ArrivalProcess> returnable = new ArrayList<>();
		for (ArrivalProcess ap : processes) {
			returnable.add(new ArrivalProcess(ap));
		}
		return returnable;
	}

	/**
	 * Execute the processes in a Round Robin algorithm
	 * 
	 * @return processes list
	 */
	@Override
	public ArrayList<ArrivalProcess> run() {

		// An array of terminated processes, the returned array
		ArrayList<ArrivalProcess> terminated = new ArrayList<ArrivalProcess>();

		Collections.sort(processes);

		double time = 0; // Current time of the CPU

		while (processes.size() != 0) {

			// If the CPU was idle, move forward the time so that a process is available
			if (processes.get(0).getArrivalTime() > time) {
				gantt.addSection("Idle", time);
				time = processes.get(0).getArrivalTime();
			}

			for (int i = 0; i < processes.size(); i++) {
				if (processes.get(i).getArrivalTime() > time) break;
				else try {
					if(processes.get(i).getBurstTime() > timeQuantum) {
						gantt.addSection(processes.get(i).getName(), time);
						processes.get(i).setBurstTime(processes.get(i).getBurstTime() - timeQuantum);
						processes.get(i).setTurnAroundTime(processes.get(i).getTurnAroundTime() + timeQuantum);
						time += timeQuantum;
					}
					else {
						gantt.addSection(processes.get(i).getName(), time);
						processes.get(i).setWaitTime(time - processes.get(i).getTurnAroundTime() - processes.get(i).getArrivalTime());
						time += processes.get(i).getBurstTime();
						processes.get(i).setBurstTime(processes.get(i).getBurstTime() + processes.get(i).getTurnAroundTime());
						processes.get(i).setTurnAroundTime(processes.get(i).getBurstTime() + processes.get(i).getWaitTime());
						terminated.add(processes.get(i));
						processes.remove(processes.get(i));
					}
				} catch (InvalidTimeException e) {
					e.printStackTrace();
				}
			}
		}
		averageCalc();
		gantt.end(time);
		processes = terminated;
		return terminated;
	}

	@Override
	public boolean removeProcess(ArrivalProcess p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Override of average wait time for algorithm
	 */
	@Override
	public double getAverageWaitTime() {
		double t = 0;
		for(ArrivalProcess ap: processes) {
			t += ap.getWaitTime();
		}
		return t / processes.size();
	}
	
	/**
	 * Override of average turnaround time for algorithm
	 */
	@Override
	public double getAverageTurnAroundTime() {
		double t = 0;
		for(ArrivalProcess ap: processes) {
			t += ap.getTurnAroundTime();
		}
		return t / processes.size();
	}
	
}
