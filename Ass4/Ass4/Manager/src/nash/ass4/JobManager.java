/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public interface JobManager {
	/**
	 * change the statush of the job from preAssigned to assigned
	 * or from assigned to completed.
	 * and, move the job to the appropiate list.
	 * @param job
	 */
	public void levelUp(Job job);
	/**
	 * adds a new job to the appropiate list
	 * @param job
	 */
	public void addJob(Job job);
	
	/**
	 * 
	 * @param Id
	 * @return a job by the id
	 */
	public Job getJob(String Id);
	/**
	 * 
	 * @return
	 */
	public boolean isNonSubmitted(String res , String rep);
	/**
	 * 
	 * @return
	 */
	public boolean isSubmitted(String res , String rep);
	/**
	 * 
	 * @return
	 */
	public boolean isFinished(String res , String rep);
	
	/**
	 * 
	 * @return
	 */
	public Vector<Job> getFinishedJobs();
	
	/**
	 * 
	 * @return
	 */
	public Vector<Job> getNonSubmittedJobs();

	/**
	 * 
	 * @return
	 */
	public Vector<Job> getSubmittedJobs();
	
	
	/**
	 * request a new job.
	 * the job is pulled from the nonsubmitted jobs
	 * and moved to the submitted jobs
	 * and returned.
	 * @return a nonsubmitted job
	 */
	public Job requestJob();
	
	public Job getNewJob(String resId,String xml);
	
}
