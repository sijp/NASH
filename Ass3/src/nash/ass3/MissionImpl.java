/**
 * 
 */
package nash.ass3;

import java.util.Vector;

import javax.sql.rowset.spi.SyncResolver;

/*
 * 
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
	
	public MissionImpl()
	{
		this("Anonymous",null,0);
	}
	
	public MissionImpl(String name,String skill,int toc)
	{
		this.name=name;
		this.skill=skill;
		this.timeToCompletion=toc;
		this.requiredItems=new ItemList();
		this.preMissions=new Vector<Mission>();
		this.status=Mission.PREASSIGNED;
		this.sergeantSignature = null;
		this.misLock = new Object();
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
	public void setStatus(String status)
	{
		this.status=status;
	}
	
	@Override 
	public void addPreMission(Mission m)
	{
		this.preMissions.add(m);
	}
	
	@Override 
	public int getWorkTimeLeft()
	{
		return this.timeToCompletion - this.workedTime;
	}

	@Override 
	public void addWorkTime(int i)
	{
		this.workedTime = this.workedTime + i;
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
	
}