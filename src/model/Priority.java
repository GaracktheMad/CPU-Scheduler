package model;

import java.util.ArrayList;

public class Priority extends Scheduler<PrioritizedProcess>  {
	
	@Override
	public void populateProcessList(int size) {
		if(size < 1) {
			size = 10;
		}
		for(int i = 0; i < size; i++) {
			processes.add(new PrioritizedProcess());
			//TODO Add logic for priority
		}
	}

	@Override
	public void addProcess(PrioritizedProcess pp) {
		processes.add(pp);
	}

	@Override
	public ArrayList<PrioritizedProcess> getProcesses() {
		ArrayList<PrioritizedProcess> returnable = new ArrayList<>();
		for(PrioritizedProcess pp: processes) {
			returnable.add(new PrioritizedProcess(pp));
		}
		return returnable;
	}

	@Override
	public ArrayList<PrioritizedProcess> run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProcess(PrioritizedProcess p) {
		// TODO Auto-generated method stub
		return false;
	}

}
