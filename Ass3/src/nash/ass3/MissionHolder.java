package nash.ass3;

public interface MissionHolder {
/*
 * @inv: all Mission holders sorted by the appropiate key.
 * @inv: size>=0;
 */
	
	
	/*
	 * @returns Nothing, only takes.
	 * @pre: none;
	 * @post: queue size = queue size+1 ; 
	 */
	public void insert(Mission m);
	
	/*
	 * @returns a mission according to the sergeant priority and skills
	 */
	
	public Mission fetch(Sergeant s);
	/*
	 * @returns a mission according to the sergeant priority and skills
	 * keeps the mission in the mission holder.
	 * @pre: isEmpty() == false; 
	 * @post: the mission holder size stays the same.
	 */
	public Mission getMission(Sergeant s);
	/*
	 * @return true if the mission holder is empty
	 * @return false if the mission holder has at least one mission
	 * @pre: none
	 * @post: none
	 */
	public boolean isEmpty();
	
	public int getSize();

	public void clear();
}
