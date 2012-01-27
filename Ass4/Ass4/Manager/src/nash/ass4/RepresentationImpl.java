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
	/**
	 * 
	 * @param id1 the id
	 * @param resource1 the resource
	 * @param generatorJob1 the generator job
	 */
	public RepresentationImpl(String id1 , Resource resource1 , 
			Job generatorJob1)
	{
		this.id = id1;
		this.resource = resource1;
		this.generatorJob = generatorJob1;
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
	/**
	 * isready
	 * @return boolean
	 */
	@Override
	public synchronized boolean isReady()
	{
		return this.ready;
	}
	
	/**
	 * setready
	 */
	@Override
	public synchronized void setReady()
	{
		this.ready=true;
	}

}
