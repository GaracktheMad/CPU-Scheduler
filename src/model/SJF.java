package model;

/**
 * Shortest-Job-First algorithm
 * @author 
 *
 */
public class SJF extends Scheduler implements UsesBurstProcesses  {
	
	public void addProcess(BurstProcess bp) {
		processes.add(bp);
	}

	@Override
	public void run() {
		//TODO logic to process averages and such
	}

	@Override
	public void populateProcessList(int size) {
		// TODO logic to autogen processes		
	}

}
