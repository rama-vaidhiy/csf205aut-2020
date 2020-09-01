package week5;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Java Core implementation of the SAX Parser
 * Parsing using Default and Customized Handler
 * Only for demonstration. No validation done
 */
public class SAXXMLParser {

    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String noSchemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWNoSchema.xml";

    private SAXParser saxParser = null;


    public static void main(String[] args)
    {
        SAXXMLParser parser = new SAXXMLParser();
        try {
            parser.loadBuilders();
            parser.parseXMLFile();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private void loadBuilders() throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParser = saxParserFactory.newSAXParser();
    }

    private void parseXMLFile() throws IOException, SAXException {
        if(null!=saxParser)
        {
            //We are using default handlers in this case.
            System.out.println("Parsing the XML file using SAX method with a default Handler");
            System.out.println("-------------------------------------------------------------");
            saxParser.parse(new File(noSchemaXMLFile), new DefaultHandler());
            //Using the customised handler now
            System.out.println("Parsing the XML file using SAX method with a customized Handler");
            System.out.println("---------------------------------------------------------------");
            saxParser.parse(new File(noSchemaXMLFile), new MyXMLHandler());
        }
    }


}
/**
 * Customised XML Element handler.
 * Handling only the Element -> Start and the Characters part
 */
class MyXMLHandler extends DefaultHandler
{


    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#startElement
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("+ "+qName);
        if(null!=attributes)
        {
            for(int i=0;i<attributes.getLength();i++)
            {
                System.out.println("\t"+attributes.getQName(i)+" = "+attributes.getValue(i));
            }

        }
    }


    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#characters
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String text = new String(ch, start, length);
        System.out.println("\t\t++ "+ text.trim());
    }
}
