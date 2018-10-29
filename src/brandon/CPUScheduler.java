package brandon;

import java.util.ArrayList;

import model.ArrivalProcess;
import model.InvalidTimeException;

public class CPUScheduler {
	
	//The list of processes of which will be popped onto the queue
	private ArrayList<ArrivalProcess> processes;	
	
	//The number of processes to be processed
	private int pNum;
	
	//The ready queue
	private ReadyQueue rq;
	
	//The value of the current CPU time during processing
	//This value is global so that it need no frequently be passed to dispatchJob() from getTimes()
	private double time;

	public CPUScheduler(ArrayList<ArrivalProcess> processes) {
		this.processes = processes;
		pNum = processes.size();
		rq = new ReadyQueue();

	}
	
	//CPU scheduler algorithm & times of processing - Shortest remaining time first
	public double[] getSRTFTimes() throws InvalidTimeException {
		rq.clear();	//Clear previous wait and turnaround times to avoid overlap
		while(true) {
			if(processes.size() == 0) break;
			time = getShortestProcessTime();	//Set time to next shortest arrival time of the processes
			
			//All processes of the next shortest arrival time are put on the ready queue to be processed
			dispatchJob();	
			
			/*Loop through the processes on the ready queue until the decrement function returns false.
			 This will indicate it is empty*/
			
			while(true) {
				System.out.println(time);
				time++;	//Simulate the passing of time in the CPU by incrementing the time
				rq.SRTFExecuteProcess();	//Execute the current process; increment wait and turnaround times of processes appropriately
				dispatchJob();	//Check if any jobs have arrived to be put on the ready queue
				
				/*Break out of the loop if the queue is empty.  If so and there are more processes waiting,
				 the program will return to the outer loop.  If there are any more processes left,
				 the time will jump forward to the next shortest process time to simulate waiting for the 
				 next arrival.  This is the line time = getShortestProcessTime();*/
				if(rq.isEmpty())break;		
			}
		}
		//Return the average values (accumulated value from the ready queue/pNum)
		return new double[] {rq.wait/pNum, rq.turnaround/pNum};
	}
	
	//Find shortest time of all processes - modify the turnaround time accordingly
	public double getShortestProcessTime() {
		double shortestTime = processes.get(0).getArrivalTime();
		for(int i = 1; i < processes.size(); i++) {
			if(processes.get(i).getArrivalTime()< shortestTime) shortestTime = processes.get(i).getArrivalTime();
		}
		return shortestTime;
	}
	
	//Job Dispatcher - put a job on the ready queue once it arrives
	public void dispatchJob() {
		int i = 0;
		while(i < processes.size()) {
			//If a process' arrival time equals the current CPU time, it is put onto the queue
			if(processes.get(i).getArrivalTime() == time) rq.addSRTF(processes.remove(i));
			else i++;
		}
	}
	
}