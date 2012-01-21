/**
 * 
 */
package nash.ass4;

/**
 * @author nadav
 *
 */

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.io.*;

public class MimeType {

	static final String defaultType = "text/plain";
	Hashtable<String , String> types;

	public MimeType(String fname)
	{
		types = new Hashtable<String , String>();
		readTypes(fname);
	}


	public String getType(String fname)
	{
		StringTokenizer st = new StringTokenizer(fname, ".");
		String ext = "";
		String ans;

		while (st.hasMoreTokens())
			ext = st.nextToken();

		ans = (String) types.get(ext);
		return ans==null?defaultType:ans.toLowerCase();
	}

	private void readTypes(String fname)
	{
		BufferedReader in;
		StringTokenizer st;
		String line = null;

		try {
			in = new BufferedReader (new FileReader(fname));

		} catch (Exception e){
			System.out.println("cannot open file: " + fname);
			in = null;
		}

		if (in == null)
			return;

		while (true){
			String extension;
			String type;

			try {
				line = in.readLine();
			} catch (java.io.IOException e) {
				// end of file.
				break;
			}
	
			// eof??
			if (line == null)
				break;

			st = new StringTokenizer(line, " \n\t\r\b");
			if (st.countTokens() < 2)
				continue;
			type = st.nextToken();
			while (st.hasMoreTokens()){
				extension = st.nextToken();
				types.put(extension, type);
			}
		}
		
		// add a text/plain mime type for no extension:
		types.put("", defaultType);
	}


	public static void main(String [] args)
	{
		String file;
		MimeType mt = new MimeType("mime.types");

		for (int i=0; i<args.length; i++)
			System.out.println("the type of "+ args[i]+ " is: "+ mt.getType(args[i]));

		return;
	}


	
	
}
