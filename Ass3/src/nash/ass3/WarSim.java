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
	
	public static final Logger LOG=Logger.getLogger("WarLord");

	/**
	 * the main function
	 * 
	 * @param args - prog args
	 */
	
	public static void main(String[] args)
	{
		FileHandler fh=null;
		final int MISSIONS=0,SERGEANT=1,ITEMS=2,LOGFILE=3;
		
		try
		{
			fh=new FileHandler(args[LOGFILE]);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		WarSim.LOG.addHandler(fh);
		WarSim.LOG.setLevel(Level.FINEST);
		
		try
		{
			BoardImpl.getInstance().readProperties(args[MISSIONS]);
			ChiefOfStaffImpl.getInstance().readProperties(args[SERGEANT]);
			WarehouseImpl.getInstance().readProperties(args[ITEMS]);
			ObserverImpl.getInstance().printIncompleteMissions();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}

		try
		{
			WarSim.LOG.finest("Starting Cheif of Staff Life Cycle");
			ChiefOfStaffImpl.getInstance().start();
			WarSim.LOG.finest("Starting Observer");
			ObserverImpl.getInstance().start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
