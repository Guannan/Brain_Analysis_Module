package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.DatabaseManager;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Image;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ImageDataOOSE;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.JISTDatabaseQuery;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Subject;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.User;

public class JISTDatabaseQueryTest {

	private static JISTDatabaseQuery jQuery = new JISTDatabaseQuery();
	private static DatabaseManager manager = DatabaseManager
			.getDatabaseManager();

	/**
	 * Setup the environment for testing. Create our database manager.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager.initializeDatabase("OOSE", "Group16");
	}

	/**
	 * Test if the database password belongs to a user in the system
	 */
	@Test
	public void UserTest() {
		User user2 = new User("Amy", "abc");
		manager.databaseInsert(user2);

		assertTrue(jQuery.checkPassword("abc"));
		assertFalse(jQuery.checkPassword("Abc"));
		assertFalse(jQuery.checkPassword("BadPassword"));

		User user3 = new User("Barney", "def");
		manager.databaseInsert(user3);

		assertTrue(jQuery.checkPassword("def"));
		assertTrue(jQuery.checkPassword("abc"));
		assertFalse(jQuery.checkPassword("BadPassword"));

		User user4 = new User("Carl", "ghi");
		manager.databaseInsert(user4);

		assertTrue(jQuery.checkPassword("ghi"));
		assertTrue(jQuery.checkPassword("def"));
		assertTrue(jQuery.checkPassword("abc"));
		assertFalse(jQuery.checkPassword("BadPassword"));

		manager.databaseDelete(user3);
		assertTrue(jQuery.checkPassword("ghi"));
		assertFalse(jQuery.checkPassword("def"));
		assertTrue(jQuery.checkPassword("abc"));
		assertFalse(jQuery.checkPassword("BadPassword"));

	}

	@Test
	public void queryDatabaseTest() {
		Subject subject1 = new Subject("Alex", Date.valueOf("1991-09-03"),
				"Male", "SCA6");
		manager.databaseInsert(subject1);

		ImageDataOOSE imageData1 = new ImageDataOOSE(String.valueOf(subject1.getID()),
				Date.valueOf("2014-09-03"), "MRI", "image1.img");
		manager.databaseInsert(imageData1);

		// Test on Image restrictions only
		Map<String, String> data = new HashMap<String, String>();
		data.put("imageType", "MRI");

		List<ImageDataOOSE> images = jQuery.queryDatabase("Group16", null, null);
		System.out.println("Found " + images.size() + " results");
		assertTrue(images.size() > 0);

		images = jQuery.queryDatabase("Group16", null, data);
		System.out.println("Found " + images.size() + " results");
		assertTrue(images.size() > 0);

		// Test on Subject restrictions only
		Map<String, String> subData = new HashMap<String, String>();
		subData.put("ID", String.valueOf(subject1.getID()));

		images = jQuery.queryDatabase("Group16", subData, null);
		System.out.println("Found " + images.size() + " results");
		assertTrue(images.size() > 0);

		// Test on both Subject and Image restrictions\

		// Test on Subject DOB min/max
		subData = new HashMap<String, String>();
		subData.put("birthDateMin", "1989-01-01");
		subData.put("birthDateMax", "2000-01-01");

		images = jQuery.queryDatabase("Group16", subData, null);
		System.out.println("Found " + images.size() + " results");
		assertTrue(images.size() > 0);

		// Test on Subject DOB min/max
		subData = new HashMap<String, String>();
		
		subData.put("birthDateMin", "2000-01-01");
		subData.put("birthDateMax", "2001-01-01");

		images = jQuery.queryDatabase("Group16", subData, null);
		System.out.println("Found " + images.size() + " results");
		assertTrue(images.size() == 0);

	}

}
