package controller;

import java.util.ArrayList;
import model.Process;
import model.RoundRobin;
import model.SJF;
import model.SRT;
import model.Scheduler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
public class ViewController extends Application {

	/**
	 * The frame in which all processes take place
	 */
	MainApplicationWindow frame;

	/**
	 * Tracks all processes involved
	 */

	ArrayList<Process> processes;

	/**
	 * 
	 */
	ArrayList<PrioritizedProcess> pProcesses;
	/**
	 * 
	 */
	ArrayList<BurstProcess> bProcesses;
	/**
	 * 
	 */
	ArrayList<ArrivalProcess> aProcesses;

	/**
	 * Algorithm currently being used
	 */
	String currentAlgorithm;

	/**
	 * Scheduling algorithm class
	 */
	@SuppressWarnings("rawtypes")
	Scheduler scheduler;

	@Override
	public void start(Stage primaryStage) throws Exception {
		frame = new MainApplicationWindow(new HandleStart(), new HandleRandom(), new HandleAlgorithm(),
				new HandleProcesses());
		currentAlgorithm = frame.selectedProcess();
		processes = new ArrayList<Process>();
		pProcesses = new ArrayList<PrioritizedProcess>();
		bProcesses = new ArrayList<BurstProcess>();
		aProcesses = new ArrayList<ArrivalProcess>();
		Scene scene = new Scene(frame, 485, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * Logic to link all the data entered in fields to the calculation algorithms
	 * whenever the calculate button is clicked
	 * 
	 * @author Peter Vukas
	 * 
	 */
	public class HandleStart implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent arg0) {
			// If the program is in FIFO, Round Robin, or Shortest Run First mode
			if (currentAlgorithm.equals("FIFO") || currentAlgorithm.equals("RR") || currentAlgorithm.equals("SRTF")) {
				ArrayList<ArrivalProcess> processes = new ArrayList<ArrivalProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {// Converts data to processes
						ArrivalProcess ap = new ArrivalProcess(new Process(p.getProcessName(),
								Double.valueOf(p.getBurstTime()), Double.valueOf(p.getArrivalTime())));
						processes.add(ap);
						p.setAssociatedID(ap.id);
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
				if (currentAlgorithm.equals("FIFO")) {
					scheduler = new FIFO(processes);
				} else if (currentAlgorithm.equals("RR")) {
					scheduler = new RoundRobin(processes, frame.selections.getQuantum());
				} else {
					scheduler = new SRT(processes);
				}
			} else if (currentAlgorithm.equals("P")) { // If the program is in priority mode
				ArrayList<PrioritizedProcess> processes = new ArrayList<PrioritizedProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {// Converts data to processes
						PrioritizedProcess pp = new PrioritizedProcess(new Process(p.getProcessName(),
								Double.valueOf(p.getBurstTime()), Double.valueOf(p.getArrivalTime())),
								Short.valueOf(p.getPriority()));
						processes.add(pp);
						p.setAssociatedID(pp.id);
						scheduler = new Priority(processes);
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
			} else if (currentAlgorithm.equals("SJF")) {// If the program is in Shortest Job First mode
				ArrayList<BurstProcess> processes = new ArrayList<BurstProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {
						BurstProcess bp = new BurstProcess(new Process(p.getProcessName(),
								Double.valueOf(p.getBurstTime()), Double.valueOf(p.getArrivalTime())));
						processes.add(bp);
						p.setAssociatedID(bp.id);
						scheduler = new SJF(processes);
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
			} else {
				return;
			}
			ArrayList<Process> pal = scheduler.run();
			for (ProcessInfoBox pib : frame.processes) {// Converts data to processes
				for (Process p : pal) {
					if (p.id == pib.getAssociatedID()) {
						pib.setWaitTime(p.getWaitTime());
						pib.setTurnAroundTime(p.getTurnAroundTime());
					}
				}
			}
			frame.setAverages(scheduler);
			scheduler.gantt.refresh();
			frame.setGanttList(scheduler.gantt);
		}
	}

	/**
	 * Handles the randomization of data, displays it, and calculates it.
	 * 
	 * @author Brandon Ruiz and Peter Vukas
	 * 
	 */
	public class HandleRandom implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent arg0) {
			Process.resetProcessNameGen();
			try {
				if (currentAlgorithm.equals("P")) {
					pProcesses.clear();
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						pProcesses.add(new PrioritizedProcess());
					}
					scheduler = new Priority(pProcesses);
					ArrayList<PrioritizedProcess> alp = scheduler.run();
					frame.setPrioritizedProcessList(alp);
					frame.setAverages(scheduler);
					scheduler.gantt.refresh();
					frame.setGanttList(scheduler.gantt);
				} else if (currentAlgorithm.equals("SJF")) {
					bProcesses.clear();
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						bProcesses.add(new BurstProcess());
					}
					scheduler = new SJF(bProcesses);
					ArrayList<Process> alp = scheduler.run();
					frame.setProcessList(alp);
					frame.setAverages(scheduler);
					scheduler.gantt.refresh();
					frame.setGanttList(scheduler.gantt);
				} else {
					aProcesses.clear();
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						aProcesses.add(new ArrivalProcess());
					}
					if (currentAlgorithm.equals("FIFO")) {
						scheduler = new FIFO(aProcesses);
						ArrayList<Process> alp = scheduler.run();
						frame.setProcessList(alp);
						frame.setAverages(scheduler);
						scheduler.gantt.refresh();
						frame.setGanttList(scheduler.gantt);
					} else if (currentAlgorithm.equals("SRTF")) {
						scheduler = new SRT(aProcesses);
						ArrayList<Process> alp = scheduler.run();
						frame.setProcessList(alp);
						frame.setAverages(scheduler);
						scheduler.gantt.refresh();
						frame.setGanttList(scheduler.gantt);
					} else {
						scheduler = new RoundRobin(aProcesses, frame.selections.getQuantum());
						ArrayList<Process> alp = scheduler.run();
						frame.setProcessList(alp);
						frame.setAverages(scheduler);
						scheduler.gantt.refresh();
						frame.setGanttList(scheduler.gantt);
					}
				}
			} catch (InvalidTimeException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Handles the changing of algorithm modes.
	 * 
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
	 * Handles a new number of processes being selected
	 * 
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
