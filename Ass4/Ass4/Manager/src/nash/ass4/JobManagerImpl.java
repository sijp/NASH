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

	/* (non-Javadoc)
	 * @see nash.ass4.JobManager#getJobsByRepresentation(java.lang.String, java.lang.String)
	 */
	@Override
	public Vector<Job> getJobsByRepresentation(String resource,
			String representation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.JobManager#addJob(java.lang.String, java.lang.String, nash.ass4.Job)
	 */
	@Override
	public void addJob(String resource, String representation, Job j) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see nash.ass4.JobManager#hasNewJob()
	 */
	@Override
	public boolean hasNewJob() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.JobManager#isCompleted(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isCompleted(String res, String rep) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.JobManager#isAssigned(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isAssigned(String res, String rep) {
		// TODO Auto-generated method stub
		return false;
	}

}
