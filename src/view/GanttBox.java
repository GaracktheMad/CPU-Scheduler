/**
 * 
 */
package view;

import java.util.Comparator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author Peter Vukas
 *
 */
public class GanttBox implements Comparator<GanttBox>{
	private Label processName;
	public final BorderPane shell;
	private Label time;
	private final double sortResource;

	/**
	 * @param processN
	 * @param endTime
	 */
	public GanttBox(String processN, double endTime) {
		sortResource = endTime;
		processName = new Label(processN);
		processName.setPrefSize(endTime * 10, 50);
		processName.setMaxSize(endTime * 10, 50);
		time = new Label(String.valueOf(endTime));
		time.setAlignment(Pos.BOTTOM_RIGHT);
		shell = new BorderPane(processName, null, null, time, null);
	}

	@Override
	public int compare(GanttBox arg0, GanttBox arg1) {
		if (arg0.sortResource == arg1.sortResource) {
			return 0;
		} else if (arg0.sortResource < arg1.sortResource) {
			return -1;
		} else {
			return 1;
		}
	}


}
