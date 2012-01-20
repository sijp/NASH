/**
 * 
 */
package nash.ass3;

import java.util.Vector;


/**
 * 
 * @author shlomi vs nadav
 *implementation of the mission object
 */

public class MissionImpl implements Mission
{
	String skill;
	String name;
	int timeToCompletion;
	int workedTime;
	ItemList requiredItems;
	Vector<Mission> preMissions;
	String status;
	private String sergeantSignature;
	private Object misLock;
	
	/**
	 * 
	 */
	public MissionImpl()
	{
		this("Anonymous",null,0);
	}
	/**
	 * 
	 * @param _name - the name of the mission
	 * @param _skill - the req skill
	 * @param _toc - the time to complete
	 */ 
	public MissionImpl(String _name,String _skill,int _toc)
	{
		this.name=_name;
		this.skill=_skill;
		this.timeToCompletion=_toc;
		this.requiredItems=new ItemList();
		this.preMissions=new Vector<Mission>();
		this.status=Mission.PREASSIGNED;
		this.sergeantSignature = null;
		this.misLock = new Object();
		this.workedTime=0;
	}
	
	@Override
	public void addRequiredItems(ItemList list)
	{
		this.requiredItems.addAll(list);
	}
	
	@Override 
	public String getSkill() {
		return this.skill;
	}

	@Override 
	public String getName()
	{
		return this.name;
	}

	@Override 
	public int getTimeToCompletion()
	{
		return this.timeToCompletion;
	}
	@Override
	public ItemList getRequiredItems()
	{
		return this.requiredItems;
	}

	@Override 
	public Vector<Mission> getPreMissions()
	{
		return this.preMissions;
	}
	
	@Override 
	public String getStatus()
	{
		return this.status;
	}

	@Override 
	public void setStatus(String _status)
	{
		this.status=_status;
	}
	
	@Override 
	public void addPreMission(Mission m)
	{
		this.preMissions.add(m);
	}
	
	@Override 
	public int getWorkTimeLeft()
	{
		int ret=0;
		synchronized (this.misLock)
		{
			ret=this.timeToCompletion - this.workedTime;
		}
		return ret;
	}

	@Override 
	public void addWorkTime(int i)
	{
		synchronized (this.misLock)
		{
			this.workedTime = this.workedTime + i;
		}
	}

	@Override 
	public void update(Mission m) {
		this.name=m.getName();
		this.skill=m.getSkill();
		this.timeToCompletion=m.getTimeToCompletion();
		this.requiredItems=m.getRequiredItems();
		this.preMissions=m.getPreMissions();
	}
	
	
	
	@Override
	public void print() {
		synchronized (this.misLock) 
		{
			if (this.getStatus() == Mission.COMPLETED)
				this.printCompleted();
			else
				this.printIncompleted();
		}
	}
	/*
	 * prints: mission name,excecutor sergeant name 
	 */
	private void printCompleted()
	{
		System.out.println("Mission Name: " + this.name + " , "
				+ "Sergeant In Charge" + this.sergeantSignature);
	}
	
	/*
	 * prints: mission name, hours left to excecuted, names of pre missions
	 * that aren't completef yet.
	 */
	private void printIncompleted()
	{
		System.out.println("Mission Name: " + this.name + " , " 
				+ "Hours left to completion: " + this.getWorkTimeLeft());
		System.out.println("Premissions: ");
		for(Mission m : this.preMissions)
		{
			synchronized (m.getLock()) {
				if(m.getStatus() != Mission.COMPLETED)
					System.out.print(m.getName() + " ");
			}
		}
		System.out.println("");
	}

	@Override
	public void setSergeant(String serName) {
		this.sergeantSignature = serName;		
	}

	@Override
	public Object getLock() {
		return this.misLock;
	}

	@Override
	public int getNumberOfRequiredItems()
	{
		int sum=0;
		for (Item i : this.requiredItems)
		{
			sum += i.getAmount();
		}
		return sum;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof MissionImpl)
		{
			Mission m=(Mission) o;
			if(m.getName().equals(this.getName()))
				return true;
		}
		return false;
	}
	
}