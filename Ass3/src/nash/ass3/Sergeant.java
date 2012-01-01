/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author nadav, shlomi
 *
 */
public interface Sergeant{
	/**
	 * global constants for the priority values.
	 */
	public static final String LONGEST="longestMission", SHORTEST="shortestMission",
			MINITEMS="minItems", MAXITEMS="maxItems";
	
	/**
	 * get the name of the sergeant
	 * 
	 * 
	 * @return the sergeants name
	 */
	public String getName();
	
	
	/**
	 * get the number of threads used by this sergeant
	 * 
	 * @return the number of threads this serg has
	 */
	public int getNumberofThreads();
	
	
	/**
	 * gets the maximum amount of missions.
	 * 
	 * @return max missions
	 */
	public int getMaxMissions();
	
	
	/**
	 * gets the list of available skills.
	 * 
	 * @return the skills
	 */
	public Vector<String> getSkills();
	
	
	/**
	 * get the number of work hours for this sergeant 
	 * 
	 * @return max work hours
	 */
	public int getMaxWorkHours();
	
	
	/**
	 * gets the priority of this sergeant
	 * 
	 * @return the priority
	 */
	public String getPriority();
	
	
	/**
	 * gets the current executed missions
	 * 
	 * @return the current missions
	 */
	public Vector<Mission> getCurrnetMissions();

	/**
	 * adds a new mission to the mission list of this sergeant
	 * 
	 * @param m - the mission
	 */
	public void addMission(Mission m);
	/**
	 * assigns the Mission `m` to the executor 
	 * 
	 * @param m - the mission
	 */
	public void assignMission(Mission m);
	
	
	/**
	 * checks whether this sergeant can take more missions
	 * 
	 * @return true if this sergeant can handle more tasks
	 */
	public boolean isAvailable();
	
	/**
	 * set the maximum number of missions that this sergeant can execute.
	 * 
	 * @param max - sets the maximum amount of mission this serg can handle at once
	 */
	public void setMaxMissions(int max);
	/**
	 * acquire the items the sergeant needs to execute a mission from an item list.
	 * 
	 * @param list the list of items to acquire
	 */
	public void acquireItems(ItemList list);
	
	/**
	 * return items that are not in use
	 * 
	 * @param returnedItems - items to return
	 */
	public void returnItems(ItemList returnedItems);
	
	/**
	 *mark a mission as completed on the bored
	 *and removes it from the sergeant missions
	 * 
	 * @param m - the mission
	 */
	public void completeMission(Mission m);
	
	/**
	 * prints: name, names of all of his missions , time required for completion
	 * for each mission.
	 * 
	 */
	public void print();
	
	/**
	 * kills the sergeant, R.I.P.M.F
	 */
	public void stop();
	
	/**
	 * returns the runflag.
	 * 
	 * @return true if should run
	 */
	public boolean getRunFlag();
}
