package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Creates a HBox filled with all the objects needed to identify a process.
 * 
 * @author Peter Vukas
 *
 */
public class ProcessInfoBox extends HBox {
	/**
	 * Field where the user can enter the name of the process to be specified
	 */
	private TextField processName;
	/**
	 * Field where the user can enter the arrival time of the process to be
	 * specified
	 */
	private TextField arrivalTime;
	/**
	 * Field where the user can enter the burst time of the process to be specified
	 */
	private TextField burstTime;
	/**
	 * Field where the user can enter the priority of the process to be specified
	 */
	private TextField priorityBox;
	/**
	 * Will display the wait time of the process when calculated.
	 */
	private Label waitTime;
	/**
	 * Will display the turn around time of the process when calculated.
	 */
	private Label turnAroundTime;
	/**
	 * A flag used to indicate calculations for priority algorithms are being done.
	 */
	public static boolean isPriorityMode = false;
	private int associatedID;

	/**
	 * Initializes all fields and constructs the container
	 */
	public ProcessInfoBox() {
		setup();
	}

	protected ProcessInfoBox(String name, double burst, double arrival, double wait, double ta, int priority, int id) {
		setup();
		processName.setText(name);
		burstTime.setText(String.valueOf(burst));
		if (isPriorityMode == true) {
			priorityBox.setText(String.valueOf(priority));
		}
		arrivalTime.setText(String.valueOf(arrival));
		waitTime.setText(String.valueOf(wait));
		turnAroundTime.setText(String.valueOf(ta));
		associatedID = id;
	}
	
	protected ProcessInfoBox(String name, double burst, double arrival, String wait, String ta, int priority, int id) {
		setup();
		processName.setText(name);
		burstTime.setText(String.valueOf(burst));
		if (isPriorityMode == true) {
			priorityBox.setText(String.valueOf(priority));
		}
		arrivalTime.setText(String.valueOf(arrival));
		waitTime.setText(wait);
		turnAroundTime.setText(ta);
		associatedID = id;
	}

	private void setup() {
		processName = new TextField();
		burstTime = new TextField();
		priorityBox = new TextField();
		arrivalTime = new TextField();

		if (isPriorityMode == false) {
			priorityBox.setVisible(false);
		}
		waitTime = new Label("Not Calculated"); // States the obvious
		turnAroundTime = new Label("Not Calculated"); // States the obvious
		getChildren().addAll(processName, burstTime, arrivalTime, turnAroundTime, waitTime);
		if (isPriorityMode == true) { // Checks the priority mode flag
			getChildren().add(priorityBox);
		}

		// Brandon time
		processName.setPrefWidth(100);
		burstTime.setPrefWidth(100);
		priorityBox.setPrefWidth(100);
		arrivalTime.setPrefWidth(100);
		setSpacing(25);
		// Brandon time over
	}

	/**
	 * If the isPriorityMode flag has changed, and you don't want to generate a new
	 * object, use this method to activate the priority fields.
	 */
	public void priorityModeHasChanged() {
		if (isPriorityMode == false) { // Checks the priority mode flag
			getChildren().remove(priorityBox);
		} else {
			if (getChildren().contains(priorityBox) == false) {
				getChildren().add(priorityBox);
			}
		}
	}

	/**
	 * @param taTime The turn around time of this process to be displayed to the
	 *               user
	 */
	public void setTurnAroundTime(double taTime) {
		turnAroundTime.setText(String.format("%10.2f", taTime));
	}

	/**
	 * @param waitTime The wait time of this process to be displayed to the user
	 */
	public void setWaitTime(double wTime) {
		waitTime.setText(String.format("%14.2f", wTime));
	}

	/**
	 * @return The string contained in the burst time text box
	 */
	public String getBurstTime() {
		return burstTime.getText();
	}

	/**
	 * @return The string contained in the process name text box
	 */
	public String getProcessName() {
		return processName.getText();
	}

	/**
	 * @return The string contained in the priority text box
	 */
	public String getPriority() {
		return priorityBox.getText();
	}

	/**
	 * @return The string contained in the arrival time text box
	 */
	public String getArrivalTime() {
		return arrivalTime.getText();
	}

	/**
	 * @return the associatedID
	 */
	public int getAssociatedID() {
		return associatedID;
	}

	/**
	 * @param associatedID the associated ID to set
	 */
	public void setAssociatedID(int associatedID) {
		this.associatedID = associatedID;
	}

}
