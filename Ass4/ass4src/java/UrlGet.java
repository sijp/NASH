import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class UrlGet
{
	public static void main(String [] a)
	{
		if (a.length != 1){
			System.out.println("Usage: java UrlGet url");
			return;
		}

		try
		{
			URL		url;
			URLConnection	urlConn;
			DataInputStream	input;

			url = new URL (a[0]);

			// URL connection channel.
			urlConn = url.openConnection();
			urlConn.setDoInput (true);
			urlConn.connect();

			// Get response data.
			input = new DataInputStream (urlConn.getInputStream ());

			String str;
			while (null != ((str = input.readLine())))
			{
				System.out.println (str);
			}

			input.close ();

		}
		catch (IOException ioe)
		{
			System.err.println("IOException: " + ioe.getMessage());
		}
	}	
}
