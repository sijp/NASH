/**
 * 
 */
package nash.ass4;

import java.io.File;
import java.util.Vector;

/**
 * @author nadav
 *
 */
public class ResourceImpl implements Resource {

	
	String id;
	private Vector<Representation> repList;
	private String mimeType;
	boolean ready;
	
	public ResourceImpl(String id , Representation zero , String mimeType)
	{
		this.id = id;
		this.repList.add(zero);
		this.mimeType = mimeType;
		this.ready=false;
	}
	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getNewResourceId()
	 */
	@Override
	public String getNewRepresentationId() {
		return this.repList.size() +"";
	}



	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getOriginalMimeType(java.lang.String)
	 */
	@Override
	public String getOriginalMimeType() {
		return this.mimeType;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getRepresentationFile(java.lang.String, java.lang.String)
	 */
	@Override
	public File getRepresentationFile(String rep) {
		File repFile = new File("photos/" + this.id + "/" + rep);
		return repFile;
	}
	
	@Override
	public Representation getRepresentation(String rep) {
		return this.repList.elementAt(Integer.parseInt(rep.trim()));
	}
	@Override
	public String getId() {
		return this.id;
	}
	@Override
	public Vector<Representation> getRepList() {
		return this.repList;
	}
	public void setReady()
	{
		this.ready=true;
	}
	
	public boolean isReady()
	{
		return this.ready;
	}
	

}
