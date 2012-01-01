/**
 * 
 */
package nash.ass3;

import java.util.Comparator;

/**
 * @author shlomi
 *
 */
public class MinItemsComparator implements Comparator<Mission> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Mission arg0, Mission arg1) {
		return arg0.getNumberOfRequiredItems()-arg1.getNumberOfRequiredItems();
	}
	

}
