package nash.ass3;
import java.util.Vector;

/**
 * @author Nadav lord of Middle Earth and Shlomi
 *
 */

public interface Mission{
	
	public static final String PREASSIGNED="Preassigned", QUEUED="Queued", EXECUTED="Executed", COMPLETED="Completed";
	
	/**
	 * gets the require skill for this mission.
	 * used to determine whether they can complete this mission.
	 * 
	 * @return the skill of this Mission
	 */
	public String getSkill();
	/**
	 * returns the name of the Mission.
	 * the name is used to distinguish between Missions
	 * 
	 * @return the name
	 */
	public String getName();
	/**
	 * returns the amount of time needed to complete this Mission
	 * 
	 * @return the toc
	 */
	public int getTimeToCompletion();
	/**
	 * returns a hashtable whose keys are items that are needed to
	 * complete the mission and values are Integer's that represents
	 * the amount of each item needed.
	 * Note: Hashtables are thread-safe.
	 * 
	 * @return the required items
	 */
	public ItemList getRequiredItems();
	
	/**
	 * returns a list (Vector) of Missions' names that are needed to
	 * be completed before this mission can be executed.
	 * 
	 * @return the pre missions
	 */
	public Vector<Mission> getPreMissions();
	
	/**
	 * gets the status of this mission (Preassigned, Queued, Executed, Complete)
	 * 
	 * @return the status
	 */
	public String getStatus();
	/**
	 * sets the status of this mission (Preassigned, Queued, Executed, Complete)
	 * 
	 * @param status - the new status for this mission
	 */
	public void setStatus(String status);
	/**
	 * Adds the specified Mission `m` to the list of missions that are needed to be completed
	 * before this mission can start. 
	 * 
	 * @param m - the mission
	 */
	public void addPreMission(Mission m);
	
	/**
	 * returns the time left for completing the mission
	 * 
	 * @return the time left to complete the mission
	 */
	public int getWorkTimeLeft();

	/**
	 * adds the time i we worked on this mission
	 * 
	 * @param i amount of time to add that this mission was worked on
	 */
	public void addWorkTime(int i);

	/**
	 * updates this mission with the data in mission m
	 * @param m - the mission to get the data from
	 */
	public void update(Mission m);
	/**
	 * prints the mission according to the complete and incomplete statush.
	 * 
	 */
	public void print();
	
	/**
	 * adds the in charge sergeant
	 * 
	 * @param serName - sergeant name
	 */
	public void setSergeant(String serName);
	
	/**
	 * returns the lock for the mission
	 * 
	 * @return the lock object
	 */
	public Object getLock();
	/**
	 * adds the items in the itemlist list to the required items list of this mission
	 * 
	 * @param list the list of items to add
	 */
	public void addRequiredItems(ItemList list);
	
	/**
	 * returns the total amount of items required by this mission
	 * if we have 2 helicopters and 1 tank it will return 3.
	 * 
	 * @return how many items needed for this mission
	 */
	public int getNumberOfRequiredItems();
}
