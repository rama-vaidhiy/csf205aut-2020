package week6;

import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: DO NOT FORGET TO ADD jaxen.jar to run this code
 */
public class JDOMXPather {

    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String schemaXMLFile = xmlFileDir + "week5" + fileSep + "CSF205SoWNoSchema.xml";
    private Document xmlDoc = null;
    private SAXBuilder saxBuilder = null;
    private XPathFactory xpf = null;


    public static void main(String[] args)
    {
        JDOMXPather xpathTester = new JDOMXPather();
        try {
            xpathTester.loadBuilders();
            xpathTester.parseXMLFile();
            xpathTester.findAllBooks();
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
        System.out.println("XML File location : " +schemaXMLFile);
        xmlDoc = saxBuilder.build(new File(schemaXMLFile));
        xpf = XPathFactory.instance();
    }

    /**
     * Use the XPath expressions to search for all the books in the XML and print them
     * Since the XML chosen has got Namespace associated with it, we need to include them
     * in the search too, otherwise it will return empty list.
     */
    private void findAllBooks()
    {
        Namespace ns = Namespace.getNamespace("sow","http://www.example.org/sow");
        ArrayList<Namespace> nsList = new ArrayList<Namespace>();
        nsList.add(ns);
        XPathExpression<Element> expr =  xpf.compile("/sow:schemeOfWork/sow:booksRecommended/sow:book",
                Filters.element(),null,nsList);
        List<Element> books = expr.evaluate(xmlDoc);
        if(null!=books && !(books.isEmpty()))
        {
            for (Element eachBook : books){
                System.out.println("Printing the details of the book ");
                System.out.println("---------------------------------");
                parseElement(eachBook);
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

}
