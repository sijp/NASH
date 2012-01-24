/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */
import java.io.*;
import java.net.*;

public interface ServerProtocol 
{
	/**
	 * 
	 * @param msg
	 * @return
	 */
	boolean processMessage(String msg);
    /**
     * 
     * @param msg
     * @return
     */
	boolean isEnd(String msg);
	
	/**
	 * 
	 * @return
	 */
	public int getContentLength();
	
	/**
	 * 
	 * @return
	 */
	public String getContentType();
	
	/**
	 * 
	 * @return the request type
	 */
	public String getRequestType();
}
