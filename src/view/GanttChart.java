package view;

import java.util.ArrayList;

import javafx.scene.layout.HBox;

public class GanttChart {
	public final HBox container;

	public GanttChart(ArrayList<GanttBox> ganttBoxes) {
		ganttBoxes.sort(new GanttBox(null, 0));
		container = new HBox();
		for (GanttBox gb : ganttBoxes) {
			container.getChildren().add(gb.shell);
		}
	}

}
