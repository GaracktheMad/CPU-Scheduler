package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Priority algorithm
 * 
 * @author Brandon Ruiz
 *
 */

public class Priority extends Scheduler<PrioritizedProcess> {

	/**
	 * Constructor - nothing passed
	 */
	public Priority() {
		super();
		processes = new ArrayList<PrioritizedProcess>();
	}

	/**
	 * Constructor - list of processes passed
	 * 
	 * @param burst
	 *            process list
	 */
	public Priority(ArrayList<PrioritizedProcess> processes) {
		super();
		this.processes = processes;
	}

	@Override
	public void populateProcessList(int size) {
		if (size < 1) {
			size = 10;
		}
		for (int i = 0; i < size; i++) {
			try {
				processes.add(new PrioritizedProcess());
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addProcess(PrioritizedProcess pp) {
		processes.add(pp);
	}

	@Override
	public ArrayList<PrioritizedProcess> getProcesses() {
		ArrayList<PrioritizedProcess> returnable = new ArrayList<>();
		for (PrioritizedProcess pp : processes) {
			returnable.add(new PrioritizedProcess(pp));
		}
		return returnable;
	}

	@Override
	public ArrayList<PrioritizedProcess> run() {
		//Reset the termination button
		terminate = false;
		//Show the termination pop-up
		showAlert();
		
		// An array of terminated processes, the returned array
		ArrayList<PrioritizedProcess> terminated = new ArrayList<PrioritizedProcess>();

		// Sort the processes by arrival time
		Collections.sort(processes);

		// Set the wait and turnaround times of all processes to 0
		for (PrioritizedProcess p : processes) {
			System.out.println(
					p.getName() + ":	" + p.getBurstTime() + " : " + p.getArrivalTime() + " : " + p.getPriority());
			try {
				p.setWaitTime(0);
				p.setTurnAroundTime(0);
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}

		// Current CPU time
		double time = 0;

		while (processes.size() > 0) {
			
			//End chance
			if(terminate) return null;
			
			// Any time there's a gap in CPU processing, it will be mapped to the Gantt
			// chart and the time will be updated
			if (processes.get(0).getArrivalTime() > time) {
				gantt.addSection("Idle", time);
				time = processes.get(0).getArrivalTime();
			}

			// The current process running
			PrioritizedProcess current = processes.get(0);

			for (int i = 1; i < processes.size(); i++) {
				/*
				 * If a process: has a smaller priority time than the current && arrives while the
				 * current is being processed
				 */
				if (processes.get(i).getPriority() < current.getPriority()
						&& processes.get(i).getArrivalTime() < time + current.getBurstTime()) {
					if (processes.get(i).getArrivalTime() <= time)
						current = processes.get(i);
					else {
						try {
							// Process the current for the time it has before it is cut off by a shorter
							// priority process
							gantt.addSection(current.getName(), time);
							double dt = processes.get(i).getArrivalTime() - time;
							//System.out.println(dt);
							current.setBurstTime(current.getBurstTime() - dt);
							current.setTurnAroundTime(current.getTurnAroundTime() + dt);
							time += dt;
							current = processes.get(i);
							
						} catch (InvalidTimeException e) {
							e.printStackTrace();
						}
					}
				}
			}

			// Process the current for the remainder of its burst time
			try {
				
				//End chance
				if(terminate) return null;
				
				gantt.addSection(current.getName(), time);
				current.setWaitTime(time - current.getArrivalTime() - current.getTurnAroundTime());
				time += current.getBurstTime();
				current.setBurstTime(current.getTurnAroundTime() + current.getBurstTime());
				current.setTurnAroundTime(current.getWaitTime() + current.getBurstTime());
				System.out.println(current.getName() + " - " + current.getWaitTime() + " - " + current.getTurnAroundTime());
				
				terminated.add(current);
				processes.remove(current);
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}

		}
		
		//End chance
		if(terminate) return null;
		alert.close();
		
		gantt.end(time);
		processes = terminated;

		return terminated;
	}

	@Override
	public boolean removeProcess(PrioritizedProcess p) {
		// TODO Auto-generated method stub
		return false;
	}

}
