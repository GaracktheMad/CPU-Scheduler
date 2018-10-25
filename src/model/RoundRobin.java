package model;

public class RoundRobin extends Scheduler implements UsesArrivalProcesses{
	private double timeQuantum;

	public RoundRobin(double timeQuantum) {super();
		this.timeQuantum = timeQuantum;
	}

	public RoundRobin() {
		super();
		timeQuantum = 5;
	}

	public double getTimeQuantum() {
		return timeQuantum;
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
