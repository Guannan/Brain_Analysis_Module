package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProcessedImageTBL")
public class ProcessedImage extends Image {

	private Integer processedID;
	//private File processedData;
	private String processedData;
	private Integer rawImageID;
	//private ProcessType processType;
	private String processType;
	
	/*
	public ProcessedImage(Integer processedID, File processedData, Integer rawImageID, ProcessType processType) {
		this.rawImageID = rawImageID;
		this.processedID = processedID;
		this.processedData = processedData;
		this.processType = processType;
	}*/
	
	public ProcessedImage(String processedData, Integer rawImageID, String processType) {
		this.rawImageID = rawImageID;
		//this.processedID = processedID;
		this.processedData = processedData;
		this.processType = processType;
	}
	
	/**
	 * Getter method for rawImage ID
	 * @return int for the rawImage's ID
	 */
	@GeneratedValue
	public Integer getRawImageID() {
		return rawImageID;
	}
	/**
	 * Setter method for the rawImage ID
	 * @param userID
	 */
	public void setRawImageID(int rawImageID) {
		this.rawImageID = Integer.valueOf(rawImageID);
	}
	
	/**
	 * Getter method for processedImage ID
	 * @return int for the processedImage's ID
	 */
	@Id
	@GeneratedValue
	public Integer getProcessedID() {
		return processedID;
	}
	/**
	 * Setter method for the processedImage ID
	 * @param processed Image ID
	 */
	public void setProcessedID(int processedID) {
		this.processedID = Integer.valueOf(processedID);
	}
	
	/**
	 * Getter method for the processed image data file
	 * @return File containing the information pointing to the processed image data
	 */
	public String getProcessedData() {
		return processedData;
	}

	/**
	 * Setter method for the processed image data file
	 * @param processedData specification for the file processed image data
	 */
	public void setProcessedData(String processedData) {
		this.processedData = processedData;
	}

	/**
	 * Getter method for the processed type
	 */
	/*public ProcessType getProcessType() {
		return processType;
	}*/
	public String getProcessType() {
		return processType;
	}

	/**
	 * Setter method for the process type
	 */
	/*public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}*/
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	
	/*private ProcessType processType;
	File rawData;
	
	*/
	/**
	 * Constructor to create a new ProcessedImage based on the input file.
	 * @param rawData A raw data file
	 *//*
	public ProcessedImage(File rawData) {
		super(rawData);
		this.rawData = rawData;
		this.setProcessType(null);
		
	}
	
	*//**
	 * Constructor to create a new ProcessedImage based on the input file.
	 * @param rawData A raw data file
	 * @param processType The processed used on the image
	 *//*
	public ProcessedImage(File rawData, ProcessType process) {
		super(rawData);
		this.rawData = rawData;
		this.setProcessType(process);
		
	}
	
	*//**
	 * Constructor to create a new ProcessedImage based on the input file. Gives procesing type
	 * @param the process type
	 * @param A raw data file
	 *//*
	public ProcessedImage(ProcessType type, File rawData) {
		super(rawData);
		this.rawData = rawData;
		this.setProcessType(type);
	}

	*//**
	 * @return the processType
	 *//*
	public ProcessType getProcessType() {
		return processType;
	}

	*//**
	 * @param processType the processType to set
	 *//*
	private void setProcessType(ProcessType processType) {
		this.processType = processType;
	}*/
	
}
