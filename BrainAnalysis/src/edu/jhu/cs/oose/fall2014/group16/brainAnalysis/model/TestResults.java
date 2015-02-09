package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="TestResultsTBL")
public class TestResults extends Data {

	private int testID;
	private String subjectID;
	private Date takenOn;
	private String testName;
	private String testResult;
	
	/**
	 * Constructor to create a new Image based on the input file.
	 * @param rawData - A raw data file
	 */
	public TestResults() {
	}
	
	/**
	 * Links the test result to a specific image
	 * @return The image the test results correlated to
	 */
	@Transient
	public Image correlateToImage() throws FileNotFoundException {
		return null;
	}
	
	/**
	 * Returns the test result name for a specific test
	 * @param test String of the test name of interest
	 * @return String of the name of the test result
	 */
	@Transient
	public String getResultsForTest(String test) {
		return null;
	}
	
	/**
	 * Returns a list of all the test results
	 * @return A list of strings of all test result names
	 */
	@Transient
	public List<String> getTestsPerformed() {
		return null;
	}

	/**
	 * Getter method for test ID
	 * @return - int of test ID
	 */
	@Id
	@GeneratedValue
	public int getTestID() {
		return testID;
	}

	/**
	 * Setter method test ID
	 * @param testID - int of test ID
	 */
	public void setTestID(int testID) {
		this.testID = testID;
	}

	/**
	 * Getter method for date test is taken on
	 * @return - Date for test taken on
	 */
	public Date getTakenOn() {
		return takenOn;
	}

	/**
	 * Setter method for test taken on
	 * @param takenOn - Date for test taken on
	 */
	public void setTakenOn(Date takenOn) {
		this.takenOn = takenOn;
	}

	/**
	 * Getter method for test name
	 * @return - String of test name
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * Setter method for test name
	 * @param testName - String input for test name
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * Getter method for test result
	 * @return - String for test result
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 * Setter method for test result
	 * @param testResult - String input for test result
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	/**
	 * Getter method for subject ID
	 * @return String - subject ID for test
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * Setter method for subject ID
	 * @param subjectID - String
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
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
	   
	   TestResults tr2 = (TestResults) other;
	   
	   if (this.getTestID() == tr2.getTestID()) {
		   return true;
	   }
	   
	   return false;
	   
	}
	
}
