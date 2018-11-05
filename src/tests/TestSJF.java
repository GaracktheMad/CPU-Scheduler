package tests;

import model.SJF;

public class TestSJF {
	
	public static void main(String args[]) {
		SJF sjf = new SJF();
		sjf.populateProcessList(10);
		sjf.run();
		
		//ArrayList<BurstProcess> processes = sjf.getProcesses();
		
		System.out.println();
		sjf.getGantt().display();
	}

}
