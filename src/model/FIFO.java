package model;

import java.util.LinkedList;

public class FIFO extends Scheduler implements UsesArrivalProcesses {
	private LinkedList<Process> readyQueue;

	public FIFO() {super();
	readyQueue = new LinkedList<Process>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void populateProcessList(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProcess(ArrivalProcess ap) {
		processes.add(ap);
	}
	
	

}
