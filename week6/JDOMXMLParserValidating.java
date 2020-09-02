package week6;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class to demonstrate the XML Parser API's and its use
 * Schema Validation is demonstrated (if an invalid XML document associated with a schema)
 * is loaded, it will error out.
 * Reading of the elements and adding a new element (and saving) is demonstrated
 */
public class JDOMXMLParserValidating {

    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String schemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWWithXSDNoNS.xml";
    private Document xmlDoc = null;
    private SAXBuilder saxBuilder = null;
    /**
     * Main Method to run the program
     *
     * @param args
     */
    public static void main(String[] args) {

        JDOMXMLParserValidating domParser = new JDOMXMLParserValidating();
        try {
            //load a perfectly valid XML
            domParser.loadBuilders();
            //parse the XML - initially no error should be reported
            domParser.parseXMLFile(true);
            //save the updated document which has an error in the bookRecommended section
            domParser.saveUpdatedDocument();
            //now try to invalid XML again and it should fail.
            domParser.parseXMLFile(false);
        } catch (ParserConfigurationException | JDOMException | IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * API to lad the SAX Builder
     *
     * @throws ParserConfigurationException
     */
    private void loadBuilders() throws ParserConfigurationException {
        saxBuilder = new SAXBuilder(XMLReaders.XSDVALIDATING);
        //Have a customized Error Handler if you fancy
        //saxBuilder.setErrorHandler(new MyErrorHandler());
    }

    /**
     * API to parse the XML document
     *
     * @throws JDOMException
     * @throws IOException
     */
    private void parseXMLFile(boolean addNewBook) throws JDOMException, IOException {
        System.out.println("XML File location : " +schemaXMLFile);
        xmlDoc = saxBuilder.build(new File(schemaXMLFile));
        printXMLElements(addNewBook);
    }

    /**
     * Parse all the XML elements read from the file
     */
    private void printXMLElements(boolean addNewBook) throws IOException {
        if(null!=xmlDoc)
        {
            Element rootElem = xmlDoc.getRootElement();
            if(null!=rootElem)
            {
                //Read the XML and print all the elements from it
                System.out.println("Printing the current XML");
                System.out.println("------------------------");
                parseElement(rootElem);
                if(addNewBook){
                    //Add a new Book to the Books Recommended section of the XML
                    //Do not save it to the XML yet
                    System.out.println("\n\nPrinting the Books Recommended Section with new book added");
                    System.out.println("----------------------------------------------------------");
                    addNewBook(rootElem);
                }


            }

        }
    }

    /**
     * Parse each element recursively and print them
     * @param e
     */
    private void parseElement(Element e)
    {
        printElement(e);
        List<Element> children = e.getChildren();
        if(!(children.isEmpty()))
        {
            for(Element ce: children)
            {
                parseElement(ce);
            }
        }
    }

    /**
     * Print each element's name, text value if any and the attribute details
     * @param e
     */
    private void printElement(Element e)
    {
        System.out.println("+ "+e.getName()+ ((e.getTextNormalize().isEmpty())?" ":" = " +e.getTextNormalize()));
        List<Attribute> attrs = e.getAttributes();
        if(!(attrs.isEmpty())){
            for (Attribute a:attrs)
            {
                System.out.println("\t "+a.getName()+" = "+a.getValue());
            }
        }
    }


    /**
     * Add the new Book created to the parent booksRecommended parent element
     * @param parentElement
     */
    private void addNewBook(Element parentElement)
    {
        List<Element> booksReccoElement = parentElement.getChildren("booksRecommended", Namespace.getNamespace("http://www.example.org/sow"));
        if(null!=booksReccoElement && !(booksReccoElement.isEmpty()))
        {
            createNewBook(booksReccoElement.get(0));
            parseElement(booksReccoElement.get(0));
        }
    }

    /**
     * Create a new book element with all its children and attribute
     * @param parentElement
     */
    private void createNewBook(Element parentElement)
    {

        Element bookElement = new Element("book");
        //this should fail because accroding to the XSD the isbn10 value is mandatory
//        Attribute isbn10attr = new Attribute("isbn10","9781449365110");
//        bookElement.setAttribute(isbn10attr);

        Element titleElement = new Element("title");
        titleElement.setText("Java Web Services Up and Running");
        bookElement.addContent(titleElement);

        Element authorsElement = new Element("authors");
        Element authorElement = new Element("author");
        authorElement.setText("Martin Kalin");
        authorsElement.addContent(authorElement);
        bookElement.addContent(authorsElement);

        parentElement.addContent(bookElement);

    }

    private void saveUpdatedDocument() throws IOException {
        String newSchemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWWithXSDNoNS.xml";
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(xmlDoc, new FileWriter(newSchemaXMLFile));
    }


}

class MyErrorHandler implements ErrorHandler
{

    /**
     * Receive notification of a warning.
     *
     * <p>SAX parsers will use this method to report conditions that
     * are not errors or fatal errors as defined by the XML
     * recommendation.  The default behaviour is to take no
     * action.</p>
     *
     * <p>The SAX parser must continue to provide normal parsing events
     * after invoking this method: it should still be possible for the
     * application to process the document through to the end.</p>
     *
     * <p>Filters may use this method to report other, non-XML warnings
     * as well.</p>
     *
     * @param exception The warning information encapsulated in a
     *                  SAX parse exception.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see SAXParseException
     */
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("WARNING: "+exception.getMessage());
    }

    /**
     * Receive notification of a recoverable error.
     *
     * <p>This corresponds to the definition of "error" in section 1.2
     * of the W3C XML 1.0 Recommendation.  For example, a validating
     * parser would use this callback to report the violation of a
     * validity constraint.  The default behaviour is to take no
     * action.</p>
     *
     * <p>The SAX parser must continue to provide normal parsing
     * events after invoking this method: it should still be possible
     * for the application to process the document through to the end.
     * If the application cannot do so, then the parser should report
     * a fatal error even if the XML recommendation does not require
     * it to do so.</p>
     *
     * <p>Filters may use this method to report other, non-XML errors
     * as well.</p>
     *
     * @param exception The error information encapsulated in a
     *                  SAX parse exception.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see SAXParseException
     */
    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ERROR: "+exception.getMessage());
    }

    /**
     * Receive notification of a non-recoverable error.
     *
     * <p><strong>There is an apparent contradiction between the
     * documentation for this method and the documentation for {@link
     * ContentHandler#endDocument}.  Until this ambiguity
     * is resolved in a future major release, clients should make no
     * assumptions about whether endDocument() will or will not be
     * invoked when the parser has reported a fatalError() or thrown
     * an exception.</strong></p>
     *
     * <p>This corresponds to the definition of "fatal error" in
     * section 1.2 of the W3C XML 1.0 Recommendation.  For example, a
     * parser would use this callback to report the violation of a
     * well-formedness constraint.</p>
     *
     * <p>The application must assume that the document is unusable
     * after the parser has invoked this method, and should continue
     * (if at all) only for the sake of collecting additional error
     * messages: in fact, SAX parsers are free to stop reporting any
     * other events once this method has been invoked.</p>
     *
     * @param exception The error information encapsulated in a
     *                  SAX parse exception.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see SAXParseException
     */
    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("FATAL: "+exception.getMessage());
    }
}