/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public interface Representation 
{
	/**
	 * 
	 * @return string
	 */
	public String getId();
	/**
	 * 
	 * @return a resource
	 */
	public Resource getResource();
	/**
	 * 
	 * @return job
	 */
	public Job getGeneratorJob();
	/**
	 * 
	 * @return vector of jobs
	 */
	public Vector<Job> getRepresentationJobs();
	
	/**
	 * 
	 * @param job incoming
	 */
	public void setGenaratorJob(Job job);
	/**
	 * 
	 * @param job incoming
	 */
	public void addJob(Job job);
	
	/**
	 * 
	 * @return boolean value
	 */
	public boolean isReady();
	
	/**
	 * setting
	 */
	public void setReady();
	
}
