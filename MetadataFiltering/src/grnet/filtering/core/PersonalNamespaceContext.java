/**
 * 
 */
package grnet.filtering.core;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * @author vogias
 * 
 */
public class PersonalNamespaceContext implements NamespaceContext {

	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix == null)
			throw new NullPointerException("Null prefix");
		else if ("dc".equals(prefix))
			return "http://purl.org/dc/elements/1.1/";
		else if ("pre".equals(prefix))
			return "http://www.example.com/books";
		else if ("lom".equals(prefix))
			return "http://ltsc.ieee.org/xsd/LOM";
		else if ("xml".equals(prefix))
			return XMLConstants.XML_NS_URI;
		return XMLConstants.NULL_NS_URI;
	}

	@Override
	public String getPrefix(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator getPrefixes(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
