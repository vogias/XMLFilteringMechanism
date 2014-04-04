/*******************************************************************************
 * Copyright (c) 2014 Kostas Vogias.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Kostas Vogias - initial API and implementation
 ******************************************************************************/
/**
 * 
 */
package grnet.filtering.core;

import java.util.List;

import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author vogias
 * 
 */
public class XPathValidator implements XPathFunction {

	@Override
	public Object evaluate(List args) throws XPathFunctionException {
		// TODO Auto-generated method stub
		if (args.size() != 1) {
			throw new XPathFunctionException(
					"Wrong number of arguments to XPath expression");
		}

		String result;
		Object o = args.get(0);

		// perform conversions
		if (o instanceof String) {
			result = (String) args.get(0);
			return result;
		} else if (o instanceof Boolean) {
			result = o.toString();
			return result;
		} else if (o instanceof Double) {
			result = o.toString();
			return result;
		} else if (o instanceof NodeList) {
			NodeList list = (NodeList) o;
			Node node = list.item(0);
			// getTextContent is available in Java 5 and DOM 3.
			// In Java 1.4 and DOM 2, you'd need to recursively
			// accumulate the content.
			result = node.getTextContent();
			return result;
		} else {
			throw new XPathFunctionException("Could not convert argument type");
		}

	}

}
