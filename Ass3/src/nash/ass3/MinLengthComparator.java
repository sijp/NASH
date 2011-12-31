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
public class MinLengthComparator implements Comparator<Mission> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Mission arg0, Mission arg1) {
		return arg0.getTimeToCompletion()-arg1.getTimeToCompletion();
	}
	
	public static void main(String arg[]) throws InterruptedException
	{
		Mission m1=new MissionImpl("A","",3);
		Mission m2=new MissionImpl("B","",4);
		PriorityBlockingQueue<Mission> q=new PriorityBlockingQueue<Mission>(10, new MinLengthComparator());
		q.put(m1);
		q.put(m2);
		System.out.println(q.take().getName());
		System.out.println(q.take().getName());
	}

}
