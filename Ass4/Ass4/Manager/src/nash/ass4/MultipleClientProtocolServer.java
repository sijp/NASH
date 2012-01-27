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

    private boolean runFlag;
    
    
    
    public MultipleClientProtocolServer(int port, ProtocolFactory pf) 
    {
    	serverSocket = null;
    	listenPort = port;
    	protocolFactory = pf;
    	this.runFlag = true;
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
          while (this.runFlag) 
          {
            try 
            {
              ConnectionHandler newConnection = new ConnectionHandler
            		  (serverSocket.accept(), protocolFactory.create() , this);
              Thread t=new Thread(newConnection);
              t.start();
            } catch (IOException e) {
              System.out.println("Failed to accept on port " + listenPort);
            }
          }
          System.out.println("Done!");
	}

	public void shutDown()
	{
		this.runFlag = false;
		try{
			this.close();
		}catch(IOException ioe)
		{
			
		}
	}
	
    public void close() throws IOException 
    {
        serverSocket.close();
      }
}
