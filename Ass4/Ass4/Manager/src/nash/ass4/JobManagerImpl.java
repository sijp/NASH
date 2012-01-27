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
	Object lock=new Object(); 
	
	
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
	public void levelUp(Job job) 
	{
		synchronized (lock) {
			
			if(job.getStatus().equals(JobImpl.NONSUBMITTED))
			{
				job.setStatus(JobImpl.SUBMITTED);
				this.submitted.add(job);
				this.nonSubmitted.remove(job);
			}
			else if (job.getStatus().equals(JobImpl.SUBMITTED))
			{
				job.setStatus(JobImpl.FINISHED);
				this.finished.add(job);
				this.submitted.remove(job);
			}
		}
	}

	@Override
	public void addJob(Job job) 
	{
		synchronized (lock) {
			this.nonSubmitted.add(job);	
		}
		
	}

	@Override
	public Job getJob(String Id)
	{
		synchronized (lock) {
		for (Job j : this.nonSubmitted)
			if (j.getId().equals (Id))
				return j;
		}
		return null;
	}

	@Override
	public boolean isNonSubmitted(String res, String rep)
	{
		synchronized (lock) {
		for (Job j:this.nonSubmitted)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		}
		return false;
	}

	@Override
	public boolean isSubmitted(String res, String rep)
	{
		synchronized (lock) {
		for (Job j:this.submitted)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		}
		return false;
	}

	@Override
	public boolean isFinished(String res, String rep)
	{
		synchronized (lock) {
		for (Job j:this.finished)
			if (j.getResource().equals(res) && j.getRepresentationTarget().equals(rep))
				return true;
		}
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
		Job newJob;
		synchronized (lock) {
			if(ResourceClosetImpl.getInstance().getResource(resId) == null)
				return null;
			
			String targetId = ResourceClosetImpl.getInstance().getResource(resId).getNewRepresentationId();
			String id = this.nonSubmitted.size() + this.submitted.size() +
					this.finished.size() + "";
			newJob=new JobImpl(id , resId , targetId , xml);
			Representation rep = new RepresentationImpl(targetId,
					ResourceClosetImpl.getInstance().getResource(resId),
					newJob);
			System.out.println("adding rep" +targetId+ " to " + resId + " old Size is " +  ResourceClosetImpl.getInstance().getResource(resId).getRepList().size());
			ResourceClosetImpl.getInstance().getResource(resId).getRepList().add(rep);
			System.out.println("new Size is: " + ResourceClosetImpl.getInstance().getResource(resId).getRepList().size());
			this.nonSubmitted.add(newJob);
		}
		return newJob;
	}

	@Override
	public Job requestJob() 
	{
		Job assignJob = null;
		synchronized (lock) {
			
			for (int i = 0 ; i<this.nonSubmitted.size() ; i++)
			{
				Representation repSrc = ResourceClosetImpl.getInstance().getResource
						(this.nonSubmitted.elementAt(i).getResource()).getRepresentation(this.nonSubmitted.elementAt(i).getRepresentationSource());
				if (repSrc.isReady())
				{
					assignJob = this.nonSubmitted.elementAt(i);
					this.levelUp(assignJob);
					return assignJob;
				}
			}
		}
		return assignJob;
	}
	


}
