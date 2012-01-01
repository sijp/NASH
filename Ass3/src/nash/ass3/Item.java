package nash.ass3;

/**
 * @author Nadav and Shlomi
 *
 */
public interface Item {
	
	/**
	 * checks if there is enough amount of this item
	 * 
	 * if yes, decrease by the amount and return true
	 * else, return false
	 * adds the sergeant that use the item to it's list of sergeants
	 * 
	 * @param amount the amount
	 * @param serName the sergeants name
	 * @return boolean
	 */
	
	public boolean takeItem(int amount , String serName);
	/**
	 * returns the given amount to this item amount
	 * update the amount used by the sergeant of this item
	 * 
	 * @param amount amount
	 * @param serName name of sergeant
	 */
	
	void returnItems(int amount , String serName);
	/**
	 * returns the amount of this item
	 * 
	 * @return amount of items
	 */
	int getAmount();
	/**
	 * return the name of this item
	 * 
	 * @return items name
	 */
	String getName();
	
	/**
	 * prints: name, initial amount(max amount), current amount, 
	 * if initial amount > current amount, prints each sergeant name 
	 * that use it and the amount.
	 * 
	 */
	void print();
	
	/**
	 * adds amount for existing item
	 * 
	 * @param amount the amount to add
	 */
	void add(int amount);
}
