package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class Data {

	Map<String, String> fields;
	File rawData;
	Map<String, String> extractedFields;

	public Data() {
		
	}
	/**
	 * Constructor to create a new piece of Data.
	 * 
	 * @param rawData
	 *            raw data file
	 */
	public Data(File rawData) {
		this.rawData = rawData;
	}

	/**
	 * Extracts the important information from the piece of data
	 * 
	 * @return A map of important information names to results regarding the
	 *         patient from the data
	 */
	public Map<String, String> extractFields() {
		return null;
	}

	/**
	 * Organizes the data in by a given order, if the class is not overridden in
	 * a subclass it will be sorted alphabetically
	 */
	public Map<String, String> organize() {
		// TODO Organize Data
		return extractedFields;
	}
}
