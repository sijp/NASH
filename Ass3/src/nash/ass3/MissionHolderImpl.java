/**
 * 
 */
package nash.ass3;
import java.util.PriorityQueue;
/**
 * @author nadav , shlomi
 *
 */

public class MissionHolderImpl implements MissionHolder {
	private PriorityQueue<Mission> minLength, maxLength, minItems, maxItems;
	
	/*
	 * allows only one instance of a missionHolder.
	 */
	private static MissionHolder missionHolder;
	public static MissionHolder getInstance()
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
		this.minLength = new PriorityQueue<Mission>();
		this.maxLength = new PriorityQueue<Mission>();
		this.minItems = new PriorityQueue<Mission>();
		this.maxItems = new PriorityQueue<Mission>();
	}
	
	int size = 0;
	/* (non-Javadoc)
	 * @see nash.ass3.MissionHolder#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		System.out.println("size: " + this.minLength.size());
		return(this.minLength.size()==0);
	}

	@Override
	public void insert(Mission m) {
		System.out.println("insert");
		this.minLength.add(m);
		System.out.println(this.isEmpty());
		this.maxLength.add(m);
		this.minItems.add(m);
		this.maxItems.add(m);
	}

	@Override
	public Mission fetch(Sergeant s) {
		Mission mFetch = this.getMission(s);
		this.delete(mFetch);
		return mFetch;
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
