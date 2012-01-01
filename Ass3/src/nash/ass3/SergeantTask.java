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
	

	/**
	 * constructor	
	 * @param mis - the mission
	 * @param ser - the sergeant
	 */
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
		this.serToDo.acquireItems(this.misToDo.getRequiredItems());
		int minWorkTime = Math.min(this.misToDo.getWorkTimeLeft(), this.serToDo.getMaxWorkHours());
		WarSim.LOG.fine("Hello I am "+this.serToDo.getName()+" this.mistodo:"+this.misToDo.getWorkTimeLeft()+ "sertodo:"+this.serToDo.getMaxWorkHours());
		try
		{
			WarSim.LOG.fine("starting executing Mission ("+minWorkTime+"):" + this.misToDo.getName());
			final long thousend=1000;
			Thread.sleep( ((long)minWorkTime) * thousend);
			WarSim.LOG.fine("Taking a nap :" + this.misToDo.getName());
		}
		catch(InterruptedException ie)
		{
			System.out.println("Mission:"+this.misToDo.getName()+" Time left:"+this.misToDo.getWorkTimeLeft());
			ie.printStackTrace();
		}
		
		this.misToDo.addWorkTime(minWorkTime);
		this.serToDo.returnItems(this.misToDo.getRequiredItems());
		if(this.misToDo.getWorkTimeLeft() == 0)
		{
			WarSim.LOG.fine("yay we are done! :" + this.misToDo.getName());
			this.serToDo.completeMission(this.misToDo);
		}
		else if (this.serToDo.getRunFlag() == true)
		{
			WarSim.LOG.fine("reassigning the Mission to myself:" + this.misToDo.getName());
			this.serToDo.assignMission(this.misToDo);
		}
	}

}
