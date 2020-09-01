package week5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXStreamBuilder;
import org.jdom2.input.sax.XMLReaders;

public class JDOMStaxXMLReader {
	private static String userDir = System.getProperty("user.dir");
	private static String fileSep = System.getProperty("file.separator");
	private static String xmlFileDir = userDir + fileSep + "resources" + fileSep;
	private String noSchemaXMLFile = xmlFileDir + "week3" + fileSep + "CSF205SoWNoSchema.xml";
	private Document xmlDoc = null;
	private SAXBuilder saxBuilder = null;

	public static void main(String[] args) {
		JDOMStaxXMLReader parser = new JDOMStaxXMLReader();
		try {
			parser.loadBuilders();
			parser.parseXMLFile();
		} catch (ParserConfigurationException | XMLStreamException | JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * API to lad the SAX Builder
	 *
	 * @throws ParserConfigurationException
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 */
	private void loadBuilders()
			throws ParserConfigurationException, FileNotFoundException, XMLStreamException, JDOMException {
		saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);// the parameter is optional
		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(noSchemaXMLFile));

		StAXStreamBuilder builder = new StAXStreamBuilder();
		xmlDoc = builder.build(reader);
	}

	/**
	 * API to parse the XML document
	 *
	 * @throws JDOMException
	 * @throws IOException
	 */
	private void parseXMLFile() throws JDOMException, IOException {
		System.out.println("XML File location : " + noSchemaXMLFile);
		xmlDoc = saxBuilder.build(new File(noSchemaXMLFile));
		printXMLElements();
	}

	/**
	 * Parse all the XML elements read from the file
	 */
	private void printXMLElements() {
		if (null != xmlDoc) {
			Element rootElem = xmlDoc.getRootElement();
			if (null != rootElem) {
				// Read the XML and print all the elements from it
				System.out.println("Printing the current XML");
				System.out.println("------------------------");
				parseElement(rootElem);

			}

		}
	}

	/**
	 * Parse each element recursively and print them
	 * 
	 * @param e
	 */
	private void parseElement(Element e) {
		printElement(e);
		List<Element> children = e.getChildren();
		if (!(children.isEmpty())) {
			for (Element ce : children) {
				parseElement(ce);
			}
		}
	}

	/**
	 * Print each element's name, text value if any and the attribute details
	 * 
	 * @param e
	 */
	private void printElement(Element e) {
		System.out
				.println("+ " + e.getName() + ((e.getTextNormalize().isEmpty()) ? " " : " = " + e.getTextNormalize()));
		List<Attribute> attrs = e.getAttributes();
		if (!(attrs.isEmpty())) {
			for (Attribute a : attrs) {
				System.out.println("\t " + a.getName() + " = " + a.getValue());
			}
		}
	}

}
