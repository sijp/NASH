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
	public Job getJob(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPreAssigned(String res, String rep) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAssigned(String res, String rep) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCompleted(String res, String rep) {
		// TODO Auto-generated method stub
		return false;
	}


}
