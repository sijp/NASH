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
		return this.completedJobs;
	}
	
	@Override
	public Vector<Job> getNonSubmittedJobs()
	{
		return this.nonCompletedJobs;
	}

	@Override
	public Vector<Job> getSubmittedJobs()
	{
		return this.submittedJobs;
	}

	public Job getNewJob(String resId,String xml)
	{
		ResourceCloset.getInstance().getResource(resId).
		Job j=new Job();
	}
	


}
