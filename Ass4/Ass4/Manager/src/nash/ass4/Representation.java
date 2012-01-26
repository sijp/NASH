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
	public String getId();
	public Resource getResource();
	public Job getGeneratorJob();
	public Vector<Job> getRepresentationJobs();
	
	/**
	 * 
	 * @param job
	 */
	public void setGenaratorJob(Job job);
	/**
	 * 
	 * @param job
	 */
	public void addJob(Job job);
	
	/**
	 * 
	 * @return
	 */
	public boolean isReady();
	
	/**
	 * 
	 */
	public void setReady();
	
}
