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
	
	public synchronized static ResourceCloset getInstance()
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
	public synchronized Resource addNewResource(String mimeType)
	{
		Resource ret=new ResourceImpl(this.resList.size()+"" ,mimeType);
		this.resList.add(ret);
		return ret;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.ResourceCloset#getResource(java.lang.String)
	 */
	@Override
	public synchronized Resource getResource(String resId)
	{
		int id=Integer.parseInt(resId.trim());
		if (this.resList.size()>id)
			return this.resList.elementAt(id);
		return null;
	}
	
	public synchronized Vector<Resource>	getResList()
	{
		return this.resList;
	}

}
