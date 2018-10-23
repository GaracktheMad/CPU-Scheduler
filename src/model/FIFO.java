package model;

import java.util.LinkedList;

public class FIFO extends Scheduler {
	private LinkedList<Process> readyQueue;

	public FIFO() {super();
	readyQueue = new LinkedList<Process>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
