package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

public class ProcessingException extends RuntimeException {

	private String errorMessage;

	/**
	 * Constructor for creating a image processing error message
	 * @param errorMessage String of the error message output
	 */
	public ProcessingException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
}
