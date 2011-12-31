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
public class MaxLengthComparator implements Comparator<Mission> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Mission arg0, Mission arg1) {
		return arg1.getTimeToCompletion()-arg0.getTimeToCompletion();
	}
	
	public static void main(String arg[]) throws InterruptedException
	{
		Mission m3=new MissionImpl("C","",7);
		Mission m1=new MissionImpl("A","",3);
		Mission m2=new MissionImpl("B","",4);
		
		PriorityBlockingQueue<Mission> q=new PriorityBlockingQueue<Mission>(1, new MaxLengthComparator());
		System.out.println(q.size());
		q.put(m3);
		q.put(m1);
		q.put(m2);
		
		System.out.println(q.size());
		System.out.println(q.take().getName());
		System.out.println(q.take().getName());
		System.out.println(q.take().getName());
		System.out.println(q.size());
	}

}
