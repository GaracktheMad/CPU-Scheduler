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
import view.GanttBox;
import view.MainApplicationWindow;
import view.ProcessInfoBox;

/**
 * @author Peter Vukas
 *
 */
public class ViewController extends Application {

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
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

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
					scheduler = new RoundRobin(processes);
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
			ArrayList<GanttBox> gantts = new ArrayList<GanttBox>();
			for (Process p : pal) {
				gantts.add(new GanttBox(p.getName(), p.getTurnAroundTime()));
			}
			frame.setAverages(scheduler);
			frame.setGanttList(gantts);
		}
	}

	/**
	 * @author Brandon Ruiz and Peter Vukas
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
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						pProcesses.add(new PrioritizedProcess());
					}
					scheduler = new Priority(pProcesses);
				} else if (currentAlgorithm.equals("SJF")) {
					bProcesses.clear();
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						bProcesses.add(new BurstProcess());
					}
					scheduler = new SJF(bProcesses);
				} else {
					aProcesses.clear();
					for (int i = 0; i < frame.selections.getNumberOfProcesses(); i++) {
						aProcesses.add(new ArrivalProcess());
					}
					if (currentAlgorithm.equals("FIFO")) {
						scheduler = new FIFO(aProcesses);
					} else if (currentAlgorithm.equals("SRTF")) {
						scheduler = new SRT(aProcesses);
					} else
						scheduler = new RoundRobin(aProcesses);
				}
				scheduler.run();
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
