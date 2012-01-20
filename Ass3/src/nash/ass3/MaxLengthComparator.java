/**
 * 
 */
package nash.ass3;

import java.util.Comparator;

/**
 * @author shlomi nadav
 * comperator for max value of int
 *
 */
public class MaxLengthComparator implements Comparator<Mission> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Mission arg0, Mission arg1) {
		return arg1.getTimeToCompletion()-arg0.getTimeToCompletion();
	}
	
	

}
