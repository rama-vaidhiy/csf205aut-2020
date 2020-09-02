package week6;

import java.io.IOException;
import java.math.BigInteger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Processing XML with Java
 * 
 * @author Elliotte Rusty Harold
 *
 */

public class UnPrefixedFibonacciNS {
	public static void main(String[] args) {

		Namespace ns = Namespace.getNamespace("http://www.w3.org/1998/Math/MathML");
		Element root = new Element("math", ns);

		BigInteger low = BigInteger.ONE;
		BigInteger high = BigInteger.ONE;

		for (int i = 1; i <= 5; i++) {

			Element mrow = new Element("mrow", ns);

			Element mi = new Element("mi", ns);
			mi.setText("f(" + i + ")");
			mrow.addContent(mi);

			Element mo = new Element("mo", ns);
			mo.setText("=");
			mrow.addContent(mo);

			Element mn = new Element("mn", ns);
			mn.setText(low.toString());
			mrow.addContent(mn);

			BigInteger temp = high;
			high = high.add(low);
			low = temp;
			root.addContent(mrow);

		}

		Document doc = new Document(root);
		try {
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
			serializer.output(doc, System.out);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
