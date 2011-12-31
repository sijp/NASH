/**
 * 
 */
package nash.ass3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
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
		for(Sergeant s : this.serList)
		{
			s.stop();
		}
	}

	@Override
	public void readProperties(String filename) throws IOException {
		FileInputStream in=new FileInputStream(filename);
		Properties p=new Properties();
		p.load(in);
		int numberOfSergeants=Integer.parseInt(p.getProperty("numberOfSergeants").trim());
		for (int i=0 ; i<numberOfSergeants ; i++)
		{
			String sName=p.getProperty("s"+i+"Name");
			int numOfTheThreads = Integer.parseInt(p.getProperty("s"+i+"NumOfThreads").trim());
			int maxMissions = Integer.parseInt(p.getProperty("s"+i+"MaxMissions").trim());
			String sSkills = p.getProperty("s"+i+"Skills");
			int sWorkHours = Integer.parseInt(p.getProperty("s"+i+"workHours").trim());
			String sPriority = p.getProperty("s"+i+"PriorityOrder");
			
			StringTokenizer st = new StringTokenizer(sSkills);
			Vector<String> vecSkills = new Vector<String>();
			while(st.hasMoreTokens())
			{
				String skil = st.nextToken(", ");
				vecSkills.add(skil);
			}
			
			Sergeant sisi = new SergeantImpl(sName, sWorkHours, numOfTheThreads, sPriority, vecSkills);
			sisi.setMaxMissions(maxMissions);
			this.addSergeant(sisi);
		}
	}

}
