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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultipleClientProtocolServer implements Runnable {

	
	private ServerSocket serverSocket;
    private ProtocolFactory protocolFactory;
    private int listenPort;
    private ExecutorService _executor;

    public MultipleClientProtocolServer(int port, ProtocolFactory pf) 
    {
    	_executor = Executors.newCachedThreadPool();
    	serverSocket = null;
    	listenPort = port;
    	protocolFactory = pf;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
        try {
            serverSocket = new ServerSocket(listenPort);
            System.out.println("Listening...");
          } catch (IOException e) {
            System.out.println("Cannot listen on port " + listenPort);
          }
          while (true) 
          {
            try 
            {
              ConnectionHandler newConnection = new ConnectionHandler(serverSocket.accept(), protocolFactory.create());
              _executor.execute(newConnection);
            } catch (IOException e) {
              System.out.println("Failed to accept on port " + listenPort);
            }
          }
	}

    public void close() throws IOException 
    {
        serverSocket.close();
      }
}
