/**
 * 
 */
package nash.ass3;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Shlomi & Nadavi
 */

public interface Warehouse {
	/*
	 * receives an itemlist, with names and amount of the items
	 * takes it from the warehouse
	 */
	public void useItem(ItemList itemsToTake);
	/*
	 * returns items and their amount to the warehouse
	 */
	public void releaseItem(ItemList itemToReturn);
	/*
	 * adds a new item into the warehouse
	 */
	public void addItem(ItemImpl newItem);
	/*
	 * returns an item list with all the current items in the warehouse
	 */
	public ItemList getItems();
	/*
	 * reads the properties from the properties file specified by filename
	 */
	public void readProperties(String filename) throws IOException;
}
