package week5;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Uses the core DOM Implementation of Java to parse the XML file
 * Only for Demonstration purposes. No validation of XML done.
 */
public class DOMXMLParser {
    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String noSchemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWNoSchema.xml";
    private Document xmlDoc = null;
    private DocumentBuilder domBuilder = null;

    public static void main(String[] args)
    {
            DOMXMLParser parser = new DOMXMLParser();
        try {
            parser.loadBuilders();
            parser.parseXMLFile();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }


    /**
     * Load the necessary builders to parse the file
     * @throws ParserConfigurationException
     */
    private void loadBuilders() throws ParserConfigurationException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domBuilder = domFactory.newDocumentBuilder();


    }

    /**
     * Parse the XML file and start printing some data
     * @throws IOException
     * @throws SAXException
     */
    private void parseXMLFile() throws IOException, SAXException {
        if(null!=domBuilder)
        {
            xmlDoc = domBuilder.parse(new File(noSchemaXMLFile));
            if(null!=xmlDoc)
            {
                NodeList nodes = xmlDoc.getElementsByTagName("schemeOfWork");
                if(null!=nodes)
                {
                    printNodes(nodes);
                }
            }
        }
    }


    /**
     * Print all elements recursively using Core DOM Implementation
     * @param nl
     */
    private void printNodes(NodeList nl)
    {
        if(null!=nl)
        {
            for(int i=0;i<nl.getLength();i++ )
            {

                Node n = nl.item(i);
                System.out.print("+ "+n.getNodeName());
                if(null!=n.getNodeValue())
                {
                   System.out.println(" "+n.getNodeValue());
                }else
                {
                    System.out.println();
                }
                NamedNodeMap attrs = n.getAttributes();
                if(null!=attrs)
                {
                   for(int ai = 0;ai<attrs.getLength();ai++)
                   {
                       System.out.print("\t"+attrs.item(ai).getNodeName() + " = "+attrs.item(ai).getNodeValue());
                   }
                   System.out.println("");
                }
                NodeList cl = n.getChildNodes();
                printNodes(cl);
            }
        }
    }
}
