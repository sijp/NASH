package nash.ass3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class ObserverImpl implements Observer
{
	private static ObserverImpl observer=null; 
	private boolean runFlag=true;
	
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
			m.print();
			System.out.println();
		}

	}

	@Override
	public void printIncompleteMissions() {
		Vector<Mission> toPrint = BoardImpl.getInstance().getIncompletedMissions();
		for(Mission m : toPrint)
		{
			m.print();
			System.out.println();
		}
	}

	@Override
	public void printSergeants() {
		Vector<Sergeant> toPrint = ChiefOfStaffImpl.getInstance().getSergeants();
		for(Sergeant s : toPrint)
		{
			s.print();
			System.out.println();
		}

	}

	@Override
	public void printItems() {
		ItemList toPrint = WarehouseImpl.getInstance().getItems();
		for(Item i : toPrint)
		{
			i.print();
			System.out.println();
		}
	}

	@Override
	public void addMission(String cmd)
	{
		StringTokenizer st=new StringTokenizer(cmd);
		
		String misName=st.nextToken();
		String misSkill=st.nextToken();
		int misTime=Integer.parseInt(st.nextToken(",").trim());
		st.nextToken(" ");
		System.out.println(misTime);
		String misItems=st.nextToken(",");
		System.out.println(misItems);
		st.nextToken(" ");
		StringTokenizer itemsTokenizer=new StringTokenizer(misItems);
		
		ItemList items=new ItemList();
		while (itemsTokenizer.hasMoreTokens())
		{
			String itemName=itemsTokenizer.nextToken();
			int itemCount=Integer.parseInt(itemsTokenizer.nextToken());
			items.addElement(new ItemImpl(itemName,itemCount));
		}

		
		Mission mis=new MissionImpl(misName,misSkill,misTime);
		mis.addRequiredItems(items);
		
		while (st.hasMoreTokens())
		{
			String preMissionName=st.nextToken();
			//TODO: We are not in kensas anymore. Something is wrong with the addPremissions method
			//must fix fix ifixfixixfiixfixifffxi
			Mission preMission=new MissionImpl(preMissionName, "", 0);
			boolean exists=BoardImpl.getInstance().isExist(preMission);
			if (!exists)
				BoardImpl.getInstance().append(preMission);
			else
				preMission=BoardImpl.getInstance().getMissionByName(preMissionName);
			
			mis.addPreMission(preMission);
			
		}
		boolean exists=BoardImpl.getInstance().isExist(mis);
		if (!exists)
			BoardImpl.getInstance().append(mis);
		else
			BoardImpl.getInstance().getMissionByName(mis.getName()).update(mis);
	}

	@Override
	public void addSergeant(String cmd) {
		StringTokenizer st=new StringTokenizer(cmd);
		
		String serName=st.nextToken();
		int numOfThreads=Integer.parseInt(st.nextToken().trim());
		int maxNumOfMissions=Integer.parseInt(st.nextToken(", ").trim());
		String serSkills=st.nextToken(", ");
		
		StringTokenizer skillsTokenizer=new StringTokenizer(serSkills);
		Vector<String> vecSkills=new Vector<String>();
		while (skillsTokenizer.hasMoreTokens())
			vecSkills.add(skillsTokenizer.nextToken());
		
		st.nextToken(", ");
		int workHours=Integer.parseInt(st.nextToken().trim());
		String priorityOrder=st.nextToken();
		
		Sergeant s=new SergeantImpl(serName, workHours, numOfThreads, priorityOrder, vecSkills);
		s.setMaxMissions(maxNumOfMissions);
		
		ChiefOfStaffImpl.getInstance().addSergeant(s);
	}

	@Override
	public void addItem(String cmd) {
		StringTokenizer st=new StringTokenizer(cmd);
		String itemName=st.nextToken();
		int itemAmount=Integer.parseInt(st.nextToken());
		ItemImpl item=new ItemImpl(itemName, itemAmount);
		WarehouseImpl.getInstance().addItem(item);
	}

	@Override
	public void stop() {
		ChiefOfStaffImpl.getInstance().stop();
		this.runFlag=false;
	}

	@Override
	public void executeCommand(String cmd) throws Exception {
		StringTokenizer st = new StringTokenizer(cmd);
		if(!st.hasMoreTokens())
			throw new Exception("We will ignore this, this time.");
		
		String newCmd = st.nextToken();
		if (cmd.length()>newCmd.length())
			cmd=cmd.substring(newCmd.length()+1);
		
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
		while (this.runFlag && (cmd=this.getCommand())!=null)
		{
			try
			{
				this.executeCommand(cmd);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
	}
}
