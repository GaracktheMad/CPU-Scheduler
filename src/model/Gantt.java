package model;

import java.util.ArrayList;

/**
 * Gantt chart creation class
 * 
 * @author Brandon Ruiz
 * 
 */

public class Gantt {

	//The array of gantt chart sections
	ArrayList<GanttProcess> chart;
	
	//The id of the process in the most recent section
	String lastProcess;

	public Gantt() {
		chart = new ArrayList<GanttProcess>();
	}
	
	//Wipes the chart clear
	public void clear() {
		chart = new ArrayList<GanttProcess>();
		lastProcess = "";
	}

	//Add new section to the chart
	public void addProcess(String name, int time) {
		if (!chart.isEmpty())
			chart.get(chart.size() - 1).setEndTime(time);
		chart.add(new GanttProcess(name, time));
		lastProcess = name;
	}
	
	//Check if a section is being changed - for SRT
	public boolean newSection(String name) {
		return lastProcess == name;
	}
	
	//Finishes the last section of the chart
	public void end(int time) {
		if (!chart.isEmpty())
			chart.get(chart.size() - 1).setEndTime(time);
	}
	
	//Displays all elements of the chart
	public void display() {
		for(int i = 0; i < chart.size(); i++) {
			GanttProcess gp = chart.get(i);
			System.out.println(gp.getName() + ":	" + gp.getStartTime() + " - " + gp.getEndTime());
		}
	}

}