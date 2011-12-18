/**
 * 
 */
package nash.ass3;

import java.util.Vector;

/**
 * @author Shlomi & Nadavi
 */

public interface Warehouse {
	public boolean useItem(String item);
	public boolean releaseItem(String item);
	public void addItem(String item);
	public Vector<String> getItems();
}
