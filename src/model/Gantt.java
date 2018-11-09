package model;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import view.GanttBox;

/**
 * Gantt chart creation class
 * 
 * @author Brandon Ruiz
 * 
 */
public class Gantt {

	/**
	 * The array of gantt chart sections
	 */
	ArrayList<GanttSection> chart;
	
	/**
	 * The id of the process in the most recent section
	 */
	String lastProcess;

	/**
	 * Initializes the array list
	 */
	public Gantt() {
		chart = new ArrayList<GanttSection>();
	}
	
	/**
	 * Wipes the chart of all data
	 */
	public void clear() {
		chart = new ArrayList<GanttSection>();
		lastProcess = "";
	}

	/**
	 * Add new section to the chart
	 * @param name Name of the process to be displayed
	 * @param time End Time
	 */
	public void addSection(String name, double time) {
		if (!chart.isEmpty())
			chart.get(chart.size() - 1).setEndTime(time);
		chart.add(new GanttSection(name, time));
		lastProcess = name;
	}
	
	/**Check if a section is being changed - for SRT
	 * @param name Name of the process to be checked
	 * @return True if the names are the same
	 */
	public boolean newSection(String name) {
		return lastProcess.equals(name);
	}
	
	/**
	 * Finishes the last section of the chart
	 * 
	 * @param time
	 *            End time
	 */
	public void end(double time) {
		if (!chart.isEmpty())
			chart.get(chart.size() - 1).setEndTime(time);
	}
	
	/**
	 * Displays all elements of the chart
	 */
	public void display() {
		for(int i = 0; i < chart.size(); i++) {
			GanttSection gp = chart.get(i);
			System.out.println(gp.getName() + ":	" + gp.getStartTime() + " - " + gp.getEndTime());
		}
	}
	
//	public HBox createChart() {
//		HBox hb = new HBox();
//		for(GanttSection g: chart) {
//			hb.getChildren().add(new GanttBox(g.getName(), g.getEndTime()).shell);
//		}
//		return hb;
//	}

}