/**
 * 
 */
package nash.ass3;

import java.util.Collections;
import java.util.Vector;

/**
 * @author nadav, shlomi
 *
 */
public class ItemList extends Vector<ItemImpl> 
{
	private static final long serialVersionUID = 1L;
	
	ItemComparator itemComp;
	public ItemList()
	{
		super();
		this.itemComp = new ItemComparator();
	}
	
	@Override
	public void addElement(ItemImpl item)
	{
		if (this.indexOf(item) < 0)
		{
			super.addElement(item);
			Collections.sort(this, itemComp);
		}
		else
			this.elementAt(this.indexOf(item)).add(item.getAmount());
		}
	
	public ItemList getCopy()
	{
		ItemList itemsCopy = new ItemList();
		for (ItemImpl i : this)
		{
			synchronized (i.getLock()) {
				itemsCopy.addElement(i);
			}
		}
		return itemsCopy;
	}
}
