package tests;

import java.util.ArrayList;

import model.InvalidTimeException;
import model.PrioritizedProcess;
import model.Priority;

public class TestPriority {

	public static void main(String[] args) throws InvalidTimeException {
		Priority ps = new Priority();
		ps.populateProcessList(10);
		ps.run();
		
		ArrayList<PrioritizedProcess> processes = ps.getProcesses();
		
		ps.getGantt().display();
		
		System.out.println(ps.averageWait(processes));
		System.out.println(ps.averageTurnAround(processes));
		
	}

}
