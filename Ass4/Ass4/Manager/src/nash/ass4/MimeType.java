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

	static final String DEFAULTTYPE = "text/plain";
	Hashtable<String , String> types;

	/**
	 * 
	 * @param fname fmnann
	 */
	public MimeType(String fname)
	{
		this.types = new Hashtable<String , String>();
		readTypes(fname);
	}

	/**
	 * 
	 * @param fname fnammm
	 * @return rett
	 */
	public String getType(String fname)
	{
		StringTokenizer st = new StringTokenizer(fname, ".");
		String ext = "";
		String ans;

		while (st.hasMoreTokens())
			ext = st.nextToken();

		ans = (String)this.types.get(ext);
		return ans==null?DEFAULTTYPE:ans.toLowerCase();
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
				this.types.put(extension, type);
			}
		}
		
		// add a text/plain mime type for no extension:
		this.types.put("", DEFAULTTYPE);
	}



	
	
}
