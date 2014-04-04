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
package grnet.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vogias
 * 
 */
public class Enviroment {

	boolean envCreation = false;
	File filteredInData, filteredOutData, dataProviderFilteredIn,
			dataProviderFilteredOuT;
	Arguments arguments;

	
	public Enviroment(String sourcePath) {
		envCreation = createEnviroment(sourcePath);
	}

	/**
	 * @return the arguments
	 */
	public Arguments getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(Arguments arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the dataProvider
	 */

	/**
	 * @return the envCreation
	 */
	public boolean EnvCreation() {
		return envCreation;
	}

	/**
	 * @return the filteredInData
	 */
	public File getFilteredInData() {
		return filteredInData;
	}

	/**
	 * @return the filteredOutData
	 */
	public File getFilteredOutData() {
		return filteredOutData;
	}

	/**
	 * @return the dataProviderFilteredIn
	 */
	public File getDataProviderFilteredIn() {
		return dataProviderFilteredIn;
	}

	/**
	 * @return the dataProviderFilteredOuT
	 */
	public File getDataProviderFilteredOuT() {
		return dataProviderFilteredOuT;
	}

	/**
	 * @param filteredInData
	 *            the filteredInData to set
	 */
	public void setFilteredInData(File filteredInData) {
		this.filteredInData = filteredInData;
	}

	/**
	 * @param filteredOutData
	 *            the filteredOutData to set
	 */
	public void setFilteredOutData(File filteredOutData) {
		this.filteredOutData = filteredOutData;
	}

	/**
	 * @param dataProviderFilteredIn
	 *            the dataProviderFilteredIn to set
	 */
	public void setDataProviderFilteredIn(File dataProviderFilteredIn) {
		this.dataProviderFilteredIn = dataProviderFilteredIn;
	}

	/**
	 * @param dataProviderFilteredOuT
	 *            the dataProviderFilteredOuT to set
	 */
	public void setDataProviderFilteredOuT(File dataProviderFilteredOuT) {
		this.dataProviderFilteredOuT = dataProviderFilteredOuT;
	}

	private boolean createEnviroment(String sourcePath) {

		try {
			arguments = new Arguments();

			File destStructure = new File(arguments.getDestFolderLocation());

			if (destStructure.exists()) {
				filteredInData = new File(destStructure, "FILTERED_IN");
				filteredOutData = new File(destStructure, "FILTERED_OUT");
				filteredInData.mkdir();
				filteredOutData.mkdir();

				// File source = new File(arguments.getInputFolder());

				File source = new File(sourcePath);

				if (source.exists()) {
					dataProviderFilteredIn = new File(filteredInData,
							source.getName());
					dataProviderFilteredIn.mkdir();
					dataProviderFilteredOuT = new File(filteredOutData,
							source.getName());
					dataProviderFilteredOuT.mkdir();

				} else {
					// System.err.println("Wrong source folder location.");
					// System.err.println("Exiting...");

					System.out.println("Wrong source folder location.");
					System.err.println("Exiting...");
					System.exit(-1);
				}

				return true;
			} else
				return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			e.printStackTrace();
			return false;
		}

	}

	// public static void main(String[] args) {//
	// "http://83.212.101.124/odsAP/lomODS.xsd"
	// // TODO Auto-generated method stub
	// Enviroment enviroment = new Enviroment();
	//
	// if (enviroment.envCreation) {
	// System.out.println(enviroment.getFilteredInData().getPath());
	// System.out.println(enviroment.getFilteredOutData().getPath());
	// }
	// }
}
