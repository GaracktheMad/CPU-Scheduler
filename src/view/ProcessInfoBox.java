package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Process;

/**
 * Creates a HBox filled with all the objects needed to identify a process.
 * 
 * @author Peter Vukas
 *
 */
public class ProcessInfoBox<T extends Process> extends TableView<T> {
	private TableColumn<T, String> processName;
	private TableColumn<T, String> burstTime;
	private TableColumn<T, String> arrivalTime;
	private TableColumn<T, String> priority;
	private TableColumn<T, String> waitTime;
	private TableColumn<T, String> turnAroundTime;
	private final boolean isPriorityMode;

	/**
	 * Initializes all fields and constructs the container
	 */
	public ProcessInfoBox(int numberProcesses) {
		T processDummy = null ;
		processName = new TableColumn<T, String>("T");
		burstTime = new TableColumn<T, String>("Burst Time");
		arrivalTime = new TableColumn<T, String>("Arrival Time");
		priority = new TableColumn<T, String>("Priority");
		waitTime = new TableColumn<T, String>("Wait Time");
		turnAroundTime = new TableColumn<T, String>("Turn Around Time");
		waitTime.setEditable(false);
		turnAroundTime.setEditable(false);
		getColumns().add(processName);
		getColumns().add(burstTime);
		getColumns().add(arrivalTime);
		if(processDummy instanceof model.PrioritizedProcess) {
			isPriorityMode = true;
			getColumns().add(priority);
		}else {
			isPriorityMode = false;
		}
		getColumns().add(waitTime);
		getColumns().add(turnAroundTime);

	}

	
	/**
	 * @return True if the table is in priority mode.
	 */
	public boolean isPriorityMode() {
		return isPriorityMode;
	}

	/**
	 * @param taTime The turn around time of this process to be displayed to the
	 *               user
	 */
	public void setTurnAroundTime(double taTime) {
		turnAroundTime.setText(String.format("%.2f", taTime));
	}

	/**
	 * @param waitTime The wait time of this process to be displayed to the user
	 */
	public void setWaitTime(double waitTime) {
		turnAroundTime.setText(String.format("%.2f", waitTime));
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
		return priority.getText();
	}

	/**
	 * @return The string contained in the arrival time text box
	 */
	public String getArrivalTime() {
		return arrivalTime.getText();
	}

}
