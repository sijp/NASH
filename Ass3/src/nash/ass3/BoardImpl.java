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
 * @author Shlomi
 * the board of the game
 */
public class BoardImpl implements Board {

	
	private static Board board=null;
	private static Object lockLists= new Object();
	private Vector<Mission> preassigned, queued, executed, completed;
	
	/**
	 * gets the singleton object
	 * 
	 * @return the board's singleton
	 */
	public static synchronized Board getInstance()
	{
		if (BoardImpl.board==null)
			BoardImpl.board=new BoardImpl();
		return BoardImpl.board;
	}
	
	/**
	 * default constructor
	 */
	private BoardImpl() 
	{
		this.preassigned=new Vector<Mission>();
		this.queued=new Vector<Mission>();
		this.executed=new Vector<Mission>();
		this.completed=new Vector<Mission>();
	}
	
	
	@Override
	public void readProperties(String filename) throws IOException
	{
		FileInputStream in=new FileInputStream(filename);
		Properties p=new Properties();
		p.load(in);
		int numOfMission=Integer.parseInt(p.getProperty("numberOfMissions").trim());
		for (int i=0;i<numOfMission;i++)
		{
			String mName=p.getProperty("m"+i+"Name");
			String mSkill=p.getProperty("m"+i+"Skill");
			int mToc=Integer.parseInt(p.getProperty("m"+i+"Time").trim());
			
			Mission m=new MissionImpl(mName, mSkill, mToc);
			
			String mItems = p.getProperty("m"+i+"Items");
			StringTokenizer items=new StringTokenizer(mItems,",");
			ItemList mItemlist=new ItemList();
			while (items.hasMoreTokens())
			{
				String itemName=items.nextToken().trim();
				int itemAmount = Integer.parseInt(items.nextToken().trim());
				mItemlist.addElement(new ItemImpl(itemName, itemAmount));
			}
			
			m.addRequiredItems(mItemlist);
			
			String mPreMissions=p.getProperty("m"+i+"PreRequisites");
			//String[] mPreMissionsVec=mPreMissions.split(",");
			Vector<String> mPreMissionsVec=new Vector<String>();
			WarSim.LOG.fine("Adding to Mission '"+mName+"' the preMissions: "+mPreMissions);
			
			if (!mPreMissions.isEmpty())
			{
				StringTokenizer preMissions=new StringTokenizer(mPreMissions,", ");
				while (preMissions.hasMoreTokens())
					mPreMissionsVec.addElement(preMissions.nextToken());
					
				this.addPreMissions(m,mPreMissionsVec);
			}
			
					
			WarSim.LOG.fine("number of premissions of "+m.getName()+":" + m.getPreMissions().size());
			int pos=this.preassigned.indexOf(m);
			WarSim.LOG.fine("searching if we encountered "+m.getName()+" already:"+pos);
			if (pos<0)
				this.append(m);
			else
			{
				Mission realMission=this.preassigned.elementAt(pos);
				WarSim.LOG.fine("Mission premission amount before:" + realMission.getPreMissions().size());					
				realMission.update(m);
				WarSim.LOG.fine("Mission premission amount after:" + realMission.getPreMissions().size());
			}
		}
		String s="";
		for (Mission m : this.preassigned)
		{
			s+=m.getName()+":"+m.getPreMissions().size()+"\n";
		}
		WarSim.LOG.fine(s);
	}
	
	/**
	 * 
	 * @param m - mission
	 * @param v - vector of preMissions name
	 */
	private void addPreMissions(Mission m,Vector<String> v)
	{
		for (int i=0;i<v.size();i++)
		{
			Mission pre=new MissionImpl(v.elementAt(i), "", 0);
			int pos=this.preassigned.indexOf(pre);
			if (pos<0)
				this.preassigned.add(pre);
			else
				pre=this.preassigned.elementAt(pos);
			m.addPreMission(pre);
		}
	}
	
	/**
	 * appends a mission to the board
	 * 
	 * @param m - the mission
	 */
	public void append(Mission m)
	{
		this.preassigned.add(m);
	}
	
	/* (non-Javadoc)
	 * @see nash.ass3.Board#getAssignableMissions()
	 * 
	 */
	@Override
	public Vector<Mission> getAssignableMissions()
	{
		Vector<Mission> ret=new Vector<Mission>();
		boolean toAdd=true;
		Mission m;
		for (int i=0;i<this.preassigned.size();i++)
		{
			m=this.preassigned.elementAt(i);

			for (int j=0;j<m.getPreMissions().size() && toAdd;j++)
				if (m.getPreMissions().elementAt(j).getStatus()!=Mission.COMPLETED)
					toAdd=false;
			if (toAdd)
			{
				WarSim.LOG.fine("Found a mission ready to execute:"+m.getName()+
						" size: "+m.getPreMissions().size());
				ret.add(m);
			}
			toAdd=true;
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Board#getCompletedMissions()
	 */
	@Override
	public Vector<Mission> getCompletedMissions() {
		Vector<Mission> completedCopy = new Vector<Mission>();
		synchronized (BoardImpl.lockLists) {
			completedCopy.addAll(this.completed);
		}
		return completedCopy;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Board#getIncompletedMissions()
	 */
	@Override
	public Vector<Mission> getIncompletedMissions() {
		Vector<Mission> ret=new Vector<Mission>();
		synchronized (BoardImpl.lockLists) 
		{
			ret.addAll(this.preassigned);
			ret.addAll(this.queued);
			ret.addAll(this.executed);
		}
		return ret;
	}
	
	@Override
	public boolean markAsComplete(Mission m, Sergeant pepper)
	{
		boolean ret=this.executed.remove(m);
		if (!ret)
			return false;
		m.setStatus(Mission.COMPLETED);
		this.completed.add(m);
		return true;
	}

	@Override
	public void levelUp(Mission m)
	{
		synchronized (BoardImpl.lockLists) 
		{
			if (m.getStatus()==Mission.PREASSIGNED)
			{
				m.setStatus(Mission.QUEUED);
				this.preassigned.remove(m);
				this.queued.add(m);
			}
			else if (m.getStatus()==Mission.QUEUED)
			{
				m.setStatus(Mission.EXECUTED);
				this.queued.remove(m);
				this.executed.add(m);
			}
			else if (m.getStatus()==Mission.EXECUTED)
			{
				m.setStatus(Mission.COMPLETED);
				this.executed.remove(m);
				this.completed.add(m);
			}
		}
	}

	@Override
	public boolean isExist(Mission m) {
		if(this.preassigned.indexOf(m) < 0  && this.queued.indexOf(m) < 0 &&
				this.executed.indexOf(m) < 0 && this.completed.indexOf(m) < 0)
		{
			return false;
		}
		return true;
	}

	@Override
	public Mission getMissionByName(String misName) {
		Mission misExist = new MissionImpl(misName,"", 0);
		if(this.preassigned.indexOf(misExist) >= 0)
			return this.preassigned.elementAt(this.preassigned.indexOf(misExist));
		
		if(this.queued.indexOf(misExist) >= 0)
			return this.queued.elementAt(this.queued.indexOf(misExist));
		
		if(this.executed.indexOf(misExist) >= 0)
			return this.executed.elementAt(this.executed.indexOf(misExist));
		
		if(this.completed.indexOf(misExist) >= 0)
			return this.completed.elementAt(this.completed.indexOf(misExist));
		//if doesn't exist in the bored
		return null;
	}
	@Override
	public boolean isCompleted()
	{
		return this.queued.isEmpty() && this.preassigned.isEmpty() && this.executed.isEmpty();
	}
	
}


