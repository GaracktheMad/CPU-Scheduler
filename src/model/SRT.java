package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Shortest-Remaining-Time algorithm
 * @author Brandon Ruiz
 *
 */
public class SRT extends Scheduler<ArrivalProcess>{
	
	/**
	 * Constructor - nothing passed
	 */
	public SRT() {
		super();
		processes = new ArrayList<ArrivalProcess>();
	}
	
	/**
	 * Constructor - list of processes passed
	 * @param burst process list
	 */
	public SRT(ArrayList<ArrivalProcess> processes) {
		super();
		this.processes = processes;
	}

	/**
	 * Process addition
	 * @param new arrival process
	 */
	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	@Override
	public boolean removeProcess(ArrivalProcess p) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Generate a series of random arrival processes
	 * @param Number of processes to be generated
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
	 * Execute the processes in an SJF algorithm
	 */
	@Override
	public ArrayList<ArrivalProcess> run() {
		//An array of terminated processes, the returned array
		ArrayList<ArrivalProcess> terminated = new ArrayList<ArrivalProcess>();
		
		//Sort the processes by arrival time
		Collections.sort(processes);
		
		//Set the wait and turnaround times of all processes to 0
		for(Process p: processes) {
			System.out.println(p.getName() + ":	" + p.getBurstTime() + " : " + p.getArrivalTime());
			try {
				p.setWaitTime(0);
				p.setTurnAroundTime(0);
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}
		
		//Current CPU time
		double time = 0;
		
		while(processes.size() > 0) {
			//Any time there's a gap in CPU processing, it will be mapped to the Gantt chart and the time will be updated
			if(processes.get(0).getArrivalTime() > time) {
				gantt.addSection("Idle", time);
				time = processes.get(0).getArrivalTime();
			}
			
			//The current process running
			ArrivalProcess current = processes.get(0);
			
			for(int i = 1; i < processes.size(); i++) {
				/*
				 * If a process:
				 * has a shorter burst time than the current
				 * has an arrival time shorter than the projected end time of the current
				 * will still have a shorter burst if it cuts off the process while running
				 */
				if(processes.get(i).getBurstTime() < current.getBurstTime()
						&& processes.get(i).getArrivalTime() < time + current.getBurstTime()
						&& processes.get(i).getBurstTime() < time + current.getBurstTime() - processes.get(i).getArrivalTime()) {
					if(processes.get(i).getArrivalTime() <= time) 
						current = processes.get(i);
					else {
						try {
							//Process the current for the time it has before it is cut off by a shorter burst process
							gantt.addSection(current.getName(), time);
							double dt = processes.get(i).getArrivalTime() - time;
							System.out.println(dt);
							current.setBurstTime(current.getBurstTime() - dt);
							current.setTurnAroundTime(current.getTurnAroundTime() + dt);
							time += dt;
							current = processes.get(i);
						} catch(InvalidTimeException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			//Process the current for the remainder of its burst time
			try {
				
				//End chance
				if(terminate) return null;
				
				gantt.addSection(current.getName(), time);
				current.setWaitTime(time - current.getArrivalTime() - current.getTurnAroundTime());
				time += current.getBurstTime();
				current.setBurstTime(current.getTurnAroundTime() + current.getBurstTime());
				current.setTurnAroundTime(current.getWaitTime() + current.getBurstTime());
				terminated.add(current);
				processes.remove(current);
			} catch(InvalidTimeException e) {
				e.printStackTrace();
			}
			
		}
		
		//End chance
		if(terminate) return null;
		alert.close();
		
		gantt.end(time);
		processes = terminated;
		averageCalc();
		
		return terminated;
	}

	@Override
	public ArrayList<ArrivalProcess> getProcesses() {
		return processes;
	}

}
