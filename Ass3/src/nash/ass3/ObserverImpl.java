package nash.ass3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class ObserverImpl implements Observer
{
	private static ObserverImpl observer=null; 
	
	private BufferedReader in;
	
	private ObserverImpl()
	{
		in=new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static synchronized Observer getInstance()
	{
		if (ObserverImpl.observer==null)
			ObserverImpl.observer=new ObserverImpl();
		return ObserverImpl.observer;
	}
	
	@Override
	public void printCompleteMissions() 
	{
		Vector<Mission> toPrint = BoardImpl.getInstance().getCompletedMissions();
		for(Mission m : toPrint)
		{
			//TODO: print for mission
			m.print();
		}

	}

	@Override
	public void printIncompleteMissions() {
		Vector<Mission> toPrint = BoardImpl.getInstance().getIncompletedMissions();
		for(Mission m : toPrint)
			m.print();
	}

	@Override
	public void printSergeants() {
		Vector<Sergeant> toPrint = ChiefOfStaffImpl.getInstance().getSergeants();
		for(Sergeant s : toPrint)
			s.print();

	}

	@Override
	public void printItems() {
		ItemList toPrint = WarehouseImpl.getInstance().getItems();
		for(Item i : toPrint)
			i.print();
	}

	@Override
	public void addMission(String cmd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSergeant(String cmd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addItem(String cmd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void executeCommand(String cmd) throws Exception {
		StringTokenizer st = new StringTokenizer(cmd);
		if(!st.hasMoreTokens())
			throw new Exception("We will ignore this, this time.");
		String newCmd = st.nextToken();
		if(newCmd.equals(Observer.COMPLETEMISSIONS))
			this.printCompleteMissions();
		else if (newCmd.equals(Observer.INCOMPLETEMISSIONS))
			this.printIncompleteMissions();
		else if (newCmd.equals(Observer.SERGEANTS))
			this.printSergeants();
		else if (newCmd.equals(Observer.WAREHOUSE))
			this.printItems();
		else if (newCmd.equals(Observer.ADDMISSION))
			this.addMission(cmd);
		else if (newCmd.equals(Observer.ADDSERGEANT))
			this.addSergeant(cmd);
		else if (newCmd.equals(Observer.ADDITEM))
			this.addItem(cmd);
		else if (newCmd.equals(Observer.STOP))
			this.stop();
		else
			throw new Exception("Unknown Command");

	}
	
	private String getCommand() throws IOException
	{
		String ret=in.readLine();
		return ret;
	}

	@Override
	public void start() throws IOException{
		
		String cmd="";
		while ((cmd=this.getCommand())!=null)
		{
			this.executeCommand(cmd);
		}
	}

}
