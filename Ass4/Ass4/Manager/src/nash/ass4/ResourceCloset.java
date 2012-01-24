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
	public String getNewResourceId();
	public String getNewRepresentationId(String resId);
	public Vector<Job> getJobsByRepresentation(String resId,string repId);
	public String getOriginalMimeType(String resId);
	public File getRepresentationFile(res,rep);
}
