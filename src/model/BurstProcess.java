package model;

public class BurstProcess extends Process implements Comparable<Process>  {

	/**
	 * @param p
	 *            The process to be compared by burst time
	 * @return 0 if both bursts are equal, -1 if this < p, 1 if this > p
	 */
	@Override
	public int compareTo(Process p) {
		if (getBurstTime() == p.getBurstTime()) {
			return 0;
		} else if (getBurstTime() < p.getBurstTime() ) {
			return -1;
		} else {
			return 1;
		}
	}
}
