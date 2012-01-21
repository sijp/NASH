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

public class HttpProtocolFactory implements ProtocolFactory 
{
    public ServerProtocol create() 
    { 
    	return new HttpProtocol(); 
    }
}
