/**
 * 
 */
package nash.ass4;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author nadav
 *
 */
public class JobImpl implements Job {

	//TODO: allow jobStatus to be only one of this: preAssigned, Assigned , completed.
	
	private String id;
	private String resourceId;
	private String representationSource;
	private String representationTarget;
	private String jobXml;
	private String jobStatus;
	private Document xmlDocument;
	
	/**
	 *
	 */
	 
	 public JobImpl(String id,String resId,String repTargetId,String xml)
	 {
	 	this.id=id;
	 	this.resourceId=resId;
	 	this.jobXml=xml;
	 	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	 	
	 	try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get fDom representation of the XML file
			this.xmlDocument = db.parse(fXMLFileName);

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		Element docEle = this.xmlDocument.getDocumentElement();
		
		NodeList nl = docEle.getElementByTagName("InputRepresentation");
		this.representationSource=((Element) nl.item(0)).getAttribute("id");

		this.representationTarget=repTargetId;
		
		Element outputRepElem=this.xmlDocument.createElement("OutputRepresentation");
		outputRepElem.setAttribute("id",repTargetId);
		this.xmlDocument.insertAfter(outputRepElem,((Element) nl.item(0)));
		
	 }
	
	/* (non-Javadoc)
	 * @see nash.ass4.Job#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getRepresentation()
	 */
	@Override
	public String getRepresentationSource() {
		return this.representationSource;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getResource()
	 */
	@Override
	public String getResource() {
		return this.resourceId;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getXML()
	 */
	@Override
	public String getXML()
	{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(this.xmlDocument);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();
		return xmlString;
	}

	/* (non-Javadoc)
	 * @see nash.ass4.Job#getStatus()
	 */
	@Override
	public String getStatus() {
		return this.jobStatus;
	}


	@Override
	public String getRepresentationTarget() {
		return this.representationTarget;
	}

}
