package week6;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JDOMXMLParserExternalValidation {
    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String schemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWNoSchema.xml";
    private Document xmlDoc = null;
    private SAXBuilder saxBuilder = null;
    /**
     * Main Method to run the program
     *
     * @param args
     */
    public static void main(String[] args) {

        JDOMXMLParserExternalValidation domParser = new JDOMXMLParserExternalValidation();
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
    private void loadBuilders() throws ParserConfigurationException, JDOMException {
        //Before creating the SAXBuilder, create a schema factory object
        String xsdFile  =  xmlFileDir + "week5" + fileSep + "sow.xsd";
        XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(xsdFile);
        //set that schema factory object in the SAXBuilder instead of using the schema defined in the XML
        saxBuilder = new SAXBuilder(schemafac);


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
        String newSchemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWNoSchema.xml";
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(xmlDoc, new FileWriter(newSchemaXMLFile));
    }

}
