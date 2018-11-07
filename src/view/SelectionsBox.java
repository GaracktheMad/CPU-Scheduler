package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Creates a VBox container with public access. It has all items needed for the
 * selectable section of the program
 * 
 * @author Peter Vukas
 *
 */
public class SelectionsBox extends VBox {
	/**
	 * A list of all the rows of elements, used in constructing the container
	 */
	private final HBox rows[] = { new HBox(), new HBox(), new HBox(), new HBox() };
	/**
	 * A list of all completed algorithms in a selectable form
	 */
	private final ComboBox<String> algorithmBox;
	/**
	 * An easy to enforce list of number of processes the user can select
	 */
	private final ComboBox<Integer> processNumBox;
	/**
	 * An easy to enforce list of quantum values users can select
	 */
	private final ComboBox<Integer> quantumBox;
	/**
	 * When selected, a process list will be autogenerated with random times.
	 */
	private Button randomizerBtn;
	/**
	 * Starts the calculation of the currently produced values.
	 */
	private Button startBtn;
	/**
	 * Label to tell the user what the algorithm box does.
	 */
	private final Label whatIsAlgorithmBox;
	/**
	 * Label to tell the user what the quantum box does.
	 */
	private final Label whatIsQuantumBox;
	/**
	 * Label to tell the user what the process box does.
	 */
	private final Label whatIsProcessBox;

	/**
	 * Creates a selections box with the specified limits.
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
	public SelectionsBox(int maxProcesses, int maxQuantum, EventHandler<ActionEvent> start,
			EventHandler<ActionEvent> randomize, EventHandler<ActionEvent> algorithmSelection,
			EventHandler<ActionEvent> processNumSelection) {
		algorithmBox = new ComboBox<String>();
		algorithmBox.getItems().addAll("First in First Out", "Shortest Job First",
				// "Priority",
				// "Round Robin",
				"Shortest Remaining Time First");
		algorithmBox.setEditable(false); // Ensures the user can only select expected values.
		algorithmBox.getSelectionModel().selectFirst();
		processNumBox = new ComboBox<Integer>();
		for (int i = 1; i <= maxProcesses; i++) {
			processNumBox.getItems().add(i);
		}
		processNumBox.getSelectionModel().selectFirst();
		processNumBox.setEditable(false);// Ensures the user can only select expected values.
		quantumBox = new ComboBox<Integer>();
		for (int i = 1; i <= maxQuantum; i++) {
			quantumBox.getItems().add(i);
		}
		quantumBox.getSelectionModel().selectFirst();
		quantumBox.setEditable(false);// Ensures the user can only select expected values.
		randomizerBtn = new Button("Autofill All Fields");
		startBtn = new Button("Calculate");
		whatIsAlgorithmBox = new Label("Select an Algorithm to use:");
		whatIsQuantumBox = new Label("Select a quantum value:");
		whatIsProcessBox = new Label("Select the number of processes:");

		// Construction of the container
		rows[0].getChildren().addAll(whatIsAlgorithmBox, algorithmBox);
		rows[1].getChildren().addAll(whatIsProcessBox, processNumBox);
		rows[2].getChildren().addAll(whatIsQuantumBox, quantumBox);
		rows[3].setSpacing(10);
		rows[3].getChildren().addAll(randomizerBtn, startBtn);
		getChildren().addAll(rows[0], rows[1], rows[2], rows[3]);
		setActionHandlers(start, randomize, algorithmSelection, processNumSelection);
	}

	/**
	 * Enables or disables the quantum selector based on the opposite of the current
	 * value
	 */
	public void toggleQuantum() {
		rows[3].setDisable(!rows[3].isDisable());
	}

	/**
	 * @return The selected number of processes
	 */
	public int getNumberOfProcesses() {
		return processNumBox.getValue();
	}

	/**
	 * @return The selected quantum value or a -1 if this is called when the quantum
	 *         box is disabled
	 */
	public int getQuantum() {
		if (quantumBox.isDisable() == true) {
			return -1;
		}
		return quantumBox.getValue();
	}

	/**
	 * Gets the algorithm selected by the user
	 * 
	 * @return "FIRST IN FIRST OUT", "SHORTEST JOB FIRST","PRIORITY","ROUND
	 *         ROBIN","SHORTEST REMAINING TIME FIRST"
	 */
	public String getAlgorithm() {
		return algorithmBox.getValue().toUpperCase();
	}

	/**
	 * @param start An event handler for when the "Start" button is clicked
	 */
	public void setStartButtonActionHandler(EventHandler<ActionEvent> start) {
		startBtn.setOnAction(start);
	}

	/**
	 * @param randomize An event handler for when the user requests random burst
	 *                  times
	 */
	public void setRandomizerButtonActionHandler(EventHandler<ActionEvent> randomize) {
		randomizerBtn.setOnAction(randomize);
	}

	/**
	 * @param quantumSelection An event handler for when the user selects a new
	 *                         quantum value
	 */
	public void setQuantumBoxActionHandler(EventHandler<ActionEvent> quantumSelection) {
		quantumBox.setOnAction(quantumSelection);
	}

	/**
	 * @param algorithmSelection An event handler for when the user selects a new
	 *                           algorithm
	 */
	public void setAlgorithmBoxActionHandler(EventHandler<ActionEvent> algorithmSelection) {
		algorithmBox.setOnAction(algorithmSelection);
	}

	/**
	 * @param processNumSelection An event handler for when the number of processes
	 *                            is changed
	 */
	public void setActionHandler(EventHandler<ActionEvent> processNumSelection) {
		processNumBox.setOnAction(processNumSelection);
	}

	/**
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
	public void setActionHandlers(EventHandler<ActionEvent> start, EventHandler<ActionEvent> randomize,
			EventHandler<ActionEvent> algorithmSelection, EventHandler<ActionEvent> processNumSelection) {
		startBtn.setOnAction(start);
		randomizerBtn.setOnAction(randomize);
		algorithmBox.setOnAction(algorithmSelection);
		processNumBox.setOnAction(processNumSelection);
	}
}
