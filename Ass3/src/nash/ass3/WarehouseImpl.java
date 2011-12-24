/**
 * 
 */
package nash.ass3;

/**
 * @author nadavit, shlomit
 *
 */
public class WarehouseImpl implements Warehouse {
	private static Warehouse theWarehouse = null;
	ItemList closet;
	
	public static Warehouse getInstance()
	{
		if(theWarehouse == null)
			theWarehouse = new WarehouseImpl();
		return theWarehouse;
	}
	
	private WarehouseImpl()
	{
		this.closet = new ItemList();
	}
	
	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#useItem(nash.ass3.ItemList)
	 */
	@Override
	public void useItem(ItemList itemsToTake) {
		for(int i = 0 ; i < itemsToTake.size() ; i++)
		{
			int index=this.closet.indexOf(itemsToTake.elementAt(i));
			ItemImpl selectedItem =this.closet.elementAt(index);
			
			synchronized (selectedItem.getLock())
			{
				try{
					while(!selectedItem.takeItem(itemsToTake.elementAt(i).getAmount()))
					{
						selectedItem.getLock().wait();
					}
				}catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
			
		}
		//exit for
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#releaseItem(nash.ass3.ItemList)
	 */
	@Override
	public void releaseItem(ItemList itemToReturn) {
		for(int i = 0 ; i < itemToReturn.size() ; i ++)
		{
			int index = this.closet.indexOf(itemToReturn.elementAt(i));
			ItemImpl selectedItem = this.closet.elementAt(index);
			selectedItem.returnItems(itemToReturn.elementAt(i).getAmount());
			selectedItem.getLock().notify();
		}

	}

	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#addItem(nash.ass3.ItemImpl)
	 */
	@Override
	public void addItem(ItemImpl newItem) {
		this.closet.addElement(newItem);

	}

	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#getItems()
	 */
	@Override
	public ItemList getItems() {
		return this.closet;
	}

}
