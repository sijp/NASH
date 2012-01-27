/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */
public class HttpProtocol implements ServerProtocol {

	private String reqType;
	private String reqResource;
	private String reqContentType;
	private int reqContentLength;
	
	public HttpProtocol() 
    {
		
    }
      /* (non-Javadoc)
	 * @see nash.ass4.ServerProtocal#processMessage(java.lang.String)
	 */
	@Override
	public boolean processMessage(String msg) 
	{
	        if (isEnd(msg)) {
	        	return true;
	        }
	        else 
	        {
	        	this.assignParameter(msg);
	        	return false;
	        }
	}
	
	private void assignParameter(String msg)
	{
		int pos1 = msg.indexOf(" ");
		String start = msg.substring(0 , pos1);
		if(start.equals("GET") || start.equals("PUT") || start.equals("POST"))
		{
			/*this.reqType = start;
			int pos2 = msg.indexOf(" ", pos1+1);
			this.reqResource = msg.substring(pos1+1, pos2);*/
			
			this.reqType=msg;
		}
		
		else if(start.equals("Content-Type:"))
		{
			this.reqContentType = msg.substring(pos1+1);
		}
		
		else if (start.equals("Content-Length:"))
		{
			this.reqContentLength = Integer.parseInt(msg.substring(pos1+1).trim());
		}	
	}
	
	public String getRequestType()
	{
		return this.reqType;
	}
	

	/* (non-Javadoc)
	 * @see nash.ass4.ServerProtocal#isEnd(java.lang.String)
	 */
	@Override
	public boolean isEnd(String msg) 
	{
		System.out.println(msg.equals(""));
		return msg.equals("");
    }
	
	@Override
	public int getContentLength() {
		return this.reqContentLength;
	}
	
	@Override
	public String getContentType() {
		return this.reqContentType;
	}

}
