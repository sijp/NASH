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
	 * @return string
	 */
	public String getNewRepresentationId(); 
	/**
	 * 
	 * @return string
	 */
	public String getOriginalMimeType();
	/**
	 * 
	 * @param rep repi
	 * @return File
	 */
	public File getRepresentationFile(String rep);
	
	/**
	 * 
	 * @param rep representation
	 * @return representation
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
	
	/**
	 * 
	 * @param rep representation
	 */
	public void setReady(String rep);
	
}
