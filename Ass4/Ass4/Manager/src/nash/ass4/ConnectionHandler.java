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
import java.nio.charset.Charset;

public class ConnectionHandler implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    Socket clientSocket;
    ServerProtocol protocol;
    byte[] byteData;
    
    public ConnectionHandler(Socket acceptedSocket, ServerProtocol p) 
    {
      in = null;
      out = null;
      clientSocket = acceptedSocket;
      protocol = p;
      System.out.println("Accepted connection from client!");
      System.out.println("The client is from: " + 
    		  acceptedSocket.getInetAddress() + ":" + acceptedSocket.getPort());
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		 String msg;
	        try 
	        {
	        	initialize();
	        }
	        catch (IOException e) 
	        {
	        	System.out.println("Error in initializing I/O");
	        }
	        try 
	        {
	        	process();
	        } 
	        catch (IOException e) 
	        {
	        	System.out.println("Error in I/O");
	        } 
	        System.out.println("Connection closed - bye bye...");
	        close();
	}
	
    public void process() throws IOException 
    {
        String msg;
        boolean isEnd = false;
        while ((msg = in.readLine()) != null && !isEnd) 
        {
        	 isEnd = protocol.processMessage(msg);
        }
        byteData = new byte[protocol.getContentLength()];
        clientSocket.getInputStream().read(byteData);
        
        this.processByteData();
     }
    
    private void processByteData()
    {
    	int pos = protocol.getContentType().indexOf("/");
    	String dataType = protocol.getContentType().substring(0, pos);
    	//TODO: divide each http request to the appropiate method
    	/*
    	*if (dataType.equals("PUT") && this.protocol.getContentType().contains("upload"))
    	*	this.uploadNewImageFromClient();
    	*else if (dataType.equals("PUT") && this.protocol.getContentType().contains("="))
    	*	this.uploadNewImageFromEmployee();
  		*/
    }
      
      // Starts listening
      public void initialize() throws IOException {
        // Initialize I/O
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
        out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"), true);
        System.out.println("I/O initialized");
      }
      
      // Closes the connection
      public void close() {
        try {
          if (in != null) {
            in.close();
          }
          if (out != null) {
            out.close();
          }
          clientSocket.close();
        } catch (IOException e) {
          System.out.println("Exception in closing I/O");
        }
      }
}
