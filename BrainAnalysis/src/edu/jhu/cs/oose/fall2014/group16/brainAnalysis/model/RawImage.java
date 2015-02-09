package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RawImageTBL")
public class RawImage extends Image {

	private Integer rawImageID;
	String rawImagePath;
	
	/**
	 * Default constructor for RawImage class
	 */
	public RawImage() {
		super();
	}
	/**
	 * Constructor to create a new RawImage based on the input file.
	 * @param A raw data file
	 */
	public RawImage(String rawImagePath) {
		//super(rawImagePath);
		this.rawImagePath = rawImagePath;
	}
	
	/**
	 * Getter method for rawImage ID
	 * @return int for the rawImage's ID
	 */
	@Id
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
	 * Getter method for the raw image data file
	 * @return File containing the information pointing to the raw image data
	 */
	public String getRawImagePath() {
		return rawImagePath;
	}

	/**
	 * Setter method for the raw image data file
	 * @param rawData specification for the file raw data
	 */
	public void setRawImagePath(String rawImagePath) {
		this.rawImagePath = rawImagePath;
	}

	/**
	 * Returns a processed image. Function calls process from ProcessType on the raw image
	 * @param type An Image processing type
	 * @return ProcessedImage where the raw image is transformed with the given processing type
	 */
	public ProcessedImage processImage(ProcessType type) {
		return null;
	}


	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		RawImage data2 = (RawImage) other;
		if (this.getRawImageID().equals(data2.getRawImageID())) {
			return true;
		}
		return false;
	}
}
