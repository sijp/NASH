/**
 * 
 */
package nash.ass3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.sql.rowset.spi.SyncResolver;

/**
 * @author Shlomi
 *
 */
public class BoardImpl implements Board {

	
	private static Board board=null;
	private static Object lockLists= new Object();
	private Vector<Mission> preassigned, queued, executed, completed;
	
	
	public static synchronized Board getInstance()
	{
		if (BoardImpl.board==null)
			BoardImpl.board=new BoardImpl();
		return BoardImpl.board;
	}
	
	private BoardImpl() 
	{
		this.preassigned=new Vector<Mission>();
		this.queued=new Vector<Mission>();
		this.executed=new Vector<Mission>();
		this.completed=new Vector<Mission>();
	}
	
	public void readProperties(String filename) throws IOException
	{
		FileInputStream in=new FileInputStream(filename);
		Properties p=new Properties();
		p.load(in);
		int numOfMission=Integer.parseInt(p.getProperty("numberOfMissions"));
		for (int i=0;i<numOfMission;i++)
		{
			String mName=p.getProperty("m"+i+"Name");
			String mSkill=p.getProperty("m"+i+"skill");
			int mToc=Integer.parseInt(p.getProperty("m"+i+"Time"));
			
			Mission m=new MissionImpl(mName, mSkill, mToc);
			String mPreMissions=p.getProperty("m"+i+"PreRequisites");
			String[] mPreMissionsVec=mPreMissions.split(",");
			this.addPreMissions(m,mPreMissionsVec);
			int pos=this.preassigned.indexOf(m);
			if (pos<0)
				this.append(m);
			else
			{
				Mission realMission=this.preassigned.elementAt(pos);
				realMission.update(m);
			}
		}
	}
	
	private void addPreMissions(Mission m,String [] v)
	{
		for (int i=0;i<=v.length;i++)
		{
			Mission pre=new MissionImpl(v[i], "", 0);
			int pos=this.preassigned.indexOf(pre);
			if (pos<0)
				this.preassigned.add(pre);
			else pre=this.preassigned.elementAt(pos);
			m.addPreMission(pre);
		}
	}
	
	/*
	 * appends a mission to the board
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

			for (int j=0;j<m.getPreMissions().size();j++)
				if (m.getPreMissions().elementAt(j).getStatus()!=Mission.COMPLETED)
					toAdd=false;
			if (toAdd)
				ret.add(m);
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
}
