package week8;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ServletXMLReader {
	
	 private static String userDir = System.getProperty("user.dir");
	    private static String fileSep = System.getProperty("file.separator");
	    private String xmlFileDir = userDir + fileSep + "resources" + fileSep;
	    private String noSchemaXMLFile = xmlFileDir + "week3" + fileSep + "CSF205SoWNoSchema.xml";
	    private Document xmlDoc = null;
	    private SAXBuilder saxBuilder = null;
	    

	    /**
	     * API to lad the SAX Builder
	     *
	     * @throws ParserConfigurationException
	     */
	    void loadBuilders() throws ParserConfigurationException {
	        saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);//the parameter is optional
	    }

	    /**
	     * API to parse the XML document
	     *
	     * @throws JDOMException
	     * @throws IOException
	     */
	    String parseXMLFile(String loc) throws JDOMException, IOException {
	    	
	    	noSchemaXMLFile = loc;
	        System.out.println("XML File location : " +noSchemaXMLFile);
	        xmlDoc = saxBuilder.build(new File(noSchemaXMLFile));
	        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	        return outputter.outputString(xmlDoc);
	        
	    }


}
