package week5;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

/**
 * Class to demonstrate the XML Parser API's and its use
 * No schema validation is done.
 * Reading of the elements and adding a new element (without saving) is demonstrated
 */
public class JDOM2XMLParserNonValidating {

    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String noSchemaXMLFile = xmlFileDir + "week3" + fileSep + "CSF205SoWNoSchema.xml";
    private Document xmlDoc = null;
    private SAXBuilder saxBuilder = null;

    /**
     * Main Method to run the program
     *
     * @param args
     */
    public static void main(String[] args) {

        JDOM2XMLParserNonValidating domParser = new JDOM2XMLParserNonValidating();
        try {
            domParser.loadBuilders();
            domParser.parseXMLFile();
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
        saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);//the parameter is optional
    }

    /**
     * API to parse the XML document
     *
     * @throws JDOMException
     * @throws IOException
     */
    private void parseXMLFile() throws JDOMException, IOException {
        System.out.println("XML File location : " +noSchemaXMLFile);
        xmlDoc = saxBuilder.build(new File(noSchemaXMLFile));
        printXMLElements();
    }

    /**
     * Parse all the XML elements read from the file
     */
    private void printXMLElements() {
        if(null!=xmlDoc)
        {
            Element rootElem = xmlDoc.getRootElement();
            if(null!=rootElem)
            {
                    //Read the XML and print all the elements from it
                    System.out.println("Printing the current XML");
                    System.out.println("------------------------");
                    parseElement(rootElem);
                    //Add a new Book to the Books Recommended section of the XML
                    //Do not save it to the XML yet
                    System.out.println("\n\nPrinting the Books Recommended Section with new book added");
                    System.out.println("----------------------------------------------------------");
                    addNewBook(rootElem);
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
        List<Element> booksReccoElement = parentElement.getChildren("booksRecommended");
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
        //this should work even if we do not add the isbn10 attribute
        //because there is no xsd attached to the XML file.
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

}
