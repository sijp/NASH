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
    	String dataType = this.protocol.getContentType();
    	//TODO: implement all this methods!
    	
    	//client uploads a new image
    	if (dataType.equals("PUT /upload"))
    		this.uploadNewImageFromClient();
    	//employee uploads an edited image
    	else if (dataType.subSequence(0,2).equals("PUT"))
    		this.uploadNewImageFromEmployee();
    	//request for all the representations of a source
    	else if (dataType.contains("GET /photos/") &&
    			!(dataType.contains("=")))
    		this.allRepresentationsOfSource();
  		//client asks for a specific representation
    	else if (dataType.contains("GET /photos/") &&
    			dataType.contains("="))
    		this.getRepresentation();
    	//shows all photos and all representations
    	else if (dataType.contains("GET /photos"))
    		this.allPhotosAndRepresentations();
    	//request for a spesific job
    	else if (dataType.contains("GET /jobs/"))
    		this.getJob();
    	//request for all jobs
    	else if (dataType.contains("GET /jobs"))
    		this.getAllJobs();
    	//This request is also used by the client in order to submit a new job
    	else if (dataType.contains("POST /photos/"))
    		this.postNewJob();
    	//This request is used by employees to ask for a new job. 
    	else if(dataType.contains("POST /jobs/"))
    		this.getNewJobForEmployee();
    	//If a client calls this page, the server should shutdown gracefully. If an employee tries to
    	//reach the server when it is down - he should exit gracefully as well.
    	else if (dataType.contains("POST /shutdown"))
    		this.shutdown()
    		
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
