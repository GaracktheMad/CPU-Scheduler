package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PrioritizedProcess;
import model.Scheduler;

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
	 * Gantt Chart HBox which will be filled after calculations
	 */
	private HBox chart;
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
	 * Contains gantt chart and avs
	 */
	private VBox bottomSection;
	/**
	 * Contains wait time averages tools
	 */
	private HBox averageWait;
	/**
	 * Contains TA time average tools
	 */
	private HBox averageTA;
	/**
	 * Tells the user what TA time box is
	 */
	private Label tALbl;
	/**
	 * Tells the user what the wait time box is
	 */
	private Label wLbl;
	/**
	 * Displays the value of the average wait rounded to .00
	 */
	private TextField avWaitBox;
	/**
	 * Displays the value of average TA rounded to .00
	 */
	private TextField avTABox;

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

		averageWait = new HBox(5);
		averageTA = new HBox(5);
		wLbl = new Label("Average Wait Time:");
		tALbl = new Label("Average Turn Around Time:");
		avWaitBox = new TextField();
		avTABox = new TextField();
		avWaitBox.setEditable(false);
		avTABox.setEditable(false);
		averageWait.getChildren().addAll(wLbl, avWaitBox);
		averageTA.getChildren().addAll(tALbl, avTABox);
		chart = new GanttChart();

		bottomSection = new VBox(10);
		bottomSection.getChildren().addAll(averageWait, averageTA, chart);

		setTop(selections);
		setCenter(scrolling);
		setBottom(bottomSection);
	}

	/**
	 * Resets the contents of process list and fills them with the specified number
	 * of process boxes.
	 * 
	 * @author Peter Vukas and Brandon Ruiz
	 * @param numberOfProcesses Number of processes to be displayed.
	 */
	public void refreshProcessList(int numberOfProcesses) {
		processList.getChildren().clear();
		processes.clear();
		topLabel.getChildren().clear();

		// Brandon time
		Label processesLabel = new Label("Processes"), burstLabel = new Label("Burst Time"),
				arrivalLabel = new Label("Arrival Time"), turnAroundLabel = new Label("Turnaround"),
				waitLabel = new Label("Wait");

		processesLabel.setPrefWidth(75);
		burstLabel.setPrefWidth(75);
		arrivalLabel.setPrefWidth(75);
		turnAroundLabel.setPrefWidth(75);
		waitLabel.setPrefWidth(75);

		topLabel.getChildren().addAll(processesLabel, burstLabel, arrivalLabel, turnAroundLabel, waitLabel);
		topLabel.setSpacing(25);
		// Brandon time over

		if (ProcessInfoBox.isPriorityMode == true) {
			topLabel.getChildren().add(new Label("Priority"));
		}
		processList.getChildren().add(topLabel);
		for (int i = 1; i <= numberOfProcesses; i++) {
			processes.add(new ProcessInfoBox());
			processList.getChildren().add(processes.get(i - 1));
		}
		
	}

	/**
	 * Populates chart with the processes in the array list
	 * 
	 * @param alp Array List of prioritized processes to be converted into
	 *            ProcessInfoBoxes (populated)
	 */
	public void setPrioritizedProcessList(ArrayList<PrioritizedProcess> alpp) {
		processList.getChildren().clear();
		processes.clear();
		topLabel.getChildren().clear();

		// Brandon time
		Label processesLabel = new Label("Processes"), burstLabel = new Label("Burst Time"),
				arrivalLabel = new Label("Arrival Time"), turnAroundLabel = new Label("Turnaround"),
				waitLabel = new Label("Wait");

		processesLabel.setPrefWidth(75);
		burstLabel.setPrefWidth(75);
		arrivalLabel.setPrefWidth(75);
		turnAroundLabel.setPrefWidth(75);
		waitLabel.setPrefWidth(75);

		topLabel.getChildren().addAll(processesLabel, burstLabel, arrivalLabel, turnAroundLabel, waitLabel);
		topLabel.setSpacing(25);
		// Brandon time over

		ProcessInfoBox.isPriorityMode = true;
		topLabel.getChildren().add(new Label("Priority"));

		processList.getChildren().add(topLabel);
		for (PrioritizedProcess p : alpp) {
			
			String w = String.format("%14.2f", p.getWaitTime());
			String t = String.format("%10.2f", p.getTurnAroundTime());
			
			ProcessInfoBox pib = new ProcessInfoBox(p.getName(), p.getBurstTime(), p.getArrivalTime(), w,
					t, p.getPriority(), p.id);
			processes.add(pib);
			processList.getChildren().add(pib);
		}
		
		
	}

	/**
	 * Populates chart with the processes in the array list
	 * 
	 * @param alp Array List of non-prioritized processes to be converted into
	 *            ProcessInfoBoxes (populated)
	 */
	public void setProcessList(ArrayList<model.Process> alp) {
		processList.getChildren().clear();
		processes.clear();
		topLabel.getChildren().clear();

		// Brandon time
		Label processesLabel = new Label("Processes"), burstLabel = new Label("Burst Time"),
				arrivalLabel = new Label("Arrival Time"), turnAroundLabel = new Label("Turnaround"),
				waitLabel = new Label("Wait");

		processesLabel.setPrefWidth(75);
		burstLabel.setPrefWidth(75);
		arrivalLabel.setPrefWidth(75);
		turnAroundLabel.setPrefWidth(75);
		waitLabel.setPrefWidth(75);

		topLabel.getChildren().addAll(processesLabel, burstLabel, arrivalLabel, turnAroundLabel, waitLabel);
		topLabel.setSpacing(25);
		// Brandon time over

		processList.getChildren().add(topLabel);

		for (model.Process p : alp) {
			
			String w = String.format("%14.2f", p.getWaitTime());
			String t = String.format("%10.2f", p.getTurnAroundTime());
			
			ProcessInfoBox pib = new ProcessInfoBox(p.getName(), p.getBurstTime(), p.getArrivalTime(), w,
					t, -1, p.id);
			processes.add(pib);
			processList.getChildren().add(pib);
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
	public void setGanttList(HBox hb) {
		bottomSection.getChildren().remove(chart);
		chart = hb;
		bottomSection.getChildren().add(hb);
	}

	/**
	 * Displays the averages to the user.
	 * 
	 * @param s Scheduling algorithm that was used
	 */
	public void setAverages(@SuppressWarnings("rawtypes") Scheduler s) {
		avWaitBox.setText(String.format("%.2f", s.getAverageWaitTime()));
		avTABox.setText(String.format("%.2f", s.getAverageTurnAroundTime()));
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
