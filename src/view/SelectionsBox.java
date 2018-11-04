package view;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Peter Vukas
 *
 */
public class SelectionsBox {
	private final HBox rows[] = { new HBox(), new HBox(), new HBox(), new HBox() };
	public final VBox container;
	private final ComboBox<String> algorithmBox;
	private final ComboBox<Integer> processNumBox;
	private final ComboBox<Integer> quantumBox;
	private Button randomizerBtn;
	private Button startBtn;
	private final Label whatIsAlgorithmBox;
	private final Label whatIsQuantumBox;
	private final Label whatIsProcessBox;

	public SelectionsBox(int maxProcesses, int maxQuantum) {
		algorithmBox = new ComboBox<String>();
		ArrayList<String> completedAlgorithms = new ArrayList<String>();
		completedAlgorithms.add("First in First Out");
		completedAlgorithms.add("Shortest Job First");
		completedAlgorithms.add("Shortest Remaining Time First");
		// completedAlgorithms.add("Priority" );
		// completedAlgorithms.add("Round Robin");
		algorithmBox.getItems().addAll(completedAlgorithms);
		processNumBox = new ComboBox<Integer>();
		for (int i = 1; i <= maxProcesses; i++) {
			processNumBox.getItems().add(i);
		}
		quantumBox = new ComboBox<Integer>();
		for (int i = 1; i <= maxQuantum; i++) {
			quantumBox.getItems().add(i);
		}
		randomizerBtn = new Button("Autofill All Fields");
		startBtn = new Button("Calculate");
		whatIsAlgorithmBox = new Label("Select an Algorithm to use:");
		whatIsQuantumBox = new Label("Select a quantum value:");
		whatIsProcessBox = new Label("Select the number of processes:");
		rows[0].getChildren().addAll(whatIsAlgorithmBox, algorithmBox);
		rows[1].getChildren().addAll(whatIsProcessBox, processNumBox);
		rows[2].getChildren().addAll(whatIsQuantumBox, quantumBox);
		rows[3].setSpacing(10);
		rows[3].getChildren().addAll(randomizerBtn, startBtn);
		container = new VBox();
		container.getChildren().addAll(rows[0], rows[1], rows[2], rows[3]);
	}

}
