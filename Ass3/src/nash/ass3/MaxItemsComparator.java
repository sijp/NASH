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
	
	public static void main(String arg[]) throws InterruptedException
	{
		
		Mission m1=new MissionImpl("A","",3);
		Mission m2=new MissionImpl("B","",4);
		Mission m3=new MissionImpl("C","",7);
		
		ItemList l=new ItemList();
		l.addElement(new ItemImpl("X", 3));
		
		m1.addRequiredItems(l);
		
		l=new ItemList();
		l.addElement(new ItemImpl("X", 1));
		l.addElement(new ItemImpl("Y", 1));
		
		
		m2.addRequiredItems(l);
		
		l=new ItemList();
		l.addElement(new ItemImpl("X", 5));
		l.addElement(new ItemImpl("Y", 5));
		l.addElement(new ItemImpl("Z", 1));
		
		m3.addRequiredItems(l);
		
		PriorityBlockingQueue<Mission> q=new PriorityBlockingQueue<Mission>(10, new MaxItemsComparator());
		q.put(m1);
		q.put(m2);
		q.put(m3);
		
		System.out.println(q.take().getName());
		System.out.println(q.take().getName());
		System.out.println(q.take().getName());
	}

}
