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
		WarSim.log.fine("Hello I am "+serToDo.getName()+" mistodo:"+misToDo.getWorkTimeLeft()+ "sertodo:"+serToDo.getMaxWorkHours());
		try
		{
			WarSim.log.fine("starting executing Mission ("+minWorkTime+"):" + this.misToDo.getName());
			final long thousend=1000;
			Thread.sleep( ((long)minWorkTime) * thousend);
			WarSim.log.fine("Taking a nap :" + this.misToDo.getName());
		}
		catch(InterruptedException ie)
		{
			System.out.println("Mission:"+misToDo.getName()+" Time left:"+misToDo.getWorkTimeLeft());
			ie.printStackTrace();
		}
		
		misToDo.addWorkTime(minWorkTime);
		serToDo.returnItems(misToDo.getRequiredItems());
		if(misToDo.getWorkTimeLeft() == 0)
		{
			WarSim.log.fine("yay we are done! :" + this.misToDo.getName());
			serToDo.completeMission(misToDo);
		}
		else if (serToDo.getRunFlag() == true)
		{
			WarSim.log.fine("reassigning the Mission to myself:" + this.misToDo.getName());
			serToDo.assignMission(misToDo);
		}
	}

}
