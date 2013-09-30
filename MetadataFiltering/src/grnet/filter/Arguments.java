/**
 * 
 */
package grnet.filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author vogias
 * 
 */
public class Arguments {

	Properties props;

	public Arguments() throws FileNotFoundException, IOException {
		props = new Properties();
		props.load(new FileInputStream("configure.properties"));
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @param props
	 *            the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}

	// public String getInputFolder() {
	// return props.getProperty(Constants.inputFolder);
	// }

	public String getDestFolderLocation() {
		return props.getProperty(Constants.outputFolder);
	}
	public String getQueries() {
		return props.getProperty(Constants.filteringQuery);
	}
}
