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
public interface Resource {
	/**
	 * 
	 * @return
	 */
	public String getNewRepresentationId(); 
	/**
	 * 
	 * @param resId
	 * @return
	 */
	public String getOriginalMimeType();
	/**
	 * 
	 * @param res
	 * @param rep
	 * @return
	 */
	public File getRepresentationFile(String rep);
	
	/**
	 * 
	 * @param rep
	 * @return
	 */
	public Representation getRepresentation(String rep);
	
	/**
	 * 
	 * @return id of the resource
	 */
	public String getId();
	/**
	 * 
	 * @return a vector of representations of a resource
	 */
	public Vector<Representation> getRepList();
	
	public void setReady(String rep);
	
}
