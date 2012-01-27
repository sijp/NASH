/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */



public class HttpProtocolFactory implements ProtocolFactory 
{
	/**
	 * creating
	 * @return serverp
	 */
    public ServerProtocol create() 
    { 
    	return new HttpProtocol(); 
    }
}
