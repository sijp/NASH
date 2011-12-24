/**
 * 
 */
package nash.ass3;

/**
 * @author nadavit, shlomit
 *
 */
public class ItemImpl implements Item {

	private String itemName;
	private int itemAmount;
	private Object lock;
	/* 
	 *
	 */
	@Override
	public boolean takeItem(int amount) {
		synchronized (this.lock) {
			if (this.getAmount() >= amount)
			{
				this.itemAmount = this.itemAmount - amount;
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
	public void returnItems(int amount) {
		/*
		 * check necessity for the synchronization
		 */
		synchronized (this.lock) 
		{
			this.itemAmount = this.itemAmount + amount;
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

}
