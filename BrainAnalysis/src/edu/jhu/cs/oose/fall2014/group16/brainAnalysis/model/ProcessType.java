package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.FileNotFoundException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Embeddable
@Table(name="ProcessTypeTBL")
public class ProcessType {

	private String name;
	private String path;
	private boolean defaultProcessing;
	private Report report;
	
	
	/**
	 * Default constructor use to create an instance of ProcessType
	 */
	public ProcessType() {
	}
	
	/**
	 * Constructor to create an instance of ProcessType given a Process Type
	 * @param template - existing ProcessType
	 */
	public ProcessType(ProcessType template) {
		this.name = template.name;
		this.path = template.path;
		this.defaultProcessing = template.defaultProcessing;
	}
	/**
	 * Constructor to specify a image processing option/algorithm
	 * @param name The name of the image processing algorithm
	 * @param path The path to the code for the image processing
	 */
	public ProcessType(String name, String path) {
		this.name = name;
		this.path = path;
		this.defaultProcessing = false;
	}
	
	/**
	 * Constructor for ProcessType given process type name
	 * @param name - String input for Process Type name
	 */
	public ProcessType(String name) {
		this.name = name;
		//this.path = path;
		this.defaultProcessing = false;
	}
	
	/**
	 * Compute the processed image given a raw image input and a processing type. This is where cooool things happen!
	 * @param image Input raw image
	 * @return ProcessedImage with filepath pointing to where image is saved
	 */
	public ProcessedImage process(RawImage image) throws FileNotFoundException {
		return null;
	}

	/**
	 * Getter method for the process type name
	 * @return String of the process type name
	 */
	@Id
	@Column(name="ProcessTypeName")
	public String getName() {
		return name;
	}

	/**
	 * Setter method for the process type name
	 * @param String name for the process type
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for the process type code path
	 * @return String where the code is saved
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Setter method for the process type code path
	 * @param path at where the process type is saved
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Returns true if Process type is a default processing type
	 * @return - boolean defaultprocessing
	 */
	public boolean isDefaultProcessing() {
		return defaultProcessing;
	}

	/**
	 * Sets the process type's default processing variable
	 * @param defaultProcessing - boolean for default processing or not
	 */
	public void setDefaultProcessing(boolean defaultProcessing) {
		this.defaultProcessing = defaultProcessing;
	}
	
	
}
