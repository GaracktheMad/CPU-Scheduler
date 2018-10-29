package model;

import java.util.ArrayList;

/**
 * Shortest-Job-First algorithm
 * @author 
 *
 */
public class SJF extends Scheduler<BurstProcess>   {
	
	public void addProcess(BurstProcess bp) {
		processes.add(bp);
	}

	@Override
	public void populateProcessList(int size) {
		// TODO logic to autogen processes		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProcess(BurstProcess p) {
		// TODO Auto-generated method stub
		return false;
	}

}
