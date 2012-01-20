/**
 * 
 */
package nash.ass3;

import java.util.Comparator;

/**
 * @author shlomi , nadav
 * comperator for max value of #items
 */
public class MaxItemsComparator implements Comparator<Mission> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Mission arg0, Mission arg1) {
		return arg1.getNumberOfRequiredItems()-arg0.getNumberOfRequiredItems();
	}
	

}
