/**
 * 
 */
package nash.ass3;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @author Shlomi, Nadavi
 *
 */
public class MissionImpl implements Mission {
	
	String skill;
	String name;
	int timeToCompletion;
	Hashtable<String,Integer> requiredItems;
	Vector<String> preMissions;
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
		this.requiredItems=new Hashtable<String,Integer>();
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

	public Hashtable<String, Integer> getRequiredItems()
	{
		return this.requiredItems;
	}

	public Vector<String> getPreMissions()
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
	
	public void addPreMission(String mName)
	{
		this.preMissions.add(m);
	}
}