package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ImageDataTBL")
public class ImageDataOOSE {

	private Integer rawImageID;
	private String subjectID;
	private Date scanDate;
	private String imageType;
	private String imagePath;
	
	/**
	 * Default constructor for ImageDataOOSE class
	 */
	public ImageDataOOSE() {
	}
	
	/**
	 * Constructor to create a piece of image data
	 * @param imagePath - A string indicating the location that the image data is stored
	 */
	public ImageDataOOSE(String subjectID, Date scanDate, String imageType, String imagePath) {
		this.subjectID = subjectID;
		this.scanDate = scanDate;
		this.imageType = imageType;
		this.imagePath = imagePath;
	}

	/**
	 * Getter method for image's id
	 * @return Integer of the image's id
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
	 * Getter method for getting the subjectID
	 * @return String indicating the subjectID
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * Setter method for the the subjectID
	 * @param fileFormat specifying the subjectID
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	
	/**
	 * Getter method to return scanDate
	 * @return Date on which image taken
	 */
	public Date getScanDate() {
		return scanDate;
	}
	
	/**
	 * Setter method for scan date of the image
	 * @param scanDate - Date for scan date
	 */
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
	
	/**
	 * Getter method to return image type
	 * @return - String indicating image type
	 */
	public String getImageType() {
		return imageType;
	}
	
	/**
	 * Setter method for image type
	 * @param imageType - String image type
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	/**
	 * Getter method for image path
	 * @return - String for file path of image
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * Setter method for filepath of image
	 * @param imagePath - String of image filepath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		ImageDataOOSE data2 = (ImageDataOOSE) other;
		if (this.getRawImageID().equals(data2.getRawImageID())) {
			return true;
		}
		return false;
	}


}
