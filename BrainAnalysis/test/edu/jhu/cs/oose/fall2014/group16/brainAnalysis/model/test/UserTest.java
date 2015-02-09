package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Data;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Report;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.User;

public class UserTest {

	User user;

	@Before
	public void setUp() throws Exception {
		user = new User("user", "password");
	}

	/**
	 * Test that reports are saved after a successful call of process image
	 */
	@Test
	public void getPastReports() {
		List<Report> reports = user.getPastReports();
		assertEquals(reports.size(), 0);
		RawImage raw = new RawImage("image.img");
		List<RawImage> list = new ArrayList<RawImage>();
		list.add(raw);
		ProcessType process = new ProcessType("sample", "sample.class");
		user.processImage(list, process);
		reports = user.getPastReports();
		assertEquals(reports.size(), 1);
	}

	/**
	 * Test that reports are not saved after an unsuccessful call of process
	 * image
	 */
	@Test
	public void getPastReportsFail() {
		List<Report> reports = user.getPastReports();
		assertEquals(reports.size(), 0);
		RawImage raw = new RawImage("image.img");
		List<RawImage> list = new ArrayList<RawImage>();
		list.add(raw);
		ProcessType process = new ProcessType("sample", null);
		user.processImage(list, process);
		reports = user.getPastReports();
		assertEquals(reports.size(), 0);
	}

	/**
	 * Access data from patient id
	 */
	@Test
	public void accessData() {
		List<String> list = new ArrayList<String>();
		String patientId = "AT0001";
		list.add(patientId);
		List<Data> data = user.accessData(list);
		assertFalse(data.isEmpty());
	}

	/**
	 * Access data from bad patient id
	 */
	@Test
	public void accessIncorrectData() {
		List<String> list = new ArrayList<String>();
		String patientId = "QWRWEQ";
		list.add(patientId);
		List<Data> data = user.accessData(list);
		assertTrue(data.isEmpty());
	}

	/**
	 * Access data from empty list
	 */
	@Test
	public void accessEmptyData() {
		List<String> list = new ArrayList<String>();
		List<Data> data = user.accessData(list);
		assertTrue(data.isEmpty());
	}

	/**
	 * Access data from null list
	 */
	@Test
	public void accessNullData() {
		List<Data> data = user.accessData(null);
		assertTrue(data.isEmpty());
	}

	/**
	 * Verify report has good data after successful call. The success is determined
	 * in the process method of processType
	 */
	@Test
	public void processImage() {
		RawImage raw = new RawImage("image.img");
		List<RawImage> list = new ArrayList<RawImage>();
		list.add(raw);
		ProcessType process = new ProcessType("sample", "sample.class");
		Report report = user.processImage(list, process);
		assertFalse(report.getProcessesDone().isEmpty());	
	}
	
	/**
	 * Verify report has bad data after an unsuccessful call. The success is determined
	 * in the process method of processType
	 */
	@Test
	public void processBadImage() {
		RawImage raw = new RawImage("badImage.img");
		List<RawImage> list = new ArrayList<RawImage>();
		list.add(raw);
		ProcessType process = new ProcessType("sample", "sample.class");
		Report report = user.processImage(list, process);
		assertTrue(report.getProcessesDone().isEmpty());	
	}
	
}
