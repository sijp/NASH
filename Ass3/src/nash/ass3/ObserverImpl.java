package nash.ass3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	public void printCompleteMissions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printIncompleteMissions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printSergeants() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printItems() {
		// TODO Auto-generated method stub

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
	public void executeCommand(String cmd) {
		// TODO Auto-generated method stub

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
