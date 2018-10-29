package brandon;

import java.util.ArrayList;

import model.ArrivalProcess;
import model.InvalidTimeException;

public class Demo {
	
	public static void main(String args[]) {
		ArrivalProcess p1 = new ArrivalProcess(8,0),
				p2 = new ArrivalProcess(4,1),
				p3 = new ArrivalProcess(9,2),
				p4 = new ArrivalProcess(5,3);
		/*Process p1 = new Process(5,1),
				p2 = new Process(1,2),
				p3 = new Process(3,3),
				p4 = new Process(2,1),
				p5 = new Process(1,15);*/
		ArrayList<ArrivalProcess> processes = new ArrayList<ArrivalProcess>();
		processes.add(p1);
		processes.add(p2);
		processes.add(p3);
		processes.add(p4);
		//processes.add(p5);
		
		CPUScheduler test = new CPUScheduler(processes);
		
		double[] results = null;
		try {
			results = test.getSRTFTimes();
		} catch (InvalidTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*System.out.println(p2.wait);
		System.out.println(p2.turnaround);
		System.out.println();*/
		System.out.println(results[0]);
		System.out.println(results[1]);
	}
	
}