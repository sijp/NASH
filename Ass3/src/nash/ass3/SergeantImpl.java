/**
 * 
 */
package nash.ass3;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nadav, shlom
 *
 */
public class SergeantImpl implements Sergeant {
	
	ExecutorService missionExecutor;
	private String serName;
	private int maxHours;
	private int maxThread;
	private int maxMission;
	public static final String LONGEST="longestMission", SHORTEST="shortestMission",
			MINITEMS="minItems", MAXITEMS="maxItems";
	private String serPriority;
	private Vector<String> serSkills;
	private Vector<Mission> serMissions;
	private Vector<Mission> allMissions;
	private Object serLock;
	private boolean runFlag = true;
	
	/**
	 * 
	 * @param name - the name
	 * @param _maxHours - max
	 * @param _maxThread - max
	 * @param priority - priority
	 * @param skills - skilss
	 */
	public SergeantImpl(String name, int _maxHours, int _maxThread, String priority,
			Vector<String> skills)
	{
		this.serName = name;
		this.maxHours = _maxHours;
		this.maxThread = _maxThread;
		this.serPriority = priority;
		this.serSkills = skills;
		this.missionExecutor = Executors.newFixedThreadPool(this.maxThread);
		this.maxMission = 0;
		this.serMissions = new Vector<Mission>();
		this.allMissions = new Vector<Mission>();
		this.serLock = new Object();
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getName()
	 */
	@Override
	public String getName() {
		return this.serName;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getNumberofThreads()
	 */
	@Override
	public int getNumberofThreads() {
		return this.maxThread;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getMaxMissions()
	 */
	@Override
	public int getMaxMissions() {
		return this.maxMission;
	}
	
	@Override
	public void setMaxMissions(int max)
	{
		this.maxMission = this.maxMission + max;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getSkills()
	 */
	@Override
	public Vector<String> getSkills() {
		return this.serSkills;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getMaxWorkHours()
	 */
	@Override
	public int getMaxWorkHours() {
		return this.maxHours;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getPriority()
	 */
	@Override
	public String getPriority() {
		return this.serPriority;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#getCurrnetMissions()
	 */
	@Override
	public Vector<Mission> getCurrnetMissions() {
		return this.serMissions;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#assignMission(nash.ass3.Mission)
	 */
	@Override
	public void addMission(Mission m) {
		this.serMissions.add(m);
		m.setSergeant(this.serName);
		this.allMissions.add(m);
		this.assignMission(m);
	}
	
	@Override
	public void completeMission(Mission m)
	{
		BoardImpl.getInstance().levelUp(m);
		this.serMissions.removeElement(m);
	}
	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#assignMission(nash.ass3.Mission)
	 */
	@Override
	public void assignMission(Mission m) {
		SergeantTask task = new SergeantTask(m, this);
		this.missionExecutor.execute(task);
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Sergeant#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		return (this.maxMission > this.serMissions.size());
	}
	
	
	@Override
	public void acquireItems(ItemList list)
	{
		WarehouseImpl.getInstance().useItem(list,this.serName);
	}
	
	@Override
	public void returnItems(ItemList returnedItems)
	{
		WarehouseImpl.getInstance().releaseItem(returnedItems,this.serName);
	}
	
	@Override
	public void print() 
	{
		synchronized (this.serLock) 
		{
			System.out.println("Sergeant name: " + this.serName);
			System.out.println("Sergeant missions: ");
			for(Mission m : this.allMissions)
			{
				System.out.println ("Mission name: " +m.getName() + 
						" Time left to completion: " + m.getWorkTimeLeft());
			}
		}
	}

	@Override
	public void stop() {
		this.runFlag = false;
		this.missionExecutor.shutdown();
	}

	@Override
	public boolean getRunFlag() 
	{
		return this.runFlag;
	}
	
	

}
