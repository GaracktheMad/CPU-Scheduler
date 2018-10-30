package brandon;

import java.util.ArrayList;
import model.Process;
import model.SRT;
import model.ArrivalProcess;
import model.InvalidTimeException;

public class Demo {

	public static void main(String args[]) {
		// ArrivalProcess p1 = new ArrivalProcess(new Process(8,0)),
		// p2 = new ArrivalProcess(new Process(4,1)),
		// p3 = new ArrivalProcess(new Process(9,2)),
		// p4 = new ArrivalProcess(new Process(5,3));
		ArrivalProcess p1, p2, p3, p4, p5;
		try {
			p1 = new ArrivalProcess(new Process(5, 1));
			p2 = new ArrivalProcess(new Process(1, 2));
			p3 = new ArrivalProcess(new Process(3, 3));
			p4 = new ArrivalProcess(new Process(2, 1));
			p5 = new ArrivalProcess(new Process(1, 15));
			ArrayList<ArrivalProcess> processes = new ArrayList<ArrivalProcess>();
			processes.add(p1);
			processes.add(p2);
			processes.add(p3);
			processes.add(p4);
			processes.add(p5);

			SRT test = new SRT(processes);

			double[] results = null;
			results = test.getSRTFTimes();

			/*
			 * System.out.println(p2.wait); System.out.println(p2.turnaround);
			 * System.out.println();
			 */
			System.out.println(results[0]);
			System.out.println(results[1]);
		} catch (InvalidTimeException e) {
			e.printStackTrace();
		}

	}

}