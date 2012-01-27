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


public class MultipleClientProtocolServer implements Runnable {

	
	private ServerSocket serverSocket;
    private ProtocolFactory protocolFactory;
    private int listenPort;

    private boolean runFlag;
    
    
    /**
     * 
     * @param port the port
     * @param pf the pf
     */
    public MultipleClientProtocolServer(int port, ProtocolFactory pf) 
    {
    	this.serverSocket = null;
    	this.listenPort = port;
    	this.protocolFactory = pf;
    	this.runFlag = true;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
        try {
            this.serverSocket = new ServerSocket(this.listenPort);
            System.out.println("Listening...");
          } catch (IOException e) {
            System.out.println("Cannot listen on port " + this.listenPort);
          }
          while (this.runFlag) 
          {
            try 
            {
              ConnectionHandler newConnection = new ConnectionHandler
            		  (this.serverSocket.accept(), this.protocolFactory.create() , this);
              Thread t=new Thread(newConnection);
              t.start();
            } catch (IOException e) {
              System.out.println("Failed to accept on port " + this.listenPort);
            }
          }
          System.out.println("Done!");
	}
	/**
	 * shutting dowwwn
	 */
	public void shutDown()
	{
		this.runFlag = false;
		try{
			this.close();
		}catch(IOException ioe)
		{
			
		}
	}
	
	/**
	 * closssing
	 * @throws IOException IO
	 */
    public void close() throws IOException 
    {
        this.serverSocket.close();
      }
}
