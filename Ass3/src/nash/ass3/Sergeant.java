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
	 * assigns the Mission `m` to this sergeant to be executed 
	 */
	public void assignMission(Mission m);
	
	
	/*
	 * checks whether this sergeant can take more missions
	 */
	public boolean isAvailable();
}
