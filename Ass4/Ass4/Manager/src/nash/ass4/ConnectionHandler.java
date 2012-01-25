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
    	String dataType = this.protocol.getRequestType();
    	//TODO: implement all this methods!
    	
    	//client uploads a new image
    	if (dataType.equals("PUT /upload"))
    		this.uploadNewImage("0");
    	//employee uploads an edited image
    	else if (dataType.subSequence(0,2).equals("PUT"))
    	{
    		String rep=this.protocol.getRequestType().substring(this.protocol.getRequestType().indexOf("=")+1);
    		this.uploadNewImage(rep);
    	}
    	//request for all the representations of a source
    	else if (dataType.contains("GET /photos/") &&
    			!(dataType.contains("=")))
    		this.allRepresentationsOfResource();
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
    		this.shutdown();
    		
    }
    
    void uploadNewImage(String rep)
    {
    	File photosDir=new File("./photos/");
    	if (!photosDir.exists() || !photosDir.isDirectory())
    		photosDir.mkdir();
    	//TODO needs to write a class for handling resources
    	String id=ResourceCloset.getInstance().getNewResourceId();
    	File resDir=new File("./photos/"+id+"/");
    	resDir.mkdir();
    	FileOutputStream writer = new FileOutputStream(new File("./photos/"+id+"/"+rep));
    	writer.write(this.byteData);
    	writer.flush();
    	
    	this.sendUploadNewImageResponse(id,rep);
    	
    }
    
    void sendUploadNewImageResponse(String id,String rep)
    {
    	
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();
    	String htmlResponse="<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"+
    			"\t<head>\n"+
    			"\t\t<title>"+serverName+"/photos/"+id+"</title>\n"+
    			"\t</head>\n"+
    			"\t<body>\n"+
    			"\t\t<b>HTTP/1.1 201 Created:</b>\n"+
    			"\t\t<a href=\"photos/"+id+"\">Resource "+id+"</a>\n"+
    			"\t\t has been created successfully.<br/>"+
    			"\t\tOrigianl image can be found <a href=\"photos/"+id+"?rep="+rep+"\">here</a>.\n"+
    			"\t</body>\n"+
    			"</html>";
    			
    	
    	this.out.println("HTTP/1.1 201 Created");
    	this.out.println("Server: "+ serverName);
    	this.out.println("Content-Type: text/html; charset=utf-8");
    	this.out.println("Content-Length: "+htmlResponse.length());
    	this.out.println();
    	this.out.print(htmlResponse);
    }
    
    private void allRepresentationsOfResource()
    {
    	String res=this.protocol.getRequestType().substring(12);
    	
    }
    
    private void getRepresentation()
    {
    	String res=this.protocol.getRequestType().substring(12,this.protocol.getRequestType().indexOf("?"));//TODO lo kolel
    	String rep=this.protocol.getRequestType().substring(this.protocol.getRequestType().indexOf("=")+1);
    	if (JobManagerImpl.getInstance().isCompleted(res,rep))
    	{
    		this.out.println("HTTP/1.1 200 OK");
    		this.out.println("Server: "+this.clientSocket.getLocalAddress().getHostAddress());
    		this.out.println("Content-Type: " + ResourceClosetImple.getInstance().getOriginalMimeType(res));
    		this.out.println("Content-Length: "+ResourceClosetImple.getInstance().getRepresentationFile(res,rep).length());
    		this.out.println();
    		this.out.flush();
    		
    		byte data[]=new byte[ResourceClosetImple.getInstance().getRepresentationFile(res,rep).length()];
    		FileInputStream reader=new FileInputStream(ResourceClosetImple.getInstance().getRepresentationFile(res,rep));
    		reader.read(data);
    		
    		this.clientSocket.getOutputStream().write(data);
    	}
    	else if (JobManagerImpl.getInstance().isAssigned(res,rep))
    	{
    		this.out.println("HTTP/1.1 206 Partial Content");
    		this.out.println();
    	}
    	else
    	{
    		this.out.println("HTTP/1.1 404 Not Found");
    		this.out.println();
    	}
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
