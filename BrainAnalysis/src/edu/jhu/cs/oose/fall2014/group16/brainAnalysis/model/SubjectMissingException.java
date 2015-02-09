package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

public class SubjectMissingException extends RuntimeException {

	private String errorMessage;

	
	/**
	 * Constructor for creating an error where the queried subject does not exist in database
	 * @param errorMessage String of error message output
	 */
	public SubjectMissingException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	
}
