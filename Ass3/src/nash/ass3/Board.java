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
	
	public boolean markAsComplete(Mission m, Sergeant pepper);
	
	public void levelUp(Mission m);
	/*
	 * check if a mission is already exist in the board
	 */
	public boolean isExist(Mission m);
	/*
	 * adds a new mission to the bored 
	 */
	public void append(Mission m);
	
	/*
	 * returns a mission if exist on the bored
	 * returns null otherwise
	 */
	public Mission getMissionByName(String misName);
}
