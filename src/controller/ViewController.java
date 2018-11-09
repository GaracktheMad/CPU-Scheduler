package controller;

import java.util.ArrayList;
import model.Process;
import model.RoundRobin;
import model.SJF;
import model.SRT;
import model.Scheduler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.ArrivalProcess;
import model.BurstProcess;
import model.FIFO;
import model.InvalidTimeException;
import model.PrioritizedProcess;
import model.Priority;
import view.MainApplicationWindow;
import view.ProcessInfoBox;

/**
 * @author Peter Vukas
 *
 */
public class ViewController {

	/**
	 * 
	 */
	MainApplicationWindow frame;

	/**
	 * 
	 */
	ArrayList<Process> processes;
	
	/**
	 * 
	 */
	ArrayList<PrioritizedProcess> pProcesses;
	ArrayList<BurstProcess> bProcesses;
	ArrayList<ArrivalProcess> aProcesses;

	/**
	 * 
	 */
	String currentAlgorithm;
	
	/**
	 * 
	 */
	Scheduler scheduler;

	/**
	 * @param stage
	 */
	public ViewController(Stage stage) {
		frame = new MainApplicationWindow(new HandleStart(), new HandleRandom(), new HandleAlgorithm(),
				new HandleProcesses());
		currentAlgorithm = frame.selectedProcess();
		processes = new ArrayList<Process>();
		pProcesses = new ArrayList<PrioritizedProcess>();
		bProcesses = new ArrayList<BurstProcess>();
		aProcesses = new ArrayList<ArrivalProcess>();
	}

	/**
	 * @author Peter Vukas
	 *
	 */
	public class HandleStart implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent arg0) {
			if (currentAlgorithm.equals("FIFO") || currentAlgorithm.equals("RR") || currentAlgorithm.equals("SRTF")) {
				processes.clear();
				for (ProcessInfoBox p : frame.processes) {
					try {
						processes.add(new ArrivalProcess(new Process(p.getProcessName(),
								Double.valueOf(p.getBurstTime()), Double.valueOf(p.getArrivalTime()))));
					} catch (NumberFormatException | InvalidTimeException e) {
						break;
					}
				}
			}
		}

	}

	/**
	 * @author Brandon Ruiz
	 *
	 */
	public class HandleRandom implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent arg0) {
			try {
				if (currentAlgorithm.equals("P")) {
					pProcesses.clear();
					for (int i = 0; i < 10; i++) {
						pProcesses.add(new PrioritizedProcess());
					}
					scheduler = new Priority(pProcesses);
					scheduler.run();
				}
				else if (currentAlgorithm.equals("SJF")) {
					bProcesses.clear();
					for (int i = 0; i < 10; i++) {
						bProcesses.add(new BurstProcess());
					}
					scheduler = new SJF(bProcesses);
					scheduler.run();
				}
				else {
					aProcesses.clear();
					for (int i = 0; i < 10; i++) {
						aProcesses.add(new ArrivalProcess());
					}
					if(currentAlgorithm.equals("FIFO")) {
						scheduler = new FIFO(aProcesses);
					}
					else if(currentAlgorithm.equals("SRTF")) {
						scheduler = new SRT(aProcesses);
					}
					else scheduler = new RoundRobin(aProcesses);
					
					scheduler.run();
				}
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @author Peter Vukas
	 *
	 */
	public class HandleAlgorithm implements EventHandler<ActionEvent> {

		/**
		 * @param arg0
		 */
		@Override
		public void handle(ActionEvent arg0) {
			String previousAlgorithm = currentAlgorithm;
			currentAlgorithm = frame.selectedProcess();
			if (currentAlgorithm.equals("RR") ^ previousAlgorithm.equals("RR")) {
				frame.selections.toggleQuantum();
			}
			if (currentAlgorithm.equals("P") ^ previousAlgorithm.equals("P")) {
				ProcessInfoBox.isPriorityMode = !ProcessInfoBox.isPriorityMode;
				frame.refreshProcessList(frame.selections.getNumberOfProcesses());
			}
		}
	}

	/**
	 * @author Peter Vukas
	 *
	 */
	public class HandleProcesses implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent arg0) {
			frame.refreshProcessList(frame.selections.getNumberOfProcesses());
		}

	}
}
