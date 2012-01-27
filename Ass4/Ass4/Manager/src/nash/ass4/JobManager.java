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
	 * 
	 * @param job - job
	 */
	public void levelUp(Job job);
	/**
	 * adds a new job to the appropiate list
	 * @param job - job
	 */
	public void addJob(Job job);
	
	/**
	 * 
	 * @param Id - id
	 * @return -rerurn the id
	 */
	public Job getJob(String Id);
	/**
	 * 
	 * @param res  resi
	 * @param rep  repi
	 * @return -r
	 */
	public boolean isNonSubmitted(String res , String rep);
	/**
	 * 
	 * @param res resi
	 * @param rep repi
	 * @return -r
	 */
	public boolean isSubmitted(String res , String rep);
	/**
	 * 
	 * @param res resi
	 * @param rep  repi
	 * @return ri
	 */
	public boolean isFinished(String res , String rep);
	
	/**
	 * 
	 * @return -r
	 */
	public Vector<Job> getFinishedJobs();
	
	/**
	 * 
	 * @return -r
	 */
	public Vector<Job> getNonSubmittedJobs();

	/**
	 * 
	 * @return -r
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
	
	/**
	 * 
	 * @param resId  idi
	 * @param xml xmli
	 * @return ri 
	 */
	public Job getNewJob(String resId,String xml);
	
}
