package week6;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Class to illustrate how the XSLT works using Java APIs
 */
public class XMLTransform {
    private static String userDir = System.getProperty("user.dir");
    private static String fileSep = System.getProperty("file.separator");
    private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
    private String schemaXMLFile = xmlFileDir + "week4" + fileSep + "CSF205SoWNoSchema.xml";
    private String xslFile = xmlFileDir + "week4"+fileSep+"sow.xsl";
    private String targetHTMLFile = xmlFileDir + "week6"+fileSep+"XML2HTML.html";


    public static void main(String[] args)
    {
        XMLTransform xmlxslt = new XMLTransform();
        try {
            xmlxslt.transformXML();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * API that performs the actual XSL transformation from XML to HTML
     * @throws TransformerException
     */
    private void transformXML() throws TransformerException {
        StreamSource source = new StreamSource(new File(schemaXMLFile));
        StreamSource stylesource = new StreamSource(new File(xslFile));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);

        StreamResult result = new StreamResult(new File(targetHTMLFile));
        transformer.transform(source, result);
    }
}
