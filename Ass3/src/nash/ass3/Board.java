/**
 * 
 */
package nash.ass3;

import java.io.IOException;
import java.util.Vector;

/**
 * @author shlomi,nadavi
 * board of the game
 */
public interface Board {
	
	/**
	 * gets the list of missions that were not yet assigned
	 *   
	 *
	 * @return the assignable missions
	 */
	
	public Vector<Mission> getAssignableMissions();
	
	
	/**
	 *  gets the missions that were already completed
	 *  
	 *  @return vector of missions
	 */
	public Vector<Mission> getCompletedMissions();
	
	
	/**
	 * gets the missions that are completed. NOT!
	 * 
	 *  @return vector of missions
	 */
	public Vector<Mission> getIncompletedMissions();
	
	/**
	 * marks the mission m as completed by the sergeant pepper
	 * 
	 * @param m - the mission to mark 
	 * 
	 * @param pepper - the sergeant who is marking
	 * 
	 * @return true if the operation was successful
	 */
	public boolean markAsComplete(Mission m, Sergeant pepper);
	
	/**
	 * levels up the mission m in this board. that is upgrading its status and moving it to
	 * the next list
	 * 
	 * 
	 * @param m - the mission to level up
	 * 
	 */
	public void levelUp(Mission m);
	/**
	 * check if a mission is already exist in the board
	 * 
	 * @param m the mission to check
	 * 
	 * @return true if exists
	 */
	public boolean isExist(Mission m);
	/**
	 * adds a new mission to the bored
	 * 
	 *  @param m - the mission
	 */
	public void append(Mission m);
	
	/**
	 * returns a mission if exist on the bored
	 * returns null otherwise
	 * 
	 * @param misName - the mission's name to look for
	 * 
	 * @return the Mission
	 */
	public Mission getMissionByName(String misName);
	/**
	 * reads the properties from the properties file specified by filename.
	 * @param filename - the properties file to read from
	 * @throws IOException 
	 */
	public void readProperties(String filename) throws IOException;

	/**
	 * checks if the board has nothing more to do
	 * 
	 * @return true if all missions are completed
	 *  
	 */
	public boolean isCompleted();
}
