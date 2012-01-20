/**
 * 
 */
package nash.ass3;

import java.io.IOException;

/**
 * @author Shlomi & Nadavi
 * interface for the warehouse, holds all the items
 */

public interface Warehouse {
	/**
	 * receives an itemlist, with names and amount of the items
	 * takes it from the warehouse
	 * 
	 * @param itemsToTake items to take
	 * @param serName sergeant name
	 */
	public void useItem(ItemList itemsToTake,String serName);
	/**
	 * returns items and their amount to the warehouse
	 * 
	 * @param itemToReturn items to return
	 * @param serName sergeant name
	 */
	public void releaseItem(ItemList itemToReturn,String serName);
	/**
	 * adds a new item into the warehouse
	 * 
	 * @param newItem - the item to add
	 */
	public void addItem(ItemImpl newItem);
	/**
	 * returns an item list with all the current items in the warehouse
	 * 
	 * @return gets a list of items in the warehouse
	 */
	public ItemList getItems();
	/**
	 * reads the properties from the properties file specified by filename
	 * 
	 * @param filename to read
	 * @throws IOException  if IO exception occurs
	 */
	public void readProperties(String filename) throws IOException;
	
	/**
	 * 
	 */
	public void stop();
}
