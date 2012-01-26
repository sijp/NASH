/**
 * 
 */
package nash.ass4;

import java.util.Vector;

/**
 * @author nadav
 *
 */
public class ResourceClosetImpl implements ResourceCloset {
	
	Vector<Resource> resList;
	private static ResourceCloset resourceCloset; 
	
	public static ResourceCloset getInstance()
	{
		if(ResourceClosetImpl.resourceCloset == null)
		{
			resourceCloset = new ResourceClosetImpl();
		}
		return resourceCloset;
	}
	
	private ResourceClosetImpl()
	{
		this.resList = new Vector<Resource>();
	}
	
	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#addNewResource(java.lang.String)
	 */
	@Override
	public Resource addNewResource(String mimeType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getResource(java.lang.String)
	 */
	@Override
	public Resource getResource(String resId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector<Resource> getResList()
	{
		return this.resList;
	}

}
