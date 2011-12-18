/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author shlomi
 *
 */
public interface Board {
	/*
	 *  gets the list of missions that were not yet assigned
	 *  
	 */
	public Vector<Mission> getAssignableMissions();
	
	
	/*
	 *  gets the missions that were already completed
	 */
	public Vector<Mission> getCompletedMissions();
	
	
	/*
	 * gets the missions that are completed. NOT! 
	 */
	public Vector<Mission> getIncompletedMissions();
}
