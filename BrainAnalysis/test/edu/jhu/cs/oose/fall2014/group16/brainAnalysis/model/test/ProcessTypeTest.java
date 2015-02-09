package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessedImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessingException;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;

public class ProcessTypeTest {

	/**
	 * Given a rawImage return the processed image associated with the raw image
	 * after it is processed
	 */
	@Test
	public void processImageTest() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType("sampleProcess",
					"sampleProcess.class");
			ProcessedImage pImage = type.process(rImage);
			assertNotEquals(rImage.getImageData(), pImage.getImageData());
			assertNotEquals(rImage.viewImage(), pImage.viewImage());
			assertEquals(pImage.getProcessType(), type);
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}

	}

	/**
	 * Given a correct image file, try to process with null code path. Throws an
	 * error
	 */
	@Test
	public void nullProcessImage() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType("sampleProcess", null);
			type.process(rImage);
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(), "Cannot find the process path");
		} catch(FileNotFoundException e) {
			fail("File not found exception");
		}
	}

	/**
	 * Given a correct image file, try to process with a process whose's path
	 * does not exist. Throws an error
	 */
	@Test
	public void wrongProcessImage() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType("sampleProcess",
					"missingSampleProcess.class");
			type.process(rImage);
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(), "Cannot find the process path");
		} catch (FileNotFoundException e) {
			fail("File not found exception");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when processImage
	 * is called
	 */
	@Test
	public void processNullImage() {

		try {
			RawImage rImage = new RawImage(null);
			ProcessType type = new ProcessType("sampleProcess",
					"sampleProcess.class");
			type.process(rImage);
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Image File does not exist");
		}
	}

	/**
	 * Given an empty image file throw an error when processImage is called
	 */
	@Test
	public void processEmptyImage() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType("sampleProcess",
					"sampleProcess.class");
			type.process(rImage);
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Image File is empty");
		}
	}
}
