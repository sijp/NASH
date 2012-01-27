/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public class RepresentationImpl implements Representation {

	private String id;
	private Resource resource;
	private Job generatorJob;
	private Vector<Job> repJobs;
	private boolean ready;
	public RepresentationImpl(String id , Resource resource , 
			Job generatorJob)
	{
		this.id = id;
		this.resource = resource;
		this.generatorJob = generatorJob;
		this.repJobs = new Vector<Job>();
		this.ready = false;
	}
	
	/* (non-Javadoc)
	 * @see nash.ass4.Representation#getId()
	 */
	@Override
	public synchronized String getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Representation#getResource()
	 */
	@Override
	public synchronized Resource getResource() {
		return this.resource;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Representation#getGeneratorJob()
	 */
	@Override
	public synchronized Job getGeneratorJob() {
		return this.generatorJob;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Representation#getRepresentationJobs()
	 */
	@Override
	public Vector<Job> getRepresentationJobs() {
		return this.repJobs;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Representation#setGenaratorJob(nash.ass4.Job)
	 */
	@Override
	public synchronized void setGenaratorJob(Job job) 
	{
		this.generatorJob = job;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Representation#addJob(nash.ass4.Job)
	 */
	@Override
	public synchronized void addJob(Job job) 
	{
		this.repJobs.add(job);
	}
	
	public synchronized boolean isReady()
	{
		return this.ready;
	}
	
	public synchronized void setReady()
	{
		this.ready=true;
	}

}
