/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */
public interface Job {
	/**
	 * 
	 * @return string
	 */
	public String getId();
	/**
	 * 
	 * @return string
	 */
	public String getResource();
	/**
	 * 
	 * @return string
	 */
	public String getRepresentationSource();
	/**
	 * 
	 * @return string
	 */
	public String getXML();
	/**
	 * 
	 * @return string
	 */
	public String getStatus();
	/**
	 * 
	 * @return string
	 */
	public String getRepresentationTarget();
	/**
	 * 
	 * @param newStatus for setting
	 */
	public void setStatus(String newStatus);
}
