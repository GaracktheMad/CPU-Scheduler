package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Peter Vukas
 *
 */
public class ProcessInfoBox {
	public final HBox container;
	private TextField processName;
	private TextField burstTime;
	private TextField priorityBox;
	private Label waitTime;
	private Label turnAroundTime;
	public static boolean isPriorityMode = false;

	public ProcessInfoBox() {
		processName = new TextField();
		burstTime = new TextField();
		priorityBox = new TextField();
		if (isPriorityMode == false) {
			priorityBox.setVisible(false);
		}
		waitTime = new Label("Not Calculated");
		turnAroundTime = new Label("Not Calculated");
		container = new HBox();
		container.setSpacing(10);
		container.getChildren().addAll(processName, burstTime, waitTime, turnAroundTime);
		if (isPriorityMode == false) {
			priorityBox.setVisible(false);
		} else {
			container.getChildren().add(priorityBox);
		}
	}

	public void setTurnAroundTime(double taTime) {
		turnAroundTime.setText(String.format("%.2f", taTime));
	}

	public void setWaitTime(double waitTime) {
		turnAroundTime.setText(String.format("%.2f", waitTime));
	}

	public String getBurstTime() {
		return burstTime.getText();
	}

	public String getProcessName() {
		return processName.getText();
	}

	public String getPriority() {
		return priorityBox.getText();
	}

}
