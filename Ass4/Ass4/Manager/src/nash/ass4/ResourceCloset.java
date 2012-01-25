/**
 * 
 */
package nash.ass4;

import java.io.File;
import java.util.Vector;

/**
 * @author nadav
 *
 */
public interface ResourceCloset {
	/**
	 * 
	 * @return
	 */
	public String getNewResourceId();
	/**
	 * 
	 * @param resId
	 * @return
	 */
	public String getNewRepresentationId(String resId);
	/**
	 * 
	 * @param resId
	 * @param repId
	 * @return
	 */
	public Vector<Job> getJobsByRepresentation(String resId , String repId);
	/**
	 * 
	 * @param resId
	 * @return
	 */
	public String getOriginalMimeType(String resId);
	/**
	 * 
	 * @param res
	 * @param rep
	 * @return
	 */
	public File getRepresentationFile(String res ,String rep);
}
