package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

public class ServerConnectionException extends RuntimeException {

	private String errorMessage;

	/**
	 * Constructor for creating a server connection exception error
	 * @param errorMessage String of the error message output
	 */
	public ServerConnectionException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
}
