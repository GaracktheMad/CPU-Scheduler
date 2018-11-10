package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A borderpane containing all the application's formatted main elements
 * 
 * @author Peter Vukas
 *
 */
public class MainApplicationWindow extends BorderPane {

	/**
	 * All of the process info hboxes
	 */
	private VBox processList;
	/**
	 * Scroll pane for the process list VBox for larger numbers
	 */
	private ScrollPane scrolling;
	/**
	 * Gantt Chart object which will be filled after calculations
	 */
	private GanttChart chart;
	/**
	 * The selections menu
	 */
	public SelectionsBox selections;
	/**
	 * The top label used in the process list
	 */
	private HBox topLabel;
	/**
	 * The processes contained in the process list
	 */
	public ArrayList<ProcessInfoBox> processes;

	/**
	 * Creates a main application with 10 max processes and a max quantum value of
	 * 10
	 * 
	 * @param maxProcesses        The maximum amount of processes the user can
	 *                            select
	 * @param maxQuantum          The maximum quantum value the user can select
	 * @param start               An event handler for when the "Start" button is
	 *                            clicked
	 * @param randomize           An event handler for when the user requests random
	 *                            burst times
	 * @param algorithmSelection  An event handler for when the user selects a new
	 *                            algorithm
	 * @param quantumSelection    An event handler for when the user selects a new
	 *                            quantum value
	 * @param processNumSelection An event handler for when the number of processes
	 *                            is changed
	 */
	public MainApplicationWindow(EventHandler<ActionEvent> start, EventHandler<ActionEvent> randomize,
			EventHandler<ActionEvent> algorithmSelection, EventHandler<ActionEvent> processNumSelection) {
		selections = new SelectionsBox(10, 10, start, randomize, algorithmSelection, processNumSelection);
		setup();
	}

	/**
	 * Creates a main application with specified max processes and quantum values
	 * 
	 * @param maxProcesses        The total processes the user is expected to select
	 * @param maxQuantum          The max quantum value used for round-robin
	 *                            calculations
	 * @param maxProcesses        The maximum amount of processes the user can
	 *                            select
	 * @param maxQuantum          The maximum quantum value the user can select
	 * @param start               An event handler for when the "Start" button is
	 *                            clicked
	 * @param randomize           An event handler for when the user requests random
	 *                            burst times
	 * @param algorithmSelection  An event handler for when the user selects a new
	 *                            algorithm
	 * @param quantumSelection    An event handler for when the user selects a new
	 *                            quantum value
	 * @param processNumSelection An event handler for when the number of processes
	 *                            is changed
	 */
	public MainApplicationWindow(int maxProcesses, int maxQuantum, EventHandler<ActionEvent> start,
			EventHandler<ActionEvent> randomize, EventHandler<ActionEvent> algorithmSelection,
			EventHandler<ActionEvent> processNumSelection) {
		selections = new SelectionsBox(maxProcesses, maxQuantum, start, randomize, algorithmSelection,
				processNumSelection);
		setup();
	}

	/**
	 * Common things between both constructors to compact code a bit
	 */
	private void setup() {
		processList = new VBox(10);
		scrolling = new ScrollPane(processList);
		topLabel = new HBox(10);
		processes = new ArrayList<ProcessInfoBox>();
		refreshProcessList(1);
		setTop(selections);
		setCenter(scrolling);
		setBottom(chart);
	}

	/**
	 * Resets the contents of process list and fills them with the specified number
	 * of process boxes.
	 * 
	 * @param numberOfProcesses Number of processes to be displayed.
	 */
	public void refreshProcessList(int numberOfProcesses) {
		processList.getChildren().clear();
		processes.clear();
		topLabel.getChildren().clear();
		topLabel.getChildren().addAll(new Label("Processes"), new Label("Burst Time"), new Label("Arrival Time"),
				new Label("Turn Around Time"), new Label("Wait Time"));
		if (ProcessInfoBox.isPriorityMode == true) {
			topLabel.getChildren().add(new Label("Priority"));
		}
		for (int i = 1; i <= numberOfProcesses; i++) {
			processes.add(new ProcessInfoBox());
			processList.getChildren().add(processes.get(i - 1));
		}
	}

	/**
	 * Takes the premade GanttBoxes and makes them into a sorted chart. All objects
	 * to be displayed in this chart should be in this array list as it cannot be
	 * appended later.
	 * 
	 * @param allGanttBoxes An array list of all gantt boxes to be displayed. .sort
	 *                      is called automatically.
	 */
	public void setGanttList(ArrayList<GanttBox> allGanttBoxes) {
		chart = new GanttChart(allGanttBoxes);
	}

	/**
	 * Gives the shorthand of the algorithm the user has selected
	 * 
	 * @return FIFO, SJF, P, RR, SRTF, or "" if nothing is selected
	 */
	public String selectedProcess() {
		if (selections.getAlgorithm().equals("FIRST IN FIRST OUT")) {
			return "FIFO";
		} else if (selections.getAlgorithm().equals("SHORTEST JOB FIRST")) {
			return "SJF";
		} else if (selections.getAlgorithm().equals("PRIORITY")) {
			return "P";
		} else if (selections.getAlgorithm().equals("ROUND ROBIN")) {
			return "RR";
		} else if (selections.getAlgorithm().equals("SHORTEST REMAINING TIME FIRST")) {
			return "SRTF";
		} else {
			return "";
		}
	}
}
