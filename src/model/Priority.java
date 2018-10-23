package model;

public class Priority extends Scheduler {

	@Override
	public void run() {
		
	}
	
	@Override
	public void populateProcessList(int size) {
		if(size < 1) {
			size = 10;
		}
		for(int i = 0; i < size; i++) {
			processes.add(new Process());
			//Add logic for priority
		}
	}

}
