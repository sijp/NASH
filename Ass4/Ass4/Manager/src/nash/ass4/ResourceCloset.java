/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public interface ResourceCloset 
{
	/**
	 * 
	 * @param mimeType for the types
	 * @return resource
	 */
	public Resource addNewResource(String mimeType);
	
	/**
	 * 
	 * @param resId the id
	 * @return a resource by the resId
	 */
	public Resource getResource(String resId);
	
	/**
	 * 
	 * @return a vector of all the resources
	 */
	public Vector<Resource> getResList();
}
