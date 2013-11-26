/**
 * 
 */
package grnet.filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vogias
 * 
 */
public class FilteringReport {

	/**
	 * @param args
	 */

	String filteringReportpath;
	int filteredInFilesNum, filteredOutFilesNum;
	File filteringReport;
	BufferedWriter writer;
	long start;

	public FilteringReport(String path, String name) throws IOException {
		filteringReportpath = path;
		filteringReport = new File(path, "filteringReport_" + name + ".txt");

		filteredInFilesNum = 0;
		filteredOutFilesNum = 0;
		start = System.currentTimeMillis();
		writer = new BufferedWriter(new FileWriter(filteringReport));
		String date = new Date().toString();
		writer.append("Report date:" + date);
		writer.newLine();

	}

	/**
	 * @return the filteredInFilesNum
	 */
	public int getFilteredInFilesNum() {
		return filteredInFilesNum;
	}

	/**
	 * @return the filteredOutFilesNum
	 */
	public int getFilteredOutFilesNum() {
		return filteredOutFilesNum;
	}

	/**
	 * @param validFilesNum
	 *            the validFilesNum to set
	 */
	public void raiseFilteredInFilesNum() {
		this.filteredInFilesNum++;
	}

	/**
	 * @param invalidFilesNum
	 *            the invalidFilesNum to set
	 */
	public void raiseFilteredOutFilesNum() {
		this.filteredOutFilesNum++;
	}

	/**
	 * @return the validationReportpath
	 */
	public String getFilteringReportpath() {
		return filteringReportpath;
	}

	/**
	 * @param validationReportpath
	 *            the validationReportpath to set
	 */
	public void setFilteringReportpath(String filteringReportpath) {
		this.filteringReportpath = filteringReportpath;
	}

	private String appendTotalParsedFiles() throws IOException {
		int total = getFilteredInFilesNum() + getFilteredOutFilesNum();
		writer.append("Total parsed files:" + total);
		writer.newLine();

		return String.valueOf(total);
	}

	private String appendFilteredInFilesNum() throws IOException {

		int filteredInFilesNum = getFilteredInFilesNum();
		writer.append("Number of filtered in records:" + filteredInFilesNum);
		writer.newLine();

		return String.valueOf(filteredInFilesNum);
	}

	private String appendFilteredOutFilesNum() throws IOException {
		int filteredOutFilesNum = getFilteredOutFilesNum();
		writer.append("Number of filtered out records:"
				+ getFilteredOutFilesNum());
		writer.newLine();

		return String.valueOf(filteredOutFilesNum);
	}

	private String appendDuration() throws IOException {
		long end = System.currentTimeMillis();
		long diff = end - start;
		writer.append("Total time (ms):" + diff);
		writer.newLine();

		return String.valueOf(diff);
	}

	public String appendXPathExpression(String expression) throws IOException {
		writer.append("XPath expression used:" + expression);
		writer.newLine();

		return expression;
	}

	public void appendGeneralInfo() throws IOException {

		appendTotalParsedFiles();
		appendDuration();
		appendFilteredInFilesNum();
		appendFilteredOutFilesNum();

		writer.close();

	}

	public void appendXMLFileNameNStatus(String name, String status)
			throws IOException {
		writer.append("Filtering file:" + name);
		writer.newLine();
		writer.append("Filtering Status:" + status);
		writer.newLine();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
