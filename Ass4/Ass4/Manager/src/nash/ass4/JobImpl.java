/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */
public class JobImpl implements Job {

	//TODO: allow jobStatus to be only one of this: preAssigned, Assigned , completed.
	
	private String id;
	private String resourceId;
	private String representationId;
	private String jobXml;
	private String jobStatus;
	
	/* (non-Javadoc)
	 * @see nash.ass4.Job#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getRepresentation()
	 */
	@Override
	public String getRepresentation() {
		return this.representationId;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getResource()
	 */
	@Override
	public String getResource() {
		return this.resourceId;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getXML()
	 */
	@Override
	public String getXML() {
		return this.jobXml;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getStatus()
	 */
	@Override
	public String getStatus() {
		return this.jobStatus;
	}

}
