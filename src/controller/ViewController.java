package controller;

import java.util.ArrayList;
import model.Process;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.ArrivalProcess;
import model.InvalidTimeException;
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
	String currentAlgorithm;

	/**
	 * @param stage
	 */
	public ViewController(Stage stage) {
		frame = new MainApplicationWindow(new HandleStart(), new HandleRandom(), new HandleAlgorithm(),
				new HandleProcesses());
		currentAlgorithm = frame.selectedProcess();
		processes = new ArrayList<Process>();
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
