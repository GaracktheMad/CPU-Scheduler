package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Round Robin Algorithm
 * 
 * @author Brandon Ruiz
 *
 */

public class RoundRobin extends Scheduler<ArrivalProcess> {
	private double timeQuantum;

	public RoundRobin(double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
	}

	public RoundRobin() {
		super();
		timeQuantum = 5;
	}

	public RoundRobin(ArrayList<ArrivalProcess> processes, double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
		this.processes = processes;
	}

	public RoundRobin(ArrayList<ArrivalProcess> processes) {
		super();
		timeQuantum = 5;
		this.processes = processes;
	}

	public double getTimeQuantum() {
		return timeQuantum;
	}

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

	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	@Override
	public ArrayList<ArrivalProcess> getProcesses() {
		ArrayList<ArrivalProcess> returnable = new ArrayList<>();
		for (ArrivalProcess ap : processes) {
			returnable.add(new ArrivalProcess(ap));
		}
		return returnable;
	}

	@Override
	public ArrayList<ArrivalProcess> run() {

		// An array of terminated processes, the returned array
		ArrayList<ArrivalProcess> terminated = new ArrayList<ArrivalProcess>();

		Collections.sort(processes);

		double time = 0; // Current time of the CPU

		while (processes.size() != 0) {

			// If the CPU was idle, move forward the time so that a process is available
			if (processes.get(0).getArrivalTime() > 0) {
				gantt.addSection("Idle", time);
				time = processes.get(0).getArrivalTime();
			}

			for (ArrivalProcess p : processes) {
				if (p.getArrivalTime() > time) break;
				else try {
					if(p.getBurstTime() > timeQuantum) {
						gantt.addSection(p.getName(), time);
						p.setBurstTime(p.getBurstTime() - timeQuantum);
						p.setTurnAroundTime(p.getTurnAroundTime() + timeQuantum);
						time += timeQuantum;
					}
					else {
						gantt.addSection(p.getName(), time);
						p.setWaitTime(time - p.getTurnAroundTime() - p.getArrivalTime());
						time += p.getBurstTime();
						p.setBurstTime(p.getBurstTime() + p.getTurnAroundTime());
						p.setTurnAroundTime(p.getBurstTime() + p.getWaitTime());
						terminated.add(p);
						processes.remove(p);
					}
				} catch (InvalidTimeException e) {
					// TODO Auto-generated catch block
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
}
