/**
 * 
 */
package nash.ass3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author nadavit, shlomit
 *
 */
public class WarehouseImpl implements Warehouse {
	private static Warehouse theWarehouse = null;
	ItemList closet;
	
	/*
	 * returns the singleton instance of the Warehouse.
	 * it's synchronized for thread-safety.
	 */
	
	public static synchronized Warehouse getInstance()
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
		return this.closet.getCopy();
	}

	
	/*
	 * (non-Javadoc)
	 * @see nash.ass3.Warehouse#readProperties(java.lang.String)
	 */
	@Override
	public void readProperties(String filename) throws IOException
	{
		FileInputStream in=new FileInputStream(filename);
		Properties p=new Properties();
		p.load(in);
		int numOfItems=Integer.parseInt(p.getProperty("numberofItems"));
		for (int i=0;i<numOfItems;i++)
		{
			String itemName=p.getProperty("item"+i+"Name");
			int itemAmount=Integer.parseInt(p.getProperty("item"+i+"Amount"));
			
			ItemImpl item=new ItemImpl(itemName, itemAmount);
			this.addItem(item);
		}
	}
}
