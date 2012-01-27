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
	
	public ResourceImpl(String id , String mimeType)
	{
		this.id = id;
		Representation zero = new RepresentationImpl("0", this, null);
		repList=new Vector<Representation>();
		this.repList.add(zero);
		this.mimeType = mimeType;
		this.ready=false;
	}
	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getNewResourceId()
	 */
	@Override
	public synchronized String getNewRepresentationId()
	{
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
	public synchronized Representation getRepresentation(String rep) {
		return this.repList.elementAt(Integer.parseInt(rep.trim()));
	}
	@Override
	public String getId() {
		return this.id;
	}
	@Override
	public synchronized Vector<Representation>  getRepList() {
		return this.repList;
	}
	public synchronized void setReady(String repId)
	{
		System.out.println("OH HAI I AM "+this.getId());
		System.out.println(repId+":"+this.repList.size());
		this.getRepresentation(repId).setReady();
	}
}
