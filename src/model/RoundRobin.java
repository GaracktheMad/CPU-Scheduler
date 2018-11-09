package model;

import java.util.ArrayList;

public class RoundRobin extends Scheduler<ArrivalProcess> {
	private double timeQuantum;

	public RoundRobin(double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
	}

	public RoundRobin() {
		super();
		timeQuantum = 5;
	}
	
	public RoundRobin(ArrayList<ArrivalProcess> processes, double timeQuantum) {
		super();
		this.timeQuantum = timeQuantum;
		this.processes = processes;
	}
	
	public RoundRobin(ArrayList<ArrivalProcess> processes) {
		super();
		timeQuantum = 5;
		this.processes = processes;
	}

	public double getTimeQuantum() {
		return timeQuantum;
	}

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

	@Override
	public boolean removeProcess(ArrivalProcess p) {
		// TODO Auto-generated method stub
		return false;
	}
}
