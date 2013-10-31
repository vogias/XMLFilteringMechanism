/**
 * 
 */
package grnet.filter;

import grnet.filtering.core.Core;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vogias
 * 
 */
public class XMLFiltering {

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(XMLFiltering.class);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method ssstub

		StringBuffer logString = new StringBuffer();
		Enviroment enviroment = new Enviroment(args[0]);

		if (enviroment.envCreation) {
			Core core = new Core();

			XMLSource source = new XMLSource(args[0]);

			File sourceFile = source.getSource();

			if (sourceFile.exists()) {

				Collection<File> xmls = source.getXMLs();

				// System.out.println("Filtering folder:"
				// + enviroment.dataProviderFilteredIn.getName());
				// System.out.println("Number of files to filter:" +
				// xmls.size());
				//
				System.out.println("Filtering repository:"
						+ enviroment.dataProviderFilteredIn.getName());
				logString.append(enviroment.dataProviderFilteredIn.getName());

				System.out.println("Number of files to filter:" + xmls.size());

				Iterator<File> iterator = xmls.iterator();

				// System.out.println("Filtering...");

				FilteringReport report = null;
				if (enviroment.getArguments().getProps()
						.getProperty(Constants.createReport)
						.equalsIgnoreCase("true")) {
					report = new FilteringReport(enviroment.getArguments()
							.getDestFolderLocation(), enviroment
							.getDataProviderFilteredIn().getName());
				}

				while (iterator.hasNext()) {

					File xmlFile = iterator.next();

					boolean xmlIsFilteredIn = core.filterXML(xmlFile,
							enviroment.getArguments().getQueries());

					if (xmlIsFilteredIn) {
						try {
							if (report != null) {
								report.appendXMLFileNameNStatus(
										xmlFile.getPath(),
										Constants.filteredInData);
								report.raiseFilteredInFilesNum();
							}

							FileUtils.copyFileToDirectory(xmlFile,
									enviroment.getDataProviderFilteredIn());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							e.printStackTrace();
							System.out.println("Filtering failed.");
						}
					} else {

						try {
							if (report != null) {
								report.appendXMLFileNameNStatus(
										xmlFile.getPath(),
										Constants.filteredOutData);
								report.raiseFilteredOutFilesNum();
							}
							FileUtils.copyFileToDirectory(xmlFile,
									enviroment.getDataProviderFilteredOuT());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							e.printStackTrace();
							System.out.println("Filtering failed.");
						}
					}
				}
				if (report != null) {

					logString.append(" "
							+ report.appendXPathExpression(enviroment
									.getArguments().getQueries()));

					logString = report.appendGeneralInfo(logString);
				}
				// System.out.println("Filtering is done.");
				System.out.println("Filtering is done.");
				slf4jLogger.info(logString.toString());
			}

		}
	}
}
