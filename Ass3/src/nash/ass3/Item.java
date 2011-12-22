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
	 */
	public boolean takeItem(int amount);
	/*
	 * retuens the given amount to this item amount
	 */
	public void returnItems(int amount);
	/*
	 * returns the amount of this item
	 */
	public int getAmount();
	/*
	 * return the name of this item
	 */
	public String getName();

}
