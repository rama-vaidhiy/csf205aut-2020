package week6;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.math.BigInteger;
import java.io.IOException;

/**
 * Processing XML with Java
 * @author Elliotte Rusty Harold
 *
 */
public class PrefixedFibonacci {

  public static void main(String[] args) {

    Element root = new Element("math", "mathml",
     "http://www.w3.org/1998/Math/MathML");

    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;

    for (int i = 1; i <= 5; i++) {

      Element mrow = new Element("mrow", "mathml",
       "http://www.w3.org/1998/Math/MathML");

      Element mi = new Element("mi", "mathml",
       "http://www.w3.org/1998/Math/MathML");
      mi.setText("f(" + i + ")");
      mrow.addContent(mi);

      Element mo = new Element("mo", "mathml",
       "http://www.w3.org/1998/Math/MathML");
      mo.setText("=");
      mrow.addContent(mo);

      Element mn = new Element("mn", "mathml",
       "http://www.w3.org/1998/Math/MathML");
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
    }
    catch (IOException e) {
      System.err.println(e);
    }

  }

}