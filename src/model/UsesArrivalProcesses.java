package model;

public interface UsesArrivalProcesses {
	/**
	 * @param ap An Arrival Process to be added to the list of processes to run
	 */
	public void addProcess(ArrivalProcess ap);
	/**
	 * @param ap The arrival process to be removed from the list of processes to run
	 * @return True if the process was found and removed.
	 */
	public boolean removeProcess(ArrivalProcess ap);
	
}
