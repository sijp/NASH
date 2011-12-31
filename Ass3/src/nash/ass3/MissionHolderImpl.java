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
		if (missionHolder==null)
		{
			missionHolder = new MissionHolderImpl();
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
		this.minLength = new PriorityBlockingQueue<Mission>(10,new MinLengthComparator());
		this.maxLength = new PriorityBlockingQueue<Mission>(10, new MaxLengthComparator());
		this.minItems = new PriorityBlockingQueue<Mission>(10, new MinItemsComparator());
		this.maxItems = new PriorityBlockingQueue<Mission>(10, new MaxItemsComparator());
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
		this.minLength.put(m);
		this.maxLength.put(m);
		this.minItems.put(m);
		this.maxItems.put(m);
		BoardImpl.getInstance().levelUp(m);
	}

	@Override
	public void insert(Vector<Mission> mList)
	{
		for (int i=0;i<mList.size();i++)
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
		
		
		PriorityBlockingQueue<Mission> relevantQueue;
		Vector<Mission> temp=new Vector<Mission>();
		
		if (s.getPriority().equals(Sergeant.SHORTEST))
			relevantQueue=this.minLength;
		else if (s.getPriority().equals(Sergeant.LONGEST))
			relevantQueue=this.maxLength;
		else if (s.getPriority().equals(Sergeant.MINITEMS))
			relevantQueue=this.minItems;
		else
			relevantQueue=this.maxItems;
		while (!relevantQueue.isEmpty())
		{
			Mission candidate=relevantQueue.poll();
			temp.add(candidate);
			WarSim.log.fine("Testing Mission "+candidate.getName()+"("+candidate.getSkill()+")");
			if (s.getSkills().contains(candidate.getSkill()))
			{
				for (Mission tm : temp)
					relevantQueue.put(tm);
				return candidate;
			}
			else
				WarSim.log.fine("The sergeant "+ s.getName()+" can't execute mission "+candidate.getName());
		}
		for (Mission tm : temp)
			relevantQueue.put(tm);
		
		return null;
	}
	
	private void delete(Mission m)
	{
		String s="Removing "+m.getName()+" success? ";
		
		this.minLength.remove(m);
		this.maxLength.remove(m);
		this.minItems.remove(m);
		this.maxItems.remove(m);
		s+=!(this.minLength.contains(m)||this.maxLength.contains(m));
		WarSim.log.fine(s);
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
