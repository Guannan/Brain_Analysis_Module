package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Data;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Image;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ImageDataOOSE;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessedImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.XMLImageData;

public class ProcessedImageTest {

	/**
	 * Given a file with data (subjectID), return a mapping of its fields
	 */
	@Test
	public void extractFields() {
		Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
		Map<String, String> orgData = data.organize();
		assertFalse(orgData.isEmpty());
		assertTrue(orgData.containsKey("subjectID"));
		assertTrue(orgData.containsValue("AT0001"));
		try {
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertFalse(orgData3.isEmpty());
			assertTrue(orgData3.containsKey("subjectID"));
			assertTrue(orgData3.containsValue("AT0001"));
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * If processedData file in null extractFields should return an empty map
	 */
	@Test
	public void extractNullFields() {
		Image data = new ProcessedImage(null,null,null);
		Map<String, String> orgData = data.extractFields();
		assertTrue(orgData.isEmpty());
		try {
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertTrue(orgData3.isEmpty());
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * If processedData file is empty, extractFields should return an empty map
	 */
	@Test
	public void extractEmptyFields() {
		Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
		Map<String, String> orgData = data.extractFields();
		assertTrue(orgData.isEmpty());
		try {
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertTrue(orgData3.isEmpty());
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * Organize data should return a map. If extractFields has not been called
	 * organize will call it. Fields Extracted from XMLImageData should be the
	 * same
	 */
	@Test
	public void organize() {
		Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
		Map<String, String> orgData = data.organize();
		assertFalse(orgData.isEmpty());
		data.extractFields();
		Map<String, String> orgData2 = data.organize();
		assertEquals(orgData, orgData2);
		try {
			Map<String, String> orgData3 = data.getImageData().extractFields();
			assertEquals(orgData, orgData3);
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	/**
	 * If the data file has no processedData or extractedData the method should
	 * return an empty map
	 */
	@Test
	public void organizeNull() {
		Data data = new ProcessedImage(null,null,null);
		Map<String, String> orgData = data.organize();
		assertTrue(orgData.isEmpty());
		data.extractFields();
		orgData = data.organize();
		assertTrue(orgData.isEmpty());

	}

	/**
	 * If the data file has an empty processedData or extractedData the method
	 * should return an empty map
	 */
	@Test
	public void organizeEmpty() {
		Data data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
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
			Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
			XMLImageData xData = data.getImageData();
			assertEquals(xData.getSubject(), "AT0001");
		} catch (FileNotFoundException e) {
			fail("Throws exception");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when getting
	 * image data
	 */
	@Test
	public void getNullImageData() {

		try {
			Image data = new ProcessedImage(null,null,null);
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
			Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
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
<<<<<<< HEAD
			Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
=======
			Image data = new ProcessedImage(new File("NotEmpty.img"));
>>>>>>> 108e5ea91f805276c3955c3f22291285821c6572
			ImageDataOOSE xData = data.viewImage();
			assertEquals(xData.getImagePath(), "/notEmpty.png");
		} catch (FileNotFoundException e) {
			fail("Error thrown");
		}
	}

	/**
	 * Given no image file or a null image file throw an error when viewing an
	 * image
	 */
	@Test
	public void viewNullImage() {
		try {
			Image data = new ProcessedImage(null,null,null);
			data.viewImage();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "File does not exist");
		}
	}

	/**
	 * Given an empty image file throw an error when viewing an image
	 */
	@Test
	public void viewEmptyImage() {
		try {
			Image data = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
			data.viewImage();
			fail("Processing Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "File is empty");
		}
	}

}
