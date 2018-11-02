package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Shortest-Job-First algorithm
 * @author Brandon Ruiz
 *
 */
public class SJF extends Scheduler<BurstProcess>   {
	
	public SJF() {
		super();
		processes = new ArrayList<BurstProcess>();
	}
	
	public void addProcess(BurstProcess bp) {
		processes.add(bp);
	}

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

	@Override
	public ArrayList<BurstProcess> getProcesses() {
		ArrayList<BurstProcess> returnable = new ArrayList<>();
		for(BurstProcess bp: processes) {
			returnable.add(new BurstProcess(bp));
		}
		return returnable;
	}

	@Override
	public ArrayList<BurstProcess> run() {
		//An array of terminated processes, the returned array
		ArrayList<BurstProcess> terminated = new ArrayList<BurstProcess>();	
		
		for(Process p: processes) 
			System.out.println(p.getName() + ":	" + p.getBurstTime() + " : " + p.getArrivalTime());
		
		Collections.sort(processes);
		
		double time = 0;	//Current time of the CPU
		boolean idle = true;	//Boolean for when the CPU time is shorter than all remaining arrival times
		
		while(processes.size() != 0) {
			
			//If the CPU was idle, move forward the time so that a process is available
			if(idle) {
				gantt.addProcess("Idle", time);
				time = getShortestArrival(processes);
			}
			
			idle = true;
			
			for(BurstProcess p: processes) {
				//Execute the shortest process that had arrived
				if(p.getArrivalTime() <= time) {
					try{
						p.setWaitTime(time);
						p.setTurnAroundTime(p.getBurstTime() + time);
						//System.out.println(p.getWaitTime() + " : " + p.getTurnAroundTime());
						gantt.addProcess(p.getName(), time);
						time += p.getBurstTime();
						terminated.add(p);
						processes.remove(p);
						idle = false;
						break;
					} catch(InvalidTimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return terminated;
	}

	@Override
	public boolean removeProcess(BurstProcess p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A process that loops through the queue to find when the next process will arrive
	public int getShortestArrival(ArrayList<BurstProcess> processes) {
		int time = Integer.MAX_VALUE;
		for(Process p: processes) {
			if (p.getArrivalTime() < time)
				time = (int) p.getArrivalTime();
		}
		return time;
	}

}
