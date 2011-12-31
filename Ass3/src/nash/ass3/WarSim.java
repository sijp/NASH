/**
 * 
 */
package nash.ass3;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nadav , shlomi
 *
 */
public class WarSim
{
	
	public static final Logger log=Logger.getLogger("WarLord");

	/**
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		FileHandler fh=null;
		
		try
		{
			fh=new FileHandler(args[3]);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		WarSim.log.addHandler(fh);
		WarSim.log.setLevel(Level.FINEST);
		
		try
		{
			BoardImpl.getInstance().readProperties(args[0]);
			ChiefOfStaffImpl.getInstance().readProperties(args[1]);
			WarehouseImpl.getInstance().readProperties(args[2]);
			ObserverImpl.getInstance().printIncompleteMissions();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}

		try
		{
			WarSim.log.finest("Starting Cheif of Staff Life Cycle");
			ChiefOfStaffImpl.getInstance().start();
			WarSim.log.finest("Starting Observer");
			ObserverImpl.getInstance().start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
