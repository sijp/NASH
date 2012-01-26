/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public class JobManagerImpl implements JobManager {

	Vector<Job> nonSubmitted, submitted, finished;
	static JobManager jobManager;
	
	public static JobManager getInstance()
	{
		if (jobManager==null)
		{
			jobManager = new JobManagerImpl();
		}
		return jobManager;
	}
	
	private JobManagerImpl()
	{
		this.nonSubmitted = new Vector<Job>();
		this.submitted = new Vector<Job>();
		this.finished = new Vector<Job>();
	}

	@Override
	public void levelUp(Job job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Job getJob(String Id)
	{
		for (Job j : this.nonSubmitted)
			if (j.getId().equals (Id))
				return j;
		return null;
	}

	@Override
	public boolean isNonSubmitted(String res, String rep)
	{
		for (Job j:this.nonSubmitted)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		return false;
	}

	@Override
	public boolean isSubmitted(String res, String rep)
	{
		for (Job j:this.submitted)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		return false;
	}

	@Override
	public boolean isFinished(String res, String rep)
	{
		for (Job j:this.finished)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		return false;
	}
	
	
	@Override
	public Vector<Job> getFinishedJobs()
	{
		return this.finished;
	}
	
	@Override
	public Vector<Job> getNonSubmittedJobs()
	{
		return this.nonSubmitted;
	}

	@Override
	public Vector<Job> getSubmittedJobs()
	{
		return this.submitted;
	}

	public Job getNewJob(String resId,String xml)
	{
		String targetId = ResourceClosetImpl.getInstance().getResource(resId).getNewRepresentationId();
		String id = this.nonSubmitted.size() + this.submitted.size() +
				this.finished.size() + "";
		Job newJob=new JobImpl(id , resId , targetId , xml);
		this.nonSubmitted.add(newJob);
		return newJob;
	}

	@Override
	public Job requestJob() 
	{
		Job assignJob = null;
		for (int i = 0 ; i<this.nonSubmitted.size() ; i++)
		{
			Representation repSrc = ResourceClosetImpl.getInstance().getResource
					(this.submitted.elementAt(i).getResource()).getRepresentation(this.submitted.elementAt(i).getRepresentationSource());
			if (repSrc.isReady())
			{
				assignJob = this.nonSubmitted.elementAt(i);
				this.levelUp(assignJob);
				return assignJob;
			}
		}
		return assignJob;
	}
	


}
