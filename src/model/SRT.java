package model;

import java.util.ArrayList;

public class SRT extends Scheduler<ArrivalProcess> implements UsesArrivalProcesses {

	@Override
	public void populateProcessList(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}

	@Override
	public ArrayList<ArrivalProcess> getProcesses() {
		ArrayList<ArrivalProcess> returnable = new ArrayList<>();
		for(ArrivalProcess ap: processes) {
			returnable.add(new ArrivalProcess(ap));
		}
		return returnable;
	}

	@Override
	public ArrayList<ArrivalProcess> run() {
		// TODO Auto-generated method stub
		return null;
	}

}
