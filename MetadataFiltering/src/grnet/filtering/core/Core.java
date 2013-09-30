/**
 * 
 */
package grnet.filtering.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author vogias
 * 
 */
public class Core {

	DocumentBuilderFactory parseFactory;
	DocumentBuilder builder;

	XPathFactory filterFactory;
	XPath xpath;

	public Core() {
		// TODO Auto-generated constructor stub
		parseFactory = DocumentBuilderFactory.newInstance();
		parseFactory.setNamespaceAware(false);
		try {
			builder = parseFactory.newDocumentBuilder();

			filterFactory = XPathFactory.newInstance();
			xpath = filterFactory.newXPath();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Document parseDocument(File file) {

		Document parseDoc;
		try {

			parseDoc = builder.parse(file);

		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return parseDoc;
	}

	public boolean filterXML(File file, String expression) {
		Document doc = parseDocument(file);

		// xpath.setNamespaceContext(new PersonalNamespaceContext());
		try {

			XPathExpression expr = xpath.compile(expression);

			Object result = expr.evaluate(doc);

			if (result != null) {
				if (result instanceof String) {

					String res = (String) result;
					try {
						double d = Double.parseDouble(res);
						System.out.println("Double value is:" + d);
						if (d > 0)
							return true;
						else
							return false;

					} catch (NumberFormatException ex) {

						if (res.equalsIgnoreCase("true"))
							return true;
						else if (res.equalsIgnoreCase("false"))
							return false;
						else {

							if (res.equals(""))
								return false;
							else
								return true;
						}
					}

				}

				else {

					return false;
					// throw new XPathFunctionException(
					// "Could not convert argument type");
				}
			}

			return false;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {// "http://83.212.101.124/odsAP/lomODS.xsd"
		// TODO Auto-generated method stub
		Core core = new Core();

		// boolean filterXML = core.filterXML(new File(
		// "C:\\Users\\vogias\\Desktop\\books.xml"),
		// "//pre:book[pre:author='Neal Stephenson']/pre:title/text()");
		//

		boolean filterXML = core
				.filterXML(
						new File(
								"C:\\Users\\vogias\\Desktop\\ODS_DATA\\data\\terena_mdstore\\tv.uvigo.es\\oai_UVigo-Tv.s.1.xml"),
						"/dc/format/text()='videoshit/x-flv'");
		// count(/dc/format)
		// /dc/type[contains(text(),'Image')] and /dc/date/text()='2007-05-02'

		System.out.println("Flag is:" + filterXML);

	}
}
