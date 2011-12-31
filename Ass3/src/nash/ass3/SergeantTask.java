/**
 * 
 */
package nash.ass3;

/**
 * @author nadav , shlomi
 *
 */
public class SergeantTask implements Runnable {

	Mission misToDo;
	Sergeant serToDo;
	
	public SergeantTask(Mission mis, Sergeant ser)
	{
		this.misToDo = mis;
		this.serToDo = ser;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		this.serToDo.acquireItems(misToDo.getRequiredItems());
		int minWorkTime = Math.min(misToDo.getWorkTimeLeft(), serToDo.getMaxWorkHours());
		try
		{
			Thread.sleep( ((long)minWorkTime) * 1000); 
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
		
		misToDo.addWorkTime(minWorkTime);
		serToDo.returnItems(misToDo.getRequiredItems());
		if(misToDo.getWorkTimeLeft() == 0)
			serToDo.completeMission(misToDo);
		else if (serToDo.getRunFlag() == true)
			serToDo.assignMission(misToDo);
	}

}
