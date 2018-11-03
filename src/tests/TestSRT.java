package tests;

import java.util.ArrayList;
import model.Process;
import model.SJF;
import model.SRT;
import model.ArrivalProcess;
import model.BurstProcess;
import model.CalcAverages;
import model.InvalidTimeException;

public class TestSRT {

	public static void main(String args[]) throws InvalidTimeException {
		SRT srt = new SRT();
		srt.populateProcessList(10);
		srt.run();
		
		ArrayList<ArrivalProcess> processes = srt.getProcesses();
		
		System.out.println();
		srt.getGantt().display();
		
		
		System.out.println(srt.averageWait(processes));
		System.out.println(srt.averageTurnAround(processes));

	}

}