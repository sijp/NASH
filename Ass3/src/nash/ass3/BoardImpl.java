/**
 * 
 */
package nash.ass3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Shlomi
 *
 */
public class BoardImpl implements Board {

	
	private Vector<Mission> preassigned, queued, executed, completed;
	
	
	public BoardImpl(String filename) throws IOException
	{
		this.preassigned=new Vector<Mission>();
		this.queued=new Vector<Mission>();
		this.executed=new Vector<Mission>();
		this.completed=new Vector<Mission>();
		this.readProperties(filename);
	}
	
	private void readProperties(String filename) throws IOException
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
			//TODO: implement the mission dependency build process
			/*for (int j=0;j<mPreMissionsVec.length;j++)
				m.addPreMission(mPreMissionsVec[j]);
				*/
			
			this.append(m);
		}
	}
	
	/*
	 * appends a mission to the board
	 */
	protected void append(Mission m)
	{
		this.preassigned.add(m);
	}
	
	/* (non-Javadoc)
	 * @see nash.ass3.Board#getAssignableMissions()
	 */
	@Override
	public Vector<Mission> getAssignableMissions() {
		return this.preassigned;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Board#getCompletedMissions()
	 */
	@Override
	public Vector<Mission> getCompletedMissions() {
		return this.completed;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Board#getIncompletedMissions()
	 */
	@Override
	public Vector<Mission> getIncompletedMissions() {
		Vector<Mission> ret=new Vector<Mission>();
		ret.addAll(this.preassigned);
		ret.addAll(this.queued);
		ret.addAll(this.executed);
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
}
