/**
 * 
 */
package nash.ass3;
import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;
/**
 * @author nadav , shlomi
 *
 */

public class MissionHolderImpl implements MissionHolder {
	private PriorityBlockingQueue<Mission> minLength, maxLength, minItems, maxItems;
	
	/*
	 * allows only one instance of a missionHolder.
	 */
	private static MissionHolder missionHolder;
	
	/*
	 * returns the singleton instance of the MissionHolder.
	 * it's synchronized for thread-safety.
	 */
	
	public static synchronized MissionHolder getInstance()
	{
		System.out.println("getting");
		if (missionHolder==null)
		{
			missionHolder = new MissionHolderImpl();
			System.out.println("+1");
		}
		return missionHolder;
	}
	/*
	*constructor of missionHolder
	*construct 4 priority queue for: max length and min length of the mission
	*and min items and max items of the amount of the items needed.
	*/
	private MissionHolderImpl()
	{
		this.minLength = new PriorityBlockingQueue<Mission>();
		this.maxLength = new PriorityBlockingQueue<Mission>();
		this.minItems = new PriorityBlockingQueue<Mission>();
		this.maxItems = new PriorityBlockingQueue<Mission>();
	}
	
	int size = 0;
	/* (non-Javadoc)
	 * @see nash.ass3.MissionHolder#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return(this.minLength.size()==0);
	}

	private void insert(Mission m) {
		this.minLength.add(m);
		this.maxLength.add(m);
		this.minItems.add(m);
		this.maxItems.add(m);
	}

	@Override
	public void insert(Vector<Mission> mList)
	{
		for (int i=0;i<=mList.size();i++)
		{
			Mission m=mList.elementAt(i);
			this.insert(m);
		}
	}

	@Override
	public Mission fetch(Sergeant s) {
		Mission mFetch = this.getMission(s);
		if(mFetch!=null)
		{
			this.delete(mFetch);
			return mFetch;
		}
		return null;
	}

	@Override
	public Mission getMission(Sergeant s) {
		if(this.isEmpty())
			return null;
		return this.minLength.peek();
	}
	
	private void delete(Mission m)
	{
		this.minLength.remove(m);
		this.maxLength.remove(m);
		this.minItems.remove(m);
		this.maxItems.remove(m);
	}
	
	
	public int getSize(){
		return this.minLength.size();
	}
	
	public void clear()
	{
		this.minLength.clear();
		this.maxLength.clear();
		this.minItems.clear();
		this.minItems.clear();
	}
}
