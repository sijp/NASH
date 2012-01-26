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
	 * adds a new resource to the closet (via Yardena)
	 * and returns it
	 */
	public Resource addNewResource(String mimeType);
	
	/**
	 * 
	 * @param resId
	 * @return a resource by the resId
	 */
	public Resource getResource(String resId);
	
	/**
	 * 
	 * @return a vector of all the resources
	 */
	public Vector<Resource> getResList();
}
