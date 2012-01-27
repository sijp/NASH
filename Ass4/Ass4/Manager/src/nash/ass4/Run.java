/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */
public class Run {

	/**
	 * @param args information for the server
	 */
	public static void main(String[] args) 
	{
		int port = Integer.decode(args[0]).intValue();
		MultipleClientProtocolServer server=new MultipleClientProtocolServer(port, new HttpProtocolFactory());
		Thread serverThread = new Thread(server);
		serverThread.start();
		try{
			serverThread.join();
		}catch(InterruptedException ie)
		{
			System.out.println("Server has stopped");
		}
	}

}
