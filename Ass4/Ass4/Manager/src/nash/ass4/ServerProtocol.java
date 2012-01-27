/**
 * 
 */
package nash.ass4;

public interface ServerProtocol 
{
	/**
	 * 
	 * @param msg holds msg
	 * @return boolean
	 */
	boolean processMessage(String msg);
    /**
     * 
     * @param msg message
     * @return boolean
     */
	boolean isEnd(String msg);
	
	/**
	 * 
	 * @return int
	 */
	public int getContentLength();
	
	/**
	 * 
	 * @return String
	 */
	public String getContentType();
	
	/**
	 * 
	 * @return the request type
	 */
	public String getRequestType();
}
