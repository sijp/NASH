/**
 * 
 */
package nash.ass3;

import java.io.IOException;

/**
 * @author nadav , shlomi
 *
 */
public class WarSim {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
		BoardImpl.getInstance().readProperties(args[0]);
		ChiefOfStaffImpl.getInstance().readProperties(args[1]);
		WarehouseImpl.getInstance().readProperties(args[2]);
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		ObserverImpl.getInstance().printItems();
		ObserverImpl.getInstance().printSergeants();
		ObserverImpl.getInstance().printIncompleteMissions();

		try
		{
			ObserverImpl.getInstance().start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
