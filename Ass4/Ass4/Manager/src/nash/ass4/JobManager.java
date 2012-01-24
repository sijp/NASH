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
	public Vector<Job> getJobsByRepresentation(String resource,String representation);
	public void addJob(String resource, String representation, Job j);
	public boolean hasNewJob();
	public boolean isCompleted(String res,String rep);
	public boolean isAssigned(String res,String rep);
	
}
