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
    MultipleClientProtocolServer multipleClientProtocolServer;
    
    public ConnectionHandler(Socket acceptedSocket, ServerProtocol p , 
    		MultipleClientProtocolServer multipleClientProtocolServer) 
    {
      in = null;
      out = null;
      clientSocket = acceptedSocket;
      protocol = p;
      System.out.println("Accepted connection from client!");
      System.out.println("The client is from: " + 
    		  acceptedSocket.getInetAddress() + ":" + acceptedSocket.getPort());
      this.multipleClientProtocolServer = multipleClientProtocolServer;
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
        if (this.protocol.getContentLength() > 0)
        {
        	byteData = new byte[protocol.getContentLength()];
        	clientSocket.getInputStream().read(byteData);
        }
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
    	Resource newRes = ResourceClosetImpl.getInstance().addNewResource
    			(this.protocol.getContentType());
    	File resDir=new File("photos/"+newRes.getId()+"/");
    	resDir.mkdir();
    	FileOutputStream writer;
		try 
		{
			writer = new FileOutputStream(newRes.getRepresentationFile(rep));
			writer.write(this.byteData);
	    	writer.flush();
	    	newRes.setReady();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    	
    	
    	this.sendUploadNewImageResponse(newRes.getId() , rep);
    	
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
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();
    	Resource resource = ResourceClosetImpl.getInstance().getResource(res);
    	String htmlResponse = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"" +
    			"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
    			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"+
    			"<head>\n" +
    			"\t<title>" + serverName + "/photos/" + res + "</title>\n" +
    			"</head>\n" +
    			"<body>\n" +
    			"\t<p>JOB STATUS CATEGORY</p>\n" +
    			"\t<ol>\n";

    	for(int i = 0 ; i < resource.getRepList().size() ; i++)
    	{
    		Representation repI = resource.getRepList().elementAt(i);
    		htmlResponse+= "\t\t<li><a href=\"photos/" + res + "?rep=" + repI.getId() +
    				"\">Representation " + repI.getId() + "</a>\n" +
    				"\t\t\t<ul>\n";
    		for(int j = 0 ; j < repI.getRepresentationJobs().size() ; j++)
    		{
    			Job jobJ = repI.getRepresentationJobs().elementAt(j);
    			htmlResponse+= "\t\t\t\t<li><a href=\"jobs/" + jobJ.getId() + "\">" +
    					"JOB " + jobJ.getId() + "</a>. Status - " + jobJ.getStatus() +"</li>\n";
    		}
    		htmlResponse+="\t\t\t</ul>\n" +
    				"\t\t</li>\n";
    	}
    	htmlResponse+="\t\t</ol>\n" +
    			"\t</body>\n" +
    			"</html>";
    	
    	this.out.println("HTTP/1.1 200 OK");
    	this.out.println("Server: " + serverName);
    	this.out.println("Content-Type: text/html; charset=utf-8");
    	try {
			this.out.println("Content-Length: " + htmlResponse.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	this.out.println();
    	this.out.print(htmlResponse);
    	this.out.flush();
    }
    
    
    private void getRepresentation()
    {
    	String res=this.protocol.getRequestType().substring(12,this.protocol.getRequestType().indexOf("?"));//TODO lo kolel
    	String rep=this.protocol.getRequestType().substring(this.protocol.getRequestType().indexOf("=")+1);
    	if (JobManagerImpl.getInstance().isCompleted(res,rep))
    	{
    		this.out.println("HTTP/1.1 200 OK");
    		this.out.println("Server: "+this.clientSocket.getLocalAddress().getHostAddress());
    		this.out.println("Content-Type: " + ResourceClosetImpl.getInstance().getResource(res).getOriginalMimeType());
    		this.out.println("Content-Length: "+ResourceClosetImpl.getInstance().getResource(res).getRepresentationFile(rep).length());
    		this.out.println();
    		this.out.flush();
    		
    		byte data[]=new byte[(int)ResourceClosetImpl.getInstance().getResource(res).getRepresentationFile(rep).length()];
    		FileInputStream reader;
			try
			{
				reader = new FileInputStream(ResourceClosetImpl.getInstance().getResource(res).getRepresentationFile(rep));
	    		reader.read(data);
	    		this.clientSocket.getOutputStream().write(data);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
    		
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
    
    private void allPhotosAndRepresentations()
    {
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();

    	String htmlResponse = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"" +
    			"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
    			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"+
    			"<head>\n" +
    			"\t<title>photos</title>\n" +
    			"</head>\n" +
    			"<body>\n" +
    			"\t<p>Photos</p>\n" +
    			"\t<ol>\n";
    	for (int i = 0 ; i < ResourceClosetImpl.getInstance().getResList().size() ; i++)
    	{
    		Resource res = ResourceClosetImpl.getInstance().getResList().elementAt(i);
    		htmlResponse+="\t\t\t<li><a href=\"photos/" + res.getId() + "\">" +
    				"Photo " + res.getId() + "</a>\n" +
    				"\t\t\t\t<ol>\n";
    		for(int j = 0 ; j < res.getRepList().size() ; j++)
    		{
    			Representation rep = res.getRepList().elementAt(j);
    			htmlResponse+= "\t\t\t\t\t" +
    					"<li><a href=\"photos/" + res.getId() + "?rep=" +
    					rep.getId() + "\">Representation " + rep.getId() + "</a></li>\n";
    		}
    		htmlResponse+="\t\t\t\t</ol>\n" +
    				"\t\t\t</li>";
    	}
    	htmlResponse+="\t</ol>\n" +
    			"</body>\n" +
    			"</html>";
    	
    	this.out.println("HTTP/1.1 200 OK");
    	this.out.println("Server: " + serverName);
    	this.out.println("Content-Type: text/html; charset=utf-8");
    	try {
			this.out.println("Content-Length: " + htmlResponse.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	this.out.println();
    	this.out.print(htmlResponse);
    	this.out.flush();
    }
    
    private void getJob()
    {
    	String jobId = this.protocol.getRequestType().substring(10);
    	Job job = JobManagerImpl.getInstance().getJob(jobId);
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();

    	
    	this.out.println("HTTP/1.1 200 OK");
    	this.out.println("Server: " + serverName);
    	this.out.println("Content-Type: text/xml; charset=utf-8");
    	try {
			this.out.println("Content-Length: " + job.getXML().getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	this.out.println();
    	this.out.print(job.getXML());
    	this.out.flush();
    }
    
    private void getAllJobs()
    {
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();

    	String htmlResponse = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"" +
    			"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
    			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"+
    			"<head>\n" +
    			"\t<title>" + serverName + "/jobs</title>\n" +
    			"</head>\n" +
    			"<body>\n" +
    			"\t<p>Jobs Status</p>\n" +
    			"\t<ol>\n";
    	htmlResponse+="\t\t<li>Non Submitted Jobs\n" +
    			"\t\t\t<ul>\n" ;
    	for (int i=0 ; i<JobManagerImpl.getInstance().getNonSubmittedJobs().size() ; i++)
    	{
    		Job job = JobManagerImpl.getInstance().getNonSubmittedJobs().elementAt(i);
    		htmlResponse+="/t/t/t/t<li><a href=\"jobs/"+job.getId()+"\">JOB "+job.getId()+"</a>:" +
    				"<a href=\"photos/" + job.getResource() + "\">Resource " +
    				job.getResource() + "</a>.</li>\n";
    	}
    	htmlResponse+="/t/t/t</ul>\n" +
    			"\t\t</li>\n";
    	htmlResponse+="\t\t<li>Submitted Jobs\n" +
    			"\t\t\t<ul>\n" ;
    	for (int i=0 ; i<JobManagerImpl.getInstance().getSubmittedJobs().size() ; i++)
    	{
    		Job job = JobManagerImpl.getInstance().getSubmittedJobs().elementAt(i);
    		htmlResponse+="\t\t\t\t<li><a href=\"jobs/"+job.getId()+"\">JOB "+job.getId()+"</a>:" +
    				"<a href=\"photos/" + job.getResource() + "\">Resource " +
    				job.getResource() + "</a>.</li>\n";
    	}
    	htmlResponse+="\t\t\t</ul>\n" +
    			"\t\t</li>\n";
    	htmlResponse+="\t\t<li>Finished Jobs\n" +
    			"\t\t\t<ul>\n" ;
    	for (int i=0 ; i<JobManagerImpl.getInstance().getFinishedJobs().size() ; i++)
    	{
    		Job job = JobManagerImpl.getInstance().getFinishedJobs().elementAt(i);
    		htmlResponse+="\t\t\t\t<li><a href=\"jobs/"+job.getId()+"\">JOB "+job.getId()+"</a>:" +
    				"<a href=\"photos/" + job.getResource() + "\">Resource " +
    				job.getResource() + "</a>.</li>\n";
    	}
    	htmlResponse+="\t\t\t</ul>\n" +
    			"\t\t</li>\n" +
    			"\t</ol>\n " +
    			"</body>\n" +
    			"</html>";
    	
    	this.out.println("HTTP/1.1 200 OK");
    	this.out.println("Server: " + serverName);
    	this.out.println("Content-Type: text/html; charset=utf-8");
    	try {
			this.out.println("Content-Length: " + htmlResponse.getBytes().length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	this.out.println();
    	this.out.print(htmlResponse);
    	this.out.flush();
    }
    
    public void postNewJob()
    {
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();

    	String resId = this.protocol.getRequestType().substring(13);
    	int pos = this.protocol.getContentType().indexOf(":");
    	String charset = this.protocol.getContentType().substring(pos+2);
    	String jobXml = new String(this.byteData , charset);
    	Job newJob = JobManagerImpl.getInstance().getNewJob(resId , jobXml);
    	
    	String htmlResponse = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"+
    			"\t<head>\n" +
    			"\t\t<title>" + serverName + "/photos/" + resId + "/" +newJob.getRepresentationTarget() +
    			"</title>\n" +
    			"\t</head>" +
    			"\t<body>" +
    			"\t\t<b>HTTP/1.1 202 Accepted:</b></br>\n" +
    			"\t\tRepresentation image can be found<a href=\"photos/" + resId + "?rep=" +
    				newJob.getRepresentationTarget() + ">here</a>\n" +
    				"\t</body>\n" +
    				"</html>";
    	
    	this.out.println("HTTP/1.1 202 Accepted");
    	this.out.println("Server: " + serverName);
    	this.out.println("Content-Type: text/xml; charset=utf-8");
    	try {
			this.out.println("Content-Length: " + htmlResponse.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	this.out.println();
    	this.out.print(htmlResponse);
    	this.out.flush();
    	
    }
    
    private void getNewJobForEmployee()
    {
    	Job job = JobManagerImpl.getInstance().requestJob();
    	String serverName=this.clientSocket.getLocalAddress().getHostAddress();

    	if(job != null)
    	{
	    	this.out.println("HTTP/1.1 200 OK");
	    	this.out.println("Server: " + serverName);
	    	this.out.println("resource: " + job.getResource());
	    	this.out.println("Content-Type: text/xml; charset=utf-8");
	    	try {
				this.out.println("Content-Length: " + job.getXML().getBytes("UTF-8").length);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    	
	    	this.out.println();
	    	this.out.print(job.getXML());
	    	this.out.flush();
    	}
    	
    	else
    	{
    		this.out.println("HTTP/1.1 204 No Content");
	    	this.out.println("Server: " + serverName);
    	}
    	
    }
    
    public void shutdown()
    {
    	this.multipleClientProtocolServer.shutDown();
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
