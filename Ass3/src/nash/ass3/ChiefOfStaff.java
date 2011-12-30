package nash.ass3;

import java.util.Vector;

/**
 * @author shlomi,nadavi
 *
 */

/*
 * Scans the board element for missions which can be executed.
 * adds thems to the missionholder
 * distributes the missions from the missionholder to different sergeants according
 * to their skills and priorities' order.
 */

public interface ChiefOfStaff extends Runnable{
	/*
	 * gets the list of all sergeants
	 */
	public Vector<Sergeant> getSergeants();
	/*
	 * adds a sergeant to the list of sergeants under the chief of staff
	 */
	public void addSergeant(Sergeant pepper);
	/*
	 * stop the thread and all underlying sergeants.
	 */
	public void stop();
}