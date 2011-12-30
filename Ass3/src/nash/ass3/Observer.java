/**
 * 
 */
package nash.ass3;

import java.io.IOException;

/**
 * @author Shlomi, Nadavi
 *
 *	
 */
public interface Observer {
	
	public static final String COMPLETEMISSIONS="completeMissions",
							INCOMPLETEMISSIONS="incompleteMissions",
							SERGEANTS="sergeants",
							WAREHOUSE="warehouse",
							ADDMISSION="addMission",
							ADDSERGEANT="addSergeant",
							ADDITEM="addItem",
							STOP="stop";

	
	/*
	 * prints the completed missions
	 */
	public void printCompleteMissions();
	/*
	 * prints the incompleted missions
	 */
	public void printIncompleteMissions();
	/*
	 * prints the sergeants 
	 */
	public void printSergeants();
	/*
	 * prints the items in the warehouse
	 * for each item print its name, initial amount, current amount
	 * and the sergeants' name that use it right now. 
	 */
	public void printItems();
	/*
	 * add the mission described in the cmd to the board
	 */
	public void addMission(String cmd);
	/*
	 * add the sergeant described in the cmd to the board
	 */
	public void addSergeant(String cmd);
	/*
	 * add the item described in the cmd to the board
	 */
	public void addItem(String cmd);
	/*
	 * stops all the threads gracefully
	 */
	public void stop();
	/*
	 * finds which function to execute from the cmd command
	 */
	public void executeCommand(String cmd) throws Exception;
	/*
	 * Start the observer's process
	 */
	public void start() throws IOException;
}
