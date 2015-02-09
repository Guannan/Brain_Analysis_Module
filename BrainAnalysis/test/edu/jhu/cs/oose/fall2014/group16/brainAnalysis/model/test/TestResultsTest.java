package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Data;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Image;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessedImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessingException;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.TestResults;

public class TestResultsTest {

	/**
	 * Given a file with data (subjectID), return a mapping of its fields
	 */
	@Test
	public void extractFields() {
		Data data = new TestResults();
		Map<String, String> orgData = data.organize();
		assertFalse(orgData.isEmpty());
		assertTrue(orgData.containsKey("subjectID"));
		assertTrue(orgData.containsValue("AT0001"));
	}

	/**
	 * If rawData file in null extractFields should return an empty map
	 */
	
	/*@Test
	public void extractNullFields() {
		Data data = new TestResults(null);
		Map<String, String> orgData = data.extractFields();
		assertTrue(orgData.isEmpty());
	}

	*//**
	 * If rawData file is empty, extractFields should return an empty map
	 *//*
	@Test
	public void extractEmptyFields() {
		Data data = new TestResults(new File("Empty.txt"));
		Map<String, String> orgData = data.extractFields();
		assertTrue(orgData.isEmpty());
	}

	*//**
	 * Organize data should return a map. If extractFields has not been called
	 * organize will call it.
	 *//*
	@Test
	public void organize() {
		Data data = new TestResults(new File("NotEmpty.txt"));
		Map<String, String> orgData = data.organize();
		assertFalse(orgData.isEmpty());
		data.extractFields();
		Map<String, String> orgData2 = data.organize();
		assertEquals(orgData, orgData2);
	}

	*//**
	 * If the data file has no rawData or extractedData the method should return
	 * an empty map
	 *//*
	@Test
	public void organizeNull() {
		Data data = new TestResults(null);
		Map<String, String> orgData = data.organize();
		assertTrue(orgData.isEmpty());
		data.extractFields();
		orgData = data.organize();
		assertTrue(orgData.isEmpty());

	}

	*//**
	 * If the data file has an empty rawData or extractedData the method should
	 * return an empty map
	 *//*
	@Test
	public void organizeEmpty() {
		Data data = new TestResults(new File("Empty.txt"));
		Map<String, String> orgData = data.organize();
		assertTrue(orgData.isEmpty());
		data.extractFields();
		orgData = data.organize();
		assertTrue(orgData.isEmpty());
	}

	*//**
	 * Correlate a test for patient AT0001 taken on 20140503 to an image for the
	 * same patient on the same day
	 *//*
	@Test
	public void correlateToImage() {
		try {
			Image image = new RawImage(new File("AT0001_20140503.img"));
			TestResults test = new TestResults(new File("AT0001_20140503.txt"));
			Image associateImage = test.correlateToImage();
			assertEquals(associateImage, image);
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	*//**
	 * Try to correlate a test for patient AT0001 taken on 20140503 to an image
	 * for the same patient on the same day. No image exists so null is returned
	 *//*
	@Test
	public void correlateToNoImage() {
		try {
			TestResults test = new TestResults(new File("AT0001_20140503.txt"));
			Image associateImage = test.correlateToImage();
			assertNull(associateImage);
		} catch (FileNotFoundException e) {
			fail("Throws error");
		}
	}

	*//**
	 * Try to correlate null test results to an image
	 *//*
	@Test
	public void nullCorrelateToImage() {
		try {
			TestResults test = new TestResults(null);
			test.correlateToImage();
			fail("Error Not Thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Test results file is not found");
		}
	}

	*//**
	 * Correlate a test for patient AT0001 taken on 20140503 to an image for the
	 * same patient on the same day
	 *//*
	@Test
	public void emptyCorrelateToImage() {
		try {
			TestResults test = new TestResults(new File("empty.txt"));
			test.correlateToImage();
			fail("No error thrown");
		} catch (FileNotFoundException e) {
			assertEquals(e.getMessage(), "Test results file is empty");
		}
	}
	
	*//**
	 * Get test results for a  test
	 *//*
	@Test
	public void getResultsForTest() {
		TestResults test = new TestResults(new File("results.txt"));
		String result = test.getResultsForTest("test");
		assertEquals(result, "182");
	}
	
	*//**
	 * Try to get test results for a non-existant test, or a test the user did not take
	 *//*
	@Test
	public void getResultsForBadTest() {
		TestResults test = new TestResults(new File("results.txt"));
		String result = test.getResultsForTest("Fake test");
		assertEquals(result, "");
	}
	
	*//**
	 * Try to get test results for null test
	 *//*
	@Test
	public void getResultsForNullTest() {
		TestResults test = new TestResults(new File("results.txt"));
		String result = test.getResultsForTest(null);
		assertEquals(result, "");
	}
	
	*//**
	 * Get a list of all tests the patient took/had
	 *//*
	@Test
	public void getTestsPerformed() {
		TestResults test = new TestResults(new File("results.txt"));
		List<String> results = test.getTestsPerformed();
		assertEquals(results.size(), 1);
		assertEquals(results.get(0), "test");
	}
	
	*//**
	 * Get a list of all tests the patient took/had, passing a null file
	 *//*
	@Test
	public void getTestsPerformedNull() {
		TestResults test = new TestResults(null);
		List<String> results = test.getTestsPerformed();
		assertEquals(results.size(), 0);
	}
	
	*//**
	 * Get a list of all tests the patient took/had, passing a empty file
	 *//*
	@Test
	public void getTestsPerformedEmpty() {
		TestResults test = new TestResults(new File("empty.txt"));
		List<String> results = test.getTestsPerformed();
		assertEquals(results.size(), 0);
	}*/
	

}
