/**
 * 
 */
package view;

import java.util.Comparator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

/**
 * A box which is used in constructing a Gantt chart
 * 
 * @author Peter Vukas
 *
 */
public class GanttBox extends BorderPane implements Comparator<GanttBox> {
	/**
	 * The process name in this box
	 */
	private Label processName;
	/**
	 * The time at which this process ends
	 */
	private Label time;
	/**
	 * The end time is stored into this variable for easy access by the comparator
	 */
	private final double sortResource;
	/**
	 * A rectangle whose width = endTime * 5
	 */
	private Rectangle ganttBox;

	/**
	 * NOTE: For any sort of preemption algorithm, assigh each time chunk for a
	 * process it's own box.
	 * 
	 * @param processN
	 *            The process name in this box
	 * @param endTime
	 *            The time at which this process ends
	 */
	public GanttBox(String processN, double endTime) {
		sortResource = endTime;
		processName = new Label(processN);
		processName.setAlignment(Pos.TOP_CENTER);
		time = new Label(String.valueOf(endTime));
		time.setAlignment(Pos.BOTTOM_RIGHT);
		ganttBox = new Rectangle();
		ganttBox.setWidth(endTime * 5);
		ganttBox.setHeight(10);
		setTop(processName);
		setCenter(ganttBox);
		setBottom(time);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
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
