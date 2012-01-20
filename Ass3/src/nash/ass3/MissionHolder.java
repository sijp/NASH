package nash.ass3;

import java.util.Vector;
/**
 * 
 * @author shlomi the fat and angry (on nadav) hobit.
 * a Tikia of missions, used by the chief of staff
 */
public interface MissionHolder {
/**
 * @inv: all Mission holders sorted by the appropiate key.
 * @inv: size>=0;
 */
	
	public static final int INITSIZE=10;
	
	/**
	 * @returns Nothing, only takes.
	 * @pre: none;
	 * @post: queue size = queue size+1 ; 
	 * 
	 * 
	 * @param mList - list of missions to insert
	 */
	public void insert(Vector<Mission> mList);
	
	/**
	 * @returns a mission according to the sergeant priority and skills
	 * 	
	 * @param s - sergeant to fetch
	 * @return the mission that this sergeant can do
	 */
	public Mission fetch(Sergeant s);
	/**
	 * @returns a mission according to the sergeant priority and skills
	 * keeps the mission in the mission holder.
	 * @pre: isEmpty() == false; 
	 * @post: the mission holder size stays the same.
	 * 
	 * @param s - sergeant to fetch
	 * @return the mission that this sergeant can do
	 */
	public Mission getMission(Sergeant s);
	/**
	 * @return true if the mission holder is empty, false if the mission holder has at least one mission
	 * @pre: none
	 * @post: none
	 * 
	 * @return
	 */
	public boolean isEmpty();
	/**
	 * 	
	 * @return the size of the mission holder
	 */
	public int getSize();
	/**
	 * 
	 */
	public void clear();

}
