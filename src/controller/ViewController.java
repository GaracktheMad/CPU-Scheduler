package controller;

import java.util.ArrayList;
import java.util.Comparator;

import model.Process;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ArrivalProcess;
import model.BurstProcess;
import model.InvalidTimeException;
import model.PrioritizedProcess;
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
	String currentAlgorithm;

	@Override
	public void start(Stage primaryStage) throws Exception {
		frame = new MainApplicationWindow(new HandleStart(), new HandleRandom(), new HandleAlgorithm(),
				new HandleProcesses());
		currentAlgorithm = frame.selectedProcess();
		Scene scene = new Scene(frame, 400, 400);
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
		@Override
		public void handle(ActionEvent arg0) {
			// If the program is in FIFO, Round Robin, or Shortest Run First mode
			if (currentAlgorithm.equals("FIFO") || currentAlgorithm.equals("RR") || currentAlgorithm.equals("SRTF")) {
				ArrayList<ArrivalProcess> processes = new ArrayList<ArrivalProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {
						processes.add(new ArrivalProcess(new Process(p.getProcessName(),
								Double.valueOf(p.getBurstTime()), Double.valueOf(p.getArrivalTime()))));
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
				if(currentAlgorithm.equals("FIFO")) {
					
				}
			} else if (currentAlgorithm.equals("P")) { // If the program is in priority mode
				ArrayList<PrioritizedProcess> processes = new ArrayList<PrioritizedProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {
						processes.add(
								new PrioritizedProcess(new Process(p.getProcessName(), Double.valueOf(p.getBurstTime()),
										Double.valueOf(p.getArrivalTime())), Short.valueOf(p.getPriority())));
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
			} else if (currentAlgorithm.equals("SJF")) {// If the program is in Shortest Job First mode
				ArrayList<BurstProcess> processes = new ArrayList<BurstProcess>();
				for (ProcessInfoBox p : frame.processes) {
					try {
						processes.add(new BurstProcess(new Process(p.getProcessName(), Double.valueOf(p.getBurstTime()),
								Double.valueOf(p.getArrivalTime()))));
					} catch (NumberFormatException | InvalidTimeException e) {
						return;
					}
				}
				processes.sort(processes.get(0));
			} else {
				return;
			}
		}
	}

	/**
	 * @author Peter Vukas
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
			// TODO Logic for Random button
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
