package view;

import java.util.ArrayList;
import model.PrioritizedProcess;
import model.BurstProcess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import model.ArrivalProcess;

/**
 * A borderpane containing all the application's formatted main elements
 * 
 * @author Peter Vukas
 *
 */
public class MainApplicationWindow extends BorderPane {
	
	private ProcessInfoBox<ArrivalProcess> pibAP;
	private ProcessInfoBox<BurstProcess> pibBP;
	private ProcessInfoBox<PrioritizedProcess> pibPP;

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
	 * @param size
	 * @return
	 */
	public ProcessInfoBox<PrioritizedProcess> activatePriority(int size){
		pibPP = new ProcessInfoBox<PrioritizedProcess>(size);
		scrolling.setContent(pibPP);
		return pibPP;
	}
	/**
	 * @param size
	 * @return
	 */
	public ProcessInfoBox<BurstProcess> activateBurst(int size){
		pibBP = new ProcessInfoBox<BurstProcess>(size);
		scrolling.setContent(pibBP);
		return pibBP;
	}
	/**
	 * @param size
	 * @return
	 */
	public ProcessInfoBox<ArrivalProcess> activateArrival(int size){
		pibAP = new ProcessInfoBox<ArrivalProcess>(size);
		scrolling.setContent(pibBP);
		return pibAP;
	}

	/**
	 * Common things between both constructors to compact code a bit
	 */
	private void setup() {
		scrolling = new ScrollPane();
		setTop(selections);
		setCenter(scrolling);
		setBottom(chart);
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
