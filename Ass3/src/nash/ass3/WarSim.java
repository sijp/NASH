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
		final int MISSIONS=0,SERGEANT=1,ITEMS=2,LOG=3;
		
		try
		{
			fh=new FileHandler(args[LOG]);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		WarSim.log.addHandler(fh);
		WarSim.log.setLevel(Level.FINEST);
		
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
