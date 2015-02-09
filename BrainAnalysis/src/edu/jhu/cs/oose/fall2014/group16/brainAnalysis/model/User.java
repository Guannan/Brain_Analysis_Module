package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="UserTBL")
public class User {

	private Integer userID;
	private String username;
	private String password;
	private List<Report> pastReports;
	
	
	/**
	 * Default constructor for user class
	 */
	public User() {
	}
	/**
	 * Constructor for creating a new user to our database!
	 * @param username String of the user's name
	 * @param password String of the user's password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Getter method for user ID
	 * @return int for the user's ID
	 */
	@Id
	@GeneratedValue
	public Integer getUserID() {
		return userID;
	}
	/**
	 * Setter method for the user ID
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = Integer.valueOf(userID);
	}
	
	/**
	 * Getter method for retrieving the user's name
	 * @return String of the user's name
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * Setter method for the user's name
	 * @param username a String containing user's name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter method for the user's password (need some protection)
	 * @return String of user's password
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * Setter method for the user's password
	 * @param password A string specifying password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method for retrieving a list of the user's past test reports
	 * @return List of Report that the user had taken before
	 */
	@Transient
	public List<Report> getPastReports() {
		return pastReports;
	}
	/**
	 * Setter method for past reports
	 * @param pastReports A list of reports
	 */
	public void setPastReports(List<Report> pastReports) {
		this.pastReports = pastReports;
	}	
	/**
	 * Returns a list of data matching the input list of data names
	 * @param dataName A list of data names to be retrieved
	 * @return A list of data retrieved
	 */
	public List<Data> accessData(List<String> dataName) {
		return null;
	}
	
	/**
	 * Generate a report after calling all image processing steps on each raw image in the raw image list.
	 * @param rawList List of raw images
	 * @param procType The process type for the images
	 * @return Report containing information from the processed images
	 */
	public Report processImage(List<RawImage> rawList, ProcessType procType) {
		return null;
	}
	
	@Override
	public boolean equals(Object other)
	{
	   if (other == null)
	   {
	      return false;
	   }

	   if (this.getClass() != other.getClass())
	   {
	      return false;
	   }
	   
	   User user2 = (User) other;
	   
	   if (this.getUserID() == user2.getUserID()) {
		   return true;
	   }
	   
	   return false;
	   
	}

	
}
