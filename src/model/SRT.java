package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * SRT CPU scheduler algorithm
 * 
 * @author Brandon Ruiz
 *
 */
public class SRT {

	/**
	 * The list of processes of which will be popped onto the queue & the queue
	 */
	private ArrayList<ArrivalProcess> newProcesses, queue;

	/**
	 * The gantt chart
	 */
	private Gantt gantt;

	/**
	 * The value of the current CPU time during processing. This value is global so
	 * that it need no frequently be passed to dispatchJob() from getTimes()
	 */
	private double time;

	/**
	 *
	 * @param processes
	 *            Array-list containing all the processes to be run through this
	 *            algorithm
	 */
	public SRT(ArrayList<ArrivalProcess> processes) {
		this.newProcesses = processes;
		queue = new ArrayList<ArrivalProcess>();
	}

	/**
	 * CPU scheduler algorithm & times of processing - Shortest remaining time first
	 * 
	 * @return Return the average values (accumulated value from the ready
	 *         queue/pNum)
	 * @throws InvalidTimeException 
	 */
	public void process() throws InvalidTimeException {
		queue = new ArrayList<ArrivalProcess>(); // Clear the queue
		while (true) {
			if (newProcesses.size() == 0){
				gantt.end((int)time);
				break;
			}
			double newTime = getShortestProcessTime(); // Set new time to next shortest arrival time of the processes
			
			//If the CPU was idle, put that section on the gantt chart and update the time
			if(newTime != time) {
				gantt.addProcess("Idle", (int) time);
				time = newTime;
			}
			
			// All processes of the next shortest arrival time are put on the ready queue to
			// be processed
			dispatchJob();

			/*
			 * Loop through the processes on the ready queue until the decrement function
			 * returns false. This will indicate it is empty
			 */

			while (true) {
				System.out.println(time);
				executeProcess(); // Execute the current process; increment wait and turnaround times of
											// processes appropriately
				time++; // Simulate the passing of time in the CPU by incrementing the time
				dispatchJob(); // Check if any jobs have arrived to be put on the ready queue

				/*
				 * Break out of the loop if the queue is empty. If so and there are more
				 * processes waiting, the program will return to the outer loop. If there are
				 * any more processes left, the time will jump forward to the next shortest
				 * process time to simulate waiting for the next arrival. This is the line time
				 * = getShortestProcessTime();
				 */
				if (queue.size() == 0)
					break;
			}
		}
	}

	//
	/**
	 * Find shortest time of all processes - modify the turnaround time accordingly
	 * 
	 * @return The shortest arrival time
	 */
	public double getShortestProcessTime() {
		double shortestTime = newProcesses.get(0).getArrivalTime();
		for (int i = 1; i < newProcesses.size(); i++) {
			if (newProcesses.get(i).getArrivalTime() < shortestTime)
				shortestTime = newProcesses.get(i).getArrivalTime();
		}
		return shortestTime;
	}
	
	/**
	 * Execute the running process, represented by the process of index 0, by decrementing the burst time
	 * @throws InvalidTimeException 
	 */
	public void executeProcess() throws InvalidTimeException {
		//If the process executing has been completed it is removed
		if(queue.get(0).getBurstTime() == 0) queue.remove(0);
		
		queue.get(0).setBurstTime(queue.get(0).getBurstTime() - 1);	//Decrement the burst time
		queue.get(0).setTurnAroundTime(queue.get(0).getTurnAroundTime() + 1);	//Increment the burst time
		
		//Increment the waiting and turnaround times of all nonexecuting processes in the queue
		for(int i = 1; i < queue.size(); i++) {
			queue.get(i).setTurnAroundTime(queue.get(i).getTurnAroundTime() + 1);
			queue.get(i).setWaitTime(queue.get(i).getWaitTime() + 1);
		}
		
		//Update the gantt chart if the current process has been changed
		if(!gantt.newSection(queue.get(0).getName())) 
			gantt.addProcess(queue.get(0).getName(), (int)time);
		
	}

	/**
	 * Job Dispatcher - put a job on the ready queue once it arrives
	 */
	public void dispatchJob() {
		int i = 0;
		while (i < newProcesses.size()) {
			// If a process' arrival time equals the current CPU time, it is put onto the
			// queue, which is then sorted by burst time
			if (newProcesses.get(i).getArrivalTime() == time) {
				queue.add(newProcesses.remove(i));
				Collections.sort(queue);
			}
			else
				i++;
		}
	}

}