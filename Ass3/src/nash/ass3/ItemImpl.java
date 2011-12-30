/**
 * 
 */
package nash.ass3;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author nadavit, shlomit
 *
 */
public class ItemImpl implements Item {

	private String itemName;
	private int globalAmount;
	private int itemAmount;
	private Object lock;
	private Hashtable<String, Integer> signedSergeants;
	
	public ItemImpl(String name, int amount)
	{
		this.itemName = name;
		this.globalAmount = amount;
		this.itemAmount = amount;
		this.lock = new Object();
		this.signedSergeants = new Hashtable<String , Integer>();
	}
	
	/* 
	 *
	 */
	@Override
	public boolean takeItem(int amount , String serName) {
		synchronized (this.lock) {
			if (this.getAmount() >= amount)
			{
				this.itemAmount = this.itemAmount - amount;
				Integer itemAmount = this.signedSergeants.get(serName);
				int am=0;
				if (itemAmount!=null)
					am=itemAmount.intValue();
				am+=amount;
				Integer newAmount=new Integer(am);
				this.signedSergeants.put(serName, newAmount);
				return true;
			}
		}
		return false;
	}
	
	public Object getLock()
	{
		return this.lock;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Item#returnItems(int)
	 */
	@Override
	public void returnItems(int amount , String serName) {
		/*
		 * check necessity for the synchronization
		 */
		synchronized (this.lock) 
		{
			this.itemAmount = this.itemAmount + amount;
			Integer itemAmount = this.signedSergeants.get(serName);
			Integer newAmount = new Integer(itemAmount.intValue() - amount);
			if (newAmount.intValue() != 0)
				this.signedSergeants.put(serName, newAmount);
			else
				this.signedSergeants.remove(serName);
		}
	}
	

	/* (non-Javadoc)
	 * @see nash.ass3.Item#getAmount()
	 */
	@Override
	public int getAmount() {
		return this.itemAmount;
	}

	/* (non-Javadoc)
	 * @see nash.ass3.Item#getName()
	 */
	@Override
	public String getName() {
		return this.itemName;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof ItemImpl)
			return this.getName().equals(((ItemImpl)obj).getName());
		else
			return false;
	}

	@Override
	public void print() 
	{
		synchronized (this.lock) 
		{
			System.out.println("Item Name: " + this.itemName + " , " + 
					"Initial amount: " + this.globalAmount + " , " +
					"Current amount: " + this.itemAmount);
			if (this.globalAmount > this.itemAmount)
			{
				Enumeration<String> serIterator = this.signedSergeants.keys();
				while(serIterator.hasMoreElements())
				{
					String serKey = serIterator.nextElement();
					System.out.println("Sergeant name: " + serKey + " , " +
					"Amount in use: " + this.signedSergeants.get(serKey));	
				}
			}
		}
	}

	@Override
	public void add(int amount) {
		synchronized (this.lock) 
		{
		this.globalAmount = this.globalAmount + amount;
		this.itemAmount = this.itemAmount + amount;
		}
	}
	
}
