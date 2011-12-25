package nash.ass3;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author Nadav lord of Middle Earth and Shlomi
 *
 */

/*
 * TODO: Note: Mission does not extends comparator anymore. we need 4 types of comparators now
 * each for every queue in the MissionHolder.
 * so we need to give each queue a comparator class (we need 4 of these).
 * right now MissionHolder is broken.
 */

public interface Mission{
	
	public static final String PREASSIGNED="Preassigned", QUEUED="Queued", EXECUTED="Executed", COMPLETED="Completed";
	
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
	public ItemList getRequiredItems();
	
	/*
	 * returns a list (Vector) of Missions' names that are needed to
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
	/*
	 * Adds the specified Mission `m` to the list of missions that are needed to be completed
	 * before this mission can start. 
	 */
	public void addPreMission(Mission m);
	
	/*
	 * returns the time left for completing the mission
	 */
	public int getWorkTimeLeft();

	/*
	 * adds the time i we worked on this mission
	 */
	public void addWorkTime(int i);
	public void update(Mission m);
	
}
