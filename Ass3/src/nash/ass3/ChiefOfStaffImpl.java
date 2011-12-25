/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author shlomi
 *
 */
public class ChiefOfStaffImpl implements ChiefOfStaff {

	private static ChiefOfStaff chiefOfStaff;
	private Vector<Sergeant> serList;
	private Thread theThread;
	private boolean runFlag;
	
	private ChiefOfStaffImpl()
	{
		this.serList=new Vector<Sergeant>();
		this.runFlag=true;
		theThread=new Thread(this);
	}
	
	public void start()
	{
		theThread.start();
	}
	
	/*
	 * returns the singleton instance of the Warehouse.
	 * it's synchronized for thread-safety.
	 */
	public static synchronized ChiefOfStaff getInstance()
	{
		if (ChiefOfStaffImpl.chiefOfStaff==null)
			ChiefOfStaffImpl.chiefOfStaff=new ChiefOfStaffImpl();
		return ChiefOfStaffImpl.chiefOfStaff;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		while (this.runFlag)
		{
			Vector<Mission> assignable=BoardImpl.getInstance().getAssignableMissions();
			MissionHolderImpl.getInstance().insert(assignable);
			
			while (this.runFlag && MissionHolderImpl.getInstance().isEmpty() )
			{
				Sergeant pepper=this.getAvailableSergeant();
				if (pepper!=null)
				{
					Mission m = MissionHolderImpl.getInstance().fetch(pepper);
					if (m!=null)
						this.assignMissionToSergeant(m,pepper);
				}	
				// Reads again the missions from the board.
				assignable=BoardImpl.getInstance().getAssignableMissions();
				MissionHolderImpl.getInstance().insert(assignable);
			}
		}
	}

	private void assignMissionToSergeant(Mission m, Sergeant pepper) {
		BoardImpl.getInstance().levelUp(m);
		pepper.addMission(m);
	}

	private Sergeant getAvailableSergeant() {
		for (int i=0;i<this.serList.size();i++)
			if (this.serList.elementAt(i).isAvailable())
				return this.serList.elementAt(i);
		return null;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.ChiefOfStaff#getSergeants()
	 */
	@Override
	public Vector<Sergeant> getSergeants() {
		return this.serList;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.ChiefOfStaff#addSergeant(nash.ass3.Sergeant)
	 */
	@Override
	public void addSergeant(Sergeant pepper) {
		this.serList.add(pepper);
	}

	/* (non-Javadoc)
	 * @see nash.ass3.ChiefOfStaff#stop()
	 */
	@Override
	public void stop() {
		this.runFlag=false;
	}

}
