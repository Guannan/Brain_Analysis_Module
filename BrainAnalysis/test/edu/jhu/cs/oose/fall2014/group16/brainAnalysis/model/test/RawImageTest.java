package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Data;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Image;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ImageDataOOSE;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessedImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessingException;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.XMLImageData;

public class RawImageTest {

	/**
	 * Given a file with data (subjectID), return a mapping of its fields
	 */
	@Test
	public void extractFields() {
		try {
			Image data = new RawImage("NotEmpty.img");
			Map<String, String> orgData = data.organize();
			assertFalse(orgData.isEmpty());
			assertTrue(orgData.containsKey("subjectID"));
			assertTrue(orgData.containsValue("AT0001"));
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertFalse(orgData3.isEmpty());
			assertTrue(orgData3.containsKey("subjectID"));
			assertTrue(orgData3.containsValue("AT0001"));
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * If rawData file in null extractFields should return an empty map
	 */
	@Test
	public void extractNullFields() {
		try {
			Image data = new RawImage(null);
			Map<String, String> orgData = data.extractFields();
			assertTrue(orgData.isEmpty());
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertTrue(orgData3.isEmpty());
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * If rawData file is empty, extractFields should return an empty map
	 */
	@Test
	public void extractEmptyFields() {
		try {
			Image data = new RawImage("Empty.img");
			Map<String, String> orgData = data.extractFields();
			assertTrue(orgData.isEmpty());
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertTrue(orgData3.isEmpty());
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * Organize data should return a map. If extractFields has not been called
	 * organize will call it.
	 */
	@Test
	public void organize() {
		Data data = new RawImage("NotEmpty.img");
		Map<String, String> orgData = data.organize();
		assertFalse(orgData.isEmpty());
		data.extractFields();
		Map<String, String> orgData2 = data.organize();
		assertEquals(orgData, orgData2);
	}

	/**
	 * If the data file has no rawData or extractedData the method should return
	 * an empty map
	 */
	@Test
	public void organizeNull() {
		Data data = new RawImage(null);
		Map<String, String> orgData = data.organize();
		assertTrue(orgData.isEmpty());
		data.extractFields();
		orgData = data.organize();
		assertTrue(orgData.isEmpty());

	}

	/**
	 * If the data file has an empty rawData or extractedData the method should
	 * return an empty map
	 */
	@Test
	public void organizeEmpty() {
		Data data = new RawImage("Empty.img");
		Map<String, String> orgData = data.organize();
		assertTrue(orgData.isEmpty());
		data.extractFields();
		orgData = data.organize();
		assertTrue(orgData.isEmpty());
	}

	/**
	 * Given an image file, extract the XMLImageData
	 */
	@Test
	public void getImageData() {
		try {
			Image data = new RawImage("NotEmpty.img");
			XMLImageData xData = data.getImageData();
			assertEquals(xData.getSubject(), "AT0001");
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when getting
	 * image data
	 */
	@Test
	public void getNullImageData() {

		try {
			Image data = new RawImage(null);
			data.getImageData();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Cannot extract data without image");
		}
	}

	/**
	 * Given an empty image file throw an error when getting image data
	 */
	@Test
	public void getEmptyImageData() {
		try {
			Image data = new RawImage("Empty.img");
			data.getImageData();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Cannot extract data without image");
		}
	}

	/**
	 * Given an image file return the path of the image data associated with the
	 * file
	 */
	@Test
	public void viewImage() {
		try {
			Image data = new RawImage("NotEmpty.img");
			ImageDataOOSE xData = data.viewImage();
<<<<<<< HEAD
			assertEquals(xData.getImagePath(), "/notEmpty.png");
=======
			assertEquals(xData.getRawImagePath(), "/notEmpty.png");
>>>>>>> 108e5ea91f805276c3955c3f22291285821c6572
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when viewing
	 * image
	 */
	@Test
	public void viewNullImage() {

		try {
			Image data = new RawImage(null);
			data.viewImage();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "File does not exist");
		}
	}

	/**
	 * Given an empty image file throw an error when viewing image
	 */
	@Test
	public void viewEmptyImage() {
		try {
			Image data = new RawImage("Empty.img");
			data.viewImage();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "File is empty");
		}
	}

	/**
	 * Given a procesType return the processed image associated with the raw
	 * image
	 */
	@Test
	public void processImageTest() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType("sampleProcess",
					"sampleProcess.class");
			ProcessedImage pImage = rImage.processImage(type);
			assertNotEquals(rImage.getImageData(), pImage.getImageData());
			assertNotEquals(rImage.viewImage(), pImage.viewImage());
			assertEquals(pImage.getProcessType(), type);
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}

	}

	/**
	 * Given a correct image file, try to process with null process type. Throws
	 * an error
	 */
	@Test
	public void nullProcessImage() {
		try {
			RawImage data = new RawImage("notEmpty.img");
			data.processImage(null);
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(), "Cannot process with no process type");
		}
	}

	/**
	 * Given a correct image file, try to process with empty process type.
	 * Throws an error
	 */
	@Test
	public void emptyProcessImage() {
		try {
			RawImage rImage = new RawImage("notEmpty.img");
			ProcessType type = new ProcessType(null, null);
			rImage.processImage(type);
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(),
					"Cannot process with an empty process type");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when processImage
	 * is called
	 */
	@Test
	public void processNullImage() {

		try {
			RawImage data = new RawImage(null);
			data.processImage(new ProcessType("sampleProcess",
					"sampleProcess.class"));
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(), "File does not exist");
		}
	}

	/**
	 * Given an empty image file throw an error when processImage is called
	 */
	@Test
	public void processEmptyImage() {
		try {
			RawImage data = new RawImage("Empty.img");
			data.processImage(new ProcessType("sampleProcess",
					"sampleProcess.class"));
			fail("Processing Error Not Thrown");
		} catch (ProcessingException e) {
			assertEquals(e.getMessage(), "File does not exist");
		}
	}

}
