package model;

public class Priority extends Scheduler implements UsesPrioritizedProcesses {

	@Override
	public void run() {
		//TODO
	}
	
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

}
