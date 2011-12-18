package nash.ass3;
import java.util.Hashtable;
import java.util.Vector;

public interface Mission extends Comparable<Mission>{
	/*
	 * gets the require skill for this mission.
	 * used to determine whether they can complete this mission.
	 */
	public String getSkill();
	/*
	 * returns the name of the Mission.
	 * the name is used to distinguish between Missions
	 */
	public String getName();
	/*
	 * returns the amount of time needed to complete this Mission
	 */
	public int getTimeToCompletion();
	/*
	 * returns a hashtable whose keys are items that are needed to
	 * complete the mission and values are Integer's that represents
	 * the amount of each item needed.
	 * Note: Hashtables are thread-safe.
	 */
	public Hashtable<String,Integer> getRequiredItems();
	
	/*
	 * returns a list (Vector) of Missions that are needed to
	 * be completed before this mission can be executed.
	 */
	public Vector<Mission> getPreMissions();
	
	/*
	 * gets the status of this mission (Preassigned, Queued, Executed, Complete)
	 */
	public String getStatus();
	/*
	 * sets the status of this mission (Preassigned, Queued, Executed, Complete)
	 */
	public void setStatus(String status);
}
