/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author nadav, shlomi
 *
 */
public interface Sergeant extends Runnable{
	/*
	 * global constantin (david) for the priority values.
	 */
	public static final String LONGEST="longestMission", SHORTEST="shortestMission",
			MINITEMS="minItems", MAXITEMS="maxItems";
	
	/*
	 * get the name of the sergeant
	 */
	public String getName();
	
	
	/*
	 * get the number of threads used by this sergeant
	 */
	public int getNumberofThreads();
	
	
	/*
	 * gets the maximum amount of missions.
	 */
	public int getMaxMissions();
	
	
	/*
	 * gets the list of available skills.
	 */
	public Vector<String> getSkills();
	
	
	/*
	 * get the number of work hours for this sergeant 
	 */
	public int getMaxWorkHours();
	
	
	/*
	 * gets the priority of this sergeant
	 */
	public String getPriority();
	
	
	/*
	 * gets the current executed missions
	 */
	public Vector<Mission> getCurrnetMissions();

	/*
	 * adds a new mission to the mission list of this sergeant
	 */
	public void addMission(Mission m);
	/*
	 * assigns the Mission `m` to the executor 
	 */
	public void assignMission(Mission m);
	
	
	/*
	 * checks whether this sergeant can take more missions
	 */
	public boolean isAvailable();
	
	/*
	 * set the maximum number of missions that this sergeant can execute.
	 */
	public void setMaxMissions(int max);
	/*
	 * acquire the items the sergeant needs to execute a mission from an item list.
	 */
	public void acquireItems(ItemList list);
	
	/*
	 * return items that are not in use
	 */
	public void returnItems(ItemList returnedItems);
	
	/*
	 *mark a mission as completed on the bored
	 *and removes it from the sergeant missions
	 */
	public void completeMission(Mission m);
	
}
