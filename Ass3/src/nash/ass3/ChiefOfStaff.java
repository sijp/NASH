package nash.ass3;

import java.io.IOException;
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
	Vector<Sergeant> getSergeants();
	/*
	 * adds a sergeant to the list of sergeants under the chief of staff
	 */
	void addSergeant(Sergeant pepper);
	
	/*
	 * reads the properties of the properties file
	 */
	void readProperties(String filename) throws IOException;

	/*
	 * stop the thread and all underlying sergeants.
	 */
	void stop();
	
	/*
	 * start the thread and all underlying sergeants.
	 */
	void start();
}
