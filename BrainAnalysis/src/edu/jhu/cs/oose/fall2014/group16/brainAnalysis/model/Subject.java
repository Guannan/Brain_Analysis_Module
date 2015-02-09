package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SubjectTBL")
public class Subject {

	private String name;
	private Date dateOfBirth;
	private int subjectID;
	private String sex;
	private String diagnosis;
	
	/**
	 * Default constructor for Subject class
	 */
	public Subject() {
	}
	
	/**
	 * Constructor for creating a new test subject
	 * @param name String of the subject's name
	 * @param id String of the unique ID given to the subject
	 * @param dateOfBirth Date on which the subject was born
	 */
	public Subject(String name, Date dateOfBirth, String sex, String diagnosis) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.diagnosis = diagnosis;
	}

	/**
	 * Getter method to return subject's date of birth
	 * @return Date on which subject was born
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * Setter method for DOB of subject
	 * @param dateOfBirth - Date for DOB
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * Getter method to return subject's ID
	 * @return String of subject's unique ID
	 */
	@Id
	@GeneratedValue
	@Column(name="SubjectID")
	public int getID() {
		return subjectID;
	}
	/**
	 * Setter method for subject ID
	 * @param subjectID - int subjectID
	 */
	public void setID(int subjectID) {
		this.subjectID = subjectID;
	}
	/**
	 * Getter method to return subject's name
	 * @return String of subject's name
	 */
	@Column(name="SubjectName")
	public String getName() {
		return name;
	}
	/**
	 * Setter method for subject name
	 * @param name - String name for subject
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for subject's gender
	 * @return - String subject gender
	 */
	@Column(name="SubjectSex")
	public String getSex() {
		return sex;
	}
	/**
	 * Setter method for subject's gender
	 * @param sex - string gender
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * Getter method for diagnosis
	 * @return - String diagnosis name
	 */
	@Column(name="Diagnosis")
	public String getDiagnosis() {
		return diagnosis;
	}
	/**
	 * Setter method for subject's diagnosis
	 * @param diagnosis - String diagnosis name
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
		
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		Subject subject2 = (Subject) other;
		if (this.getID() == subject2.getID()) {
			return true;
		}
		return false;
	}
	
}
