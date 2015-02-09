package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="XMLImageDataTBL")
public class XMLImageData {

	private String imageType;
	private Subject subject;
	private Date takenOn;
	
	Map<String, String> fields;
	
	public XMLImageData() {
		
	}
	/**
	 * Create XMLImageData from MIPAV parsed XML file
	 */
	public XMLImageData(Map<String, String> fields) {
		this.fields = fields;
	}

	/**
	 * Getter method for retrieve the image type
	 * @return String of the image type, raw or processed
	 */
	public String getImageType() {
		return imageType;
	}
	/**
	 * Setter method for image type
	 * @param imageType String specifying image type
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	/**
	 * Getter method for retrieving the identity of the test subject
	 * @return Subject instance of a test subject
	 */
	public Subject getSubject() {
		return subject;
	}
	/**
	 * Setter method for the subject 
	 * @param subject A subject
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	/**
	 * Getter method for the date on which the subject took the tests
	 * @return Date on which subject took tests
	 */
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTakenOn() {
		return takenOn;
	}
	/**
	 * Setter method for date taken on
	 * @param takenOn Date of the image taken on
	 */
	public void setTakenOn(Date takenOn) {
		this.takenOn = takenOn;
	}
	/**
	 * Return a list of attributes that the XML file holds
	 * @return Map of Strings containing individual attribute field values
	 */
	public Map<String, String> extractFields() {
		return fields;
	}
	
}
