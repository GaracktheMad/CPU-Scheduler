package view;

import java.util.ArrayList;

import javafx.scene.layout.HBox;

/**
 * Converts an array of gantt boxes into a HBox filled with them sorted by their end times
 * @author Peter Vukas
 *
 */
public class GanttChart extends HBox{

	/**
	 * Constructs a gantt chart HBox. Recreating it should be done through creating a new instance again
	 * @param ganttBoxes A list of the ALL gantt boxes to be added into the chart.
	 */
	public GanttChart(ArrayList<GanttBox> ganttBoxes) {
		super();
		getChildren().add(new GanttBox("", 0));
		ganttBoxes.sort(new GanttBox("", 0));
		for (GanttBox gb : ganttBoxes) {
			getChildren().add(gb);
		}
	}

}
