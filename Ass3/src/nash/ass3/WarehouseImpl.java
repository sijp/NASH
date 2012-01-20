/**
 * 
 */
package nash.ass3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author nadavit, shlomit
 *implementations for warehouse 
 */
public class WarehouseImpl implements Warehouse {
	private static Warehouse theWarehouse = null;
	ItemList closet;
	public static final Object WAITINGFORIMPORTLOCK=new Object();
	private boolean isopen=true;
	/**
	 * returns the singleton instance of the Warehouse.
	 * it's synchronized for thread-safety.
	 * 
	 * @return the singleton of this class
	 */
	public static synchronized Warehouse getInstance()
	{
		if(theWarehouse == null)
			theWarehouse = new WarehouseImpl();
		return theWarehouse;
	}

	/**
	 * 
	 */
	private WarehouseImpl()
	{
		this.closet = new ItemList();
	}
	
	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#useItem(nash.ass3.ItemList)
	 */
	@Override
	public void useItem(ItemList itemsToTake,String serName)
	{
		WarSim.LOG.fine("Sergeant "+serName+" is in the warehouse needs "+itemsToTake.size()+" items");
		for(int i = 0 ; i < itemsToTake.size() && this.isopen ; i++)
		{
			WarSim.LOG.fine("sergeant "+serName+" requests "+itemsToTake.elementAt(i).getName()+"("+itemsToTake.elementAt(i).getAmount()+")");
			int index=this.closet.indexOf(itemsToTake.elementAt(i));
			while (index==-1 && this.isopen)
			{
				synchronized (WarehouseImpl.WAITINGFORIMPORTLOCK) {
					try{
						WarehouseImpl.WAITINGFORIMPORTLOCK.wait();
					}catch(Exception ioe)
					{
						ioe.printStackTrace();
					}
					index=this.closet.indexOf(itemsToTake.elementAt(i));
				}
			}
			if (this.isopen)
			{
				ItemImpl selectedItem =this.closet.elementAt(index);
				
				synchronized (selectedItem.getLock())
				{
					try{
						while(this.isopen && selectedItem.takeItem(itemsToTake.elementAt(i).getAmount(),serName)==false)
						{
							WarSim.LOG.fine("sergeant: " +serName+ " , waiting for item: "
									+selectedItem.getName());
							selectedItem.getLock().wait();
						}
					}catch(InterruptedException ie)
					{
						ie.printStackTrace();
					}
				}
				WarSim.LOG.fine("sergeant "+serName+" took "+itemsToTake.elementAt(i).getName()+"("+itemsToTake.elementAt(i).getAmount()+")");
			}
		}
		//exit for
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#releaseItem(nash.ass3.ItemList)
	 */
	@Override
	public void releaseItem(ItemList itemToReturn,String serName) {
		for(int i = 0 ; i < itemToReturn.size() ; i ++)
		{
			int index = this.closet.indexOf(itemToReturn.elementAt(i));
			if (index!=-1)
			{
				ItemImpl selectedItem = this.closet.elementAt(index);
				synchronized (selectedItem.getLock())
				{
					selectedItem.returnItems(itemToReturn.elementAt(i).getAmount(),serName);
					selectedItem.getLock().notify();
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see nash.ass3.Warehouse#addItem(nash.ass3.ItemImpl)
	 */
	@Override
	public void addItem(ItemImpl newItem) {
		this.closet.addElement(newItem);
		synchronized (WarehouseImpl.WAITINGFORIMPORTLOCK) {
			WarehouseImpl.WAITINGFORIMPORTLOCK.notifyAll();	
		}
		
	}
	/**
	 * 
	 */
	public void stop()
	{
		this.isopen=false;
		synchronized (WarehouseImpl.WAITINGFORIMPORTLOCK) {

			WarehouseImpl.WAITINGFORIMPORTLOCK.notifyAll();	
		}
		for (ItemImpl item : this.closet)
		{
			synchronized (item.getLock()) 
			{
			item.getLock().notifyAll();
			}
		}
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
		int numOfItems=Integer.parseInt(p.getProperty("numberOfItems").trim());
		for (int i=0;i<numOfItems;i++)
		{
			String itemName=p.getProperty("item"+i+"Name");
			int itemAmount=Integer.parseInt(p.getProperty("item"+i+"Amount").trim());
			
			ItemImpl item=new ItemImpl(itemName, itemAmount);
			this.addItem(item);
		}
	}
}
