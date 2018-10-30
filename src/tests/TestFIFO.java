package tests;

import model.ArrivalProcess;
import model.FIFO;
import model.InvalidTimeException;
import model.Process;

public class TestFIFO {

	public static void main(String[] args) {
		FIFO test = new FIFO();
		test.populateProcessList(10);
		test.run();
		output(test);
		test.clearProcesses();
		Process.resetProcessNameGen();
		try {
			for (int i = 0; i < 5; i++) {
				test.addProcess(new ArrivalProcess(new Process((i + 1) * 5, i)));
			}

		} catch (InvalidTimeException e) {
			System.out.println("Ya done fucked it up boyo");
		}
		test.run();
		output(test);

	}

	public static void output(FIFO f) {
		for (Process i : f.getProcesses()) {
			System.out.printf("%s arrives at %.2f%nBurst Time: %.2f%nTurnaround Time: %.2f%nWait Time: %.2f%n%n",
					i.getName(), i.getArrivalTime(), i.getBurstTime(), i.getTurnAroundTime(), i.getWaitTime());
		}
		System.out.printf("Average wait: %.2f%nAverage Turn Around: %.2f%n%n", f.getAverageWaitTime(),
				f.getAverageTurnAroundTime());
	}
}
