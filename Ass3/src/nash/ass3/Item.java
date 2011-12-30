/**
 * 
 */
package nash.ass3;

/**
 * @author nadav
 *
 */
public interface Item {
	/*
	 * checks if there is enough amount of this item
	 * if yes, decrease by the amount and return true
	 * else, return false
	 * adds the sergeant that use the item to it's list of sergeants
	 */
	public boolean takeItem(int amount , String serName);
	/*
	 * returns the given amount to this item amount
	 * update the amount used by the sergeant of this item
	 */
	public void returnItems(int amount , String serName);
	/*
	 * returns the amount of this item
	 */
	public int getAmount();
	/*
	 * return the name of this item
	 */
	public String getName();
	
	/*
	 * prints: name, initial amount(max amount), current amount, 
	 * if initial amount > current amount, prints each sergeant name 
	 * that use it and the amount.
	 */
	public void print();
	
	/*
	 * adds amount for existing item
	 */
	public void add(int amount);
}
