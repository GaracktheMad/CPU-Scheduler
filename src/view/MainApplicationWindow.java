package view;

import java.util.ArrayList;

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
	private SelectionsBox selections;
	/**
	 * The top label used in the process list
	 */
	private HBox topLabel;
	/**
	 * The processes contained in the process list
	 */
	private ArrayList<ProcessInfoBox> processes;

	/**
	 *  Creates a main application with 10 max processes and a max quantum value of 10
	 */
	public MainApplicationWindow() {
		selections = new SelectionsBox(10, 10);
		setup();
	}

	/**
	 * Creates a main application with specified max processes and quantum values
	 * @param maxProcesses The total processes the user is expected to select
	 * @param maxQuantum The max quantum value used for round-robin calculations
	 */
	public MainApplicationWindow(int maxProcesses, int maxQuantum) {
		selections = new SelectionsBox(maxProcesses, maxQuantum);
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
	 * Resets the contents of process list and fills them with the specified number of process boxes.
	 * @param numberOfProcesses Number of processes to be displayed.
	 */
	private void refreshProcessList(int numberOfProcesses) {
		processList.getChildren().clear();
		processes.clear();
		topLabel.getChildren().addAll(new Label("Processes"), new Label("Burst Time"), new Label("Turn Around Time"),
				new Label("Wait Time"));
		if (ProcessInfoBox.isPriorityMode == true) {
			topLabel.getChildren().add(new Label("Priority"));
		}
		for (int i = 1; i <= numberOfProcesses; i++) {
			processes.add(new ProcessInfoBox());
			processList.getChildren().add(processes.get(i - 1));
		}
	}

	/**
	 * Takes the premade GanttBoxes and makes them into a sorted chart. All objects to be displayed in this chart should be in this array list as it cannot be appended later.
	 * @param allGanttBoxes An array list of all gantt boxes to be displayed. .sort is called automatically.
	 */
	public void setGanttList(ArrayList<GanttBox> allGanttBoxes) {
		chart = new GanttChart(allGanttBoxes);
	}

}
