package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="REPORTTBL")
public class Report {

	private int reportId;
	private Date importedOn;
	private RawImage independentImage;
	private TestResults independentTest;
	private List<String> processedImagePath;
	private List<Exception> processingErrors;
	private String independentProcessedImagePath;
	private String independentProcessType;
	private int subjectId;
	
	/**
	 * Default constructor for a report
	 */
	public Report() {
	}

	/**
	 * Getter method for Report ID
	 * @return - int of the Report ID
	 */
	@Id
	@GeneratedValue
	public int getReportId() {
		return reportId;
	}

	/**
	 * Setter method for report ID
	 * @param reportId - int for report ID
	 */
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}


	/**
	 * Returns the data on which the report was generated
	 * @return Date on which the report was generated
	 */
	public Date getImportedOn() {
		return importedOn;
	}
	
	/**
	 * Setter method for date imported
	 * @param importedOn - Date report imported on
	 */
	public void setImportedOn(Date importedOn) {
		this.importedOn = importedOn;
	}
	
	/**
	 * Returns a raw image used in the report
	 * @return RawImage for the raw image used
	 */
	@Transient
	public RawImage getIndependentImage() {
		return independentImage;
	}
	/**
	 * Getter method for retrieving the independent test result
	 * @return TestResults for a single test
	 */
	@Transient
	public TestResults getIndependentTest() {
		return independentTest;
	}
	
	/**
	 * Getter method for the saved image path for the processed images
	 * @return List of Strings of the file path locations for the processed images
	 */
	@Transient
	public List<String> getProcessedImagePath() {
		return processedImagePath;
	}

	/**
	 * Getter method for the errors that occurred during image processing steps
	 * @return List of Exceptions received during image processing
	 */
	@Transient
	public List<Exception> getProcessingErrors() {
		return processingErrors;
	}

	/**
	 * Getter method for retrieving report's processed image path
	 * @return - String filepath for processed image
	 */
	public String getIndependentProcessedImagePath() {
		return independentProcessedImagePath;
	}

	/**
	 * Setter method for processed image filepath
	 * @param independentProcessedImagePath - String filepath for processed image
	 */
	public void setIndependentProcessedImagePath(
			String independentProcessedImagePath) {
		this.independentProcessedImagePath = independentProcessedImagePath;
	}

	/**
	 * Getter methdo for processing type of report
	 * @return - String processtype name of report
	 */
	public String getIndependentProcessType() {
		return independentProcessType;
	}

	/**
	 * Setter method for processing type of report
	 * @param independentProcessType - String process type name
	 */
	public void setIndependentProcessType(String independentProcessType) {
		this.independentProcessType = independentProcessType;
	}

	/**
	 * Getter method for subject ID
	 * @return - int for subject ID
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * Setter method for subject ID
	 * @param subjectId - int for subject ID
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}



}
