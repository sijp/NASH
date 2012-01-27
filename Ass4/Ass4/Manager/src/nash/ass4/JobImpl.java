/**
 * 
 */
package nash.ass4;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author nadav
 *
 */
public class JobImpl implements Job {
	
	public static final String NONSUBMITTED="Non Submitted", 
								SUBMITTED="Submitted", 
								FINISHED= "Finished";
	
	private String id;
	private String resourceId;
	private String representationSource;
	private String representationTarget;
	private String jobXml;
	private String jobStatus;
	private Document xmlDocument;

	private Object lock=new Object();
	
	/**
	 *
	 */
	 
	 public JobImpl(String id,String resId,String repTargetId,String xml)
	 {
	 	this.id=id;
	 	this.resourceId=resId;
	 	this.jobXml=xml;
	 	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	 	this.jobStatus = JobImpl.NONSUBMITTED;
	 	try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get fDom representation of the XML file
			StringReader reader = new StringReader(this.jobXml);
			InputSource inputSource = new InputSource(reader);
			this.xmlDocument = db.parse(inputSource);
			reader.close();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		Element docEle = this.xmlDocument.getDocumentElement();
		
		NodeList nl = docEle.getElementsByTagName("InputRepresentation");
		this.representationSource=((Element) nl.item(0)).getAttribute("id");
		ResourceClosetImpl.getInstance().getResource(resId)
			.getRepresentation(this.representationSource).addJob(this);
		this.representationTarget=repTargetId;
		
		Element outputRepElem=this.xmlDocument.createElement("OutputRepresentation");
		outputRepElem.setAttribute("id",repTargetId);
		nl.item(0).getParentNode().insertBefore(outputRepElem, nl.item(0));
		//this.xmlDocument.insertBefore(outputRepElem,((Element)nl.item(1)));
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
		System.out.println("******");
		System.out.println(this.jobXml);
		String xmlString="";
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(this.xmlDocument);
			transformer.transform(source, result);

		xmlString = result.getWriter().toString();
		System.out.println(xmlString);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
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

	@Override
	public void setStatus(String newStatus) {
		synchronized (lock) {
			this.jobStatus = newStatus;
		}
		
	}

}
