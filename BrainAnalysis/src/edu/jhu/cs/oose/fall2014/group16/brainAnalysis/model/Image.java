package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Image extends Data {

	File rawData;
	
	/**
	 * Default constructor for Image class
	 */
	public Image() {
		super();
	}
	/**
	 * Constructor to create a new Image based on the input file.
	 * @param A raw data file
	 */
	public Image(File rawData) {
		super(rawData);
		this.rawData = rawData;
	}
	
	/**
	 * Returns the XML element containing information on the image
	 * @return XMLImageData that contains information on the image
	 * @throws FileNotFoundException 
	 */
	public XMLImageData getImageData() throws FileNotFoundException {
		return null;
	}
	
	/**
	 * Retrieves a image to be viewed
	 * @return ImageData with data for viewing
	 */
	public ImageDataOOSE viewImage() throws FileNotFoundException {
		return null;
	}
}
