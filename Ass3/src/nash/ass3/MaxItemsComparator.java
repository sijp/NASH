/**
 * 
 */
package nash.ass3;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author shlomi
 *
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
