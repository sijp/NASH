/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author Shlomi, Nadavi
 *
 */
public class MissionImpl implements Mission {
	
	String skill;
	String name;
	int timeToCompletion;
	int workedTime;
	ItemList requiredItems;
	Vector<Mission> preMissions;
	String status;
	
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
	}
	
	public String getSkill() {
		return this.skill;
	}

	public String getName()
	{
		return this.name;
	}

	public int getTimeToCompletion()
	{
		return this.timeToCompletion;
	}

	public ItemList getRequiredItems()
	{
		return this.requiredItems;
	}

	public Vector<Mission> getPreMissions()
	{
		return this.preMissions;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status)
	{
		this.status=status;
	}
	
	public void addPreMission(Mission m)
	{
		this.preMissions.add(m);
	}
	
	public int getWorkTimeLeft()
	{
		return this.timeToCompletion - this.workedTime;
	}
	
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
}